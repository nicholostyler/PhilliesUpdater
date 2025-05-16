using System;
using System.Net.Http;
using System.Reflection.Metadata;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Threading.Tasks;
using PhilliesUpdater;

class Program
{
   
    static async Task Main(string[] args)
    {
        //TODO: https://statsapi.mlb.com/api/v1/game/715722/playByPlay this is the API for playbyplay of a game
        // TODO: API for players https://statsapi.mlb.com/api/v1/people/430832

        // Get score from temp file
        var homeScore = 0;
        var awayScore = 0;
        string homeTeam = String.Empty;
        string awayTeam = String.Empty;
        GameRoot oldGameData;
        GameRoot currentGameData;
        GameDetails gameDetails;
        GameDetails oldGameDetails;

        try
        {
            (oldGameData, currentGameData) = await GetGameDataAsync();
            oldGameDetails = await GetOldDetailsAsync();
            
            if (currentGameData.Dates.Count == 0)
            {
                if (oldGameData.Dates.Count == 0)
                {
                    // make sure it only alerts once.
                    return;
                }
                
                // no game today
                await GenericPush($"No game today.");
                WriteScoreToFile(JsonSerializer.Serialize(currentGameData));
                return;
            }
            
            gameDetails = await GetGameDetails(currentGameData.Dates.First().Games.First().GamePk);

            
            if (oldGameData.Dates != null && oldGameData.Dates.Count != 0)
            {
                string currentGameState = currentGameData.Dates.First().Games.First().Status.AbstractGameState;
                //todo: Exception if it's an off day'
                string oldGameState = oldGameData.Dates.First().Games.First().Status.AbstractGameState;
                Console.WriteLine(currentGameState);
                Console.WriteLine(oldGameState);
                
                if (oldGameState == "Final" && currentGameState != "Final")
                {
                    // Send Push for game reminder
                    homeTeam = currentGameData.Dates.First().Games.First().Teams.Home.Team.Name;
                    awayTeam = currentGameData.Dates.First().Games.First().Teams.Away.Team.Name;
                    await GenericPush($"Today - {awayTeam} @ {homeTeam}");

                }

                else if (oldGameState == "Live" && currentGameState == "Final")
                {
                    homeTeam = oldGameData.Dates.First().Games.First().Teams.Home.Team.Name;
                    awayTeam = oldGameData.Dates.First().Games.First().Teams.Away.Team.Name;
                    homeScore = currentGameData.Dates.First().Games.First().Teams.Home.Score;
                    awayScore = currentGameData.Dates.First().Games.First().Teams.Away.Score;
                    // Game is completed
                    Console.WriteLine($"FINAL - {homeTeam}: {homeScore} vs {awayTeam}: {awayScore}");
                    await GenericPush($"FINAL - {homeTeam}: {homeScore} vs {awayTeam}: {awayScore}");

                    // now that we alerted the user the game is done, write to prevent it from doing it again.
                    WriteScoreToFile(JsonSerializer.Serialize(currentGameData));
                    WriteDetailsToFile(JsonSerializer.Serialize(gameDetails));
                }
                else
                {
                    WriteScoreToFile(JsonSerializer.Serialize(currentGameData));
                    WriteDetailsToFile(JsonSerializer.Serialize(gameDetails));
                }

                // no matter what the state is, as long as the game is live update the score
                if (currentGameState == "Live")
                {
                    // Game is still in progress
                    var date = currentGameData.Dates.FirstOrDefault();
                    if (date != null && date.Games.Any())
                    {
                        // Get the first game
                        var game = date.Games.First();

                        // Get Current Score
                        await GetCurrentScoreAsync(game,
                            oldAwayScore: oldGameData.Dates.First().Games.First().Teams.Away.Score,
                            oldHomeScore: oldGameData.Dates.First().Games.First().Teams.Home.Score, gameDetails: gameDetails);
                        WriteScoreToFile(JsonSerializer.Serialize(currentGameData));
                        WriteDetailsToFile(JsonSerializer.Serialize(gameDetails));
                    }
                    else
                    {
                        Console.WriteLine("No games available.");
                    }
                }

                WriteScoreToFile(JsonSerializer.Serialize(currentGameData));
                WriteDetailsToFile(JsonSerializer.Serialize(gameDetails));
            }

        }
        catch (Exception e)
        {
            await GenericPush(e.Message);
        }
    }

    private static async Task<GameDetails> GetOldDetailsAsync()
    {
        string tempDetailsFilePath = Path.Combine(Path.GetTempPath(), "philliesgamedetails.json");
        GameDetails? oldData = new GameDetails();
        
        try
        {
            using HttpClient client = new HttpClient();
            string oldDetailsJson = String.Empty;
            
            if (File.Exists(tempDetailsFilePath))
            {
                oldDetailsJson = await File.ReadAllTextAsync(tempDetailsFilePath);
                if (string.IsNullOrWhiteSpace(oldDetailsJson))
                {
                    Console.WriteLine("Temp file is empty.");
                    WriteDetailsToFile(oldDetailsJson);
                }

                // temp json file has been found and is not empty.
                oldData = JsonSerializer.Deserialize<GameDetails>(oldDetailsJson);
            }
            else
            {
                Console.WriteLine("No temp file found. Creating new one.");
                WriteDetailsToFile(oldDetailsJson);
            }

        }
        catch (HttpRequestException e)
        {
            Console.WriteLine($"Request error: {e.Message}");
            await GenericPush(e.Message);
        }
        catch (Exception e)
        {
            Console.WriteLine($"Exception: {e.Message}");
            await GenericPush(e.Message);
        }

        if (oldData != null )
        {
            return (oldData);
        }

        return (new GameDetails());
    }
    
    private static async Task<GameDetails> GetGameDetails(int gameId)
    {
        string apiUrl = $"https://statsapi.mlb.com/api/v1.1/game/{gameId}/feed/live";
        GameDetails gameDetails = new GameDetails();
        try
        {
            using HttpClient client = new HttpClient();
            string json = await client.GetStringAsync(apiUrl);
            string oldJson = String.Empty;
            Console.WriteLine("Recieved game details JSON successfully");
            gameDetails = JsonSerializer.Deserialize<GameDetails>(json);
        }
        catch (Exception e)
        {
            Console.WriteLine(e.Message);
        }
        
        
        return gameDetails;
    }

    private static void WriteDetailsToFile(string details)
    {
        string tempDetailsFilePath = Path.Combine(Path.GetTempPath(), "philliesgamedetails.json");
        
        if (!File.Exists(tempDetailsFilePath))
        {
            using (File.Create(tempDetailsFilePath)) { }
        }
        
        File.WriteAllText(tempDetailsFilePath, details);
    }
    
    private static void WriteScoreToFile(string score)
    {
        string tempScoreFilePath = Path.Combine(Path.GetTempPath(), "philliesgamescore.json");

        if (!File.Exists(tempScoreFilePath))
        {
            using (File.Create(tempScoreFilePath)) { }
        }
        
        File.WriteAllText(tempScoreFilePath, score);
    }

    private static async Task<(GameRoot oldGameData, GameRoot currentGameData)> GetGameDataAsync()
    {
        string teamId = "143";
        string tempFilePath = Path.Combine(Path.GetTempPath(), "philliesgamescore.json");
        string tempDetailsFilePath = Path.Combine(Path.GetTempPath(), "philliesgamedetails.json");
        string todayDate = DateTime.Today.ToString("yyyy-MM-dd");
        string apiUrl = $"https://statsapi.mlb.com/api/v1/schedule?sportId=1&teamId={teamId}&date={todayDate}";
        Console.WriteLine(apiUrl);
        GameRoot? oldData = new GameRoot();
        GameRoot? currentData = new GameRoot();

        try
        {
            using HttpClient client = new HttpClient();
            string json = await client.GetStringAsync(apiUrl);
            string oldDetailsJson = String.Empty;
            string oldJson = String.Empty;
            Console.WriteLine("Recieved JSON successfully");
            currentData = JsonSerializer.Deserialize<GameRoot>(json);

            if (File.Exists(tempFilePath))
            {
                oldJson = await File.ReadAllTextAsync(tempFilePath);
                if (string.IsNullOrWhiteSpace(oldJson))
                {
                    Console.WriteLine("Temp file is empty.");
                    WriteScoreToFile(json);
                    oldJson = json;
                }

                // temp json file has been found and is not empty.
                oldData = JsonSerializer.Deserialize<GameRoot>(oldJson);
            }
            else
            {
                Console.WriteLine("No temp file found. Creating new one.");
                WriteScoreToFile(json);
                oldJson = json;
            }

        }
        catch (HttpRequestException e)
        {
            Console.WriteLine($"Request error: {e.Message}");
            await GenericPush(e.Message);
        }
        catch (Exception e)
        {
            Console.WriteLine($"Exception: {e.Message}");
            await GenericPush(e.Message);
        }

        if (oldData != null && currentData != null)
        {
            return (oldData, currentData);
        }
        else if (oldData == null)
        {
            return (oldGameData: currentData, currentData);
        }

        return (new GameRoot(), new GameRoot());
    }
    
    static async Task GetCurrentScoreAsync(Game currentGame, int oldHomeScore, int oldAwayScore, GameDetails gameDetails)
    {
        // if game is in preview and not "started"
        if (currentGame.Status.DetailedState == "Preview") return;
        
        // Get scores
        int homeScore = currentGame.Teams.Home.Score;
        int awayScore = currentGame.Teams.Away.Score;

        string homeTeam = currentGame.Teams.Home.Team.Name;
        string awayTeam = currentGame.Teams.Away.Team.Name;
        string gameState = currentGame.Status.AbstractGameState;

        string message = String.Empty;

        Console.WriteLine($"{homeTeam}: {homeScore} vs {awayTeam}: {awayScore}");

        if (homeScore != oldHomeScore || awayScore != oldAwayScore)
        {
            foreach (var play in gameDetails.LiveData.Plays.AllPlays)
            {
                if (play.Result.Rbi > 0)
                {
                    // play resulted in a run
                    if ((homeScore == play.Result.HomeScore || awayScore == play.Result.AwayScore))
                    {
                        // if the result of the play equals the new score
                        message =
                            $"Score changed: {homeTeam}: {homeScore} vs {awayTeam}: {awayScore} from {play.Result.Description}";
                        Console.WriteLine($"Score changed: {oldHomeScore} vs {oldAwayScore} to {homeScore} vs {awayScore} from {play.Result.Description}");
                    }
                }
            }
            // send push notification
            await GenericPush(message, "Score Update!");
        }
    }

    private static async Task GenericPush(string message)
    {
        var notifier = new PushoverNotifier();
        await notifier.SendPushNotificationAsync(message, "Update");
    }
    
    private static async Task GenericPush(string message, string title)
    {
        var notifier = new PushoverNotifier();
        await notifier.SendPushNotificationAsync(message, title: title);
    }
}
public class PushoverNotifier
{
    private static readonly HttpClient client = new HttpClient();

    public async Task SendPushNotificationAsync(string message, string title)
    {
        string apiToken = "";
        string userKey = "";
        var values = new Dictionary<string, string>
        {
            { "token", apiToken },
            { "user", userKey },
            { "message", message },
            { "title", title }
        };

        var content = new FormUrlEncodedContent(values);

        try
        {
            HttpResponseMessage response = await client.PostAsync("https://api.pushover.net/1/messages.json", content);
            response.EnsureSuccessStatusCode();

            string responseBody = await response.Content.ReadAsStringAsync();
            Console.WriteLine("Pushover response: " + responseBody);
        }
        catch (Exception e)
        {
            Console.WriteLine("Error sending push notification: " + e.Message);
        }
    }
}

