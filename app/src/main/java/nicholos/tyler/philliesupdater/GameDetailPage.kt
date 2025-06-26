package nicholos.tyler.philliesupdater

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class GameDetailPage {
    @Composable
    fun GameDetailScreen(modifier: Modifier, plays: List<Play>, game: Game) {
        GameDetailList(modifier, plays)
    }

    @Composable
    fun GameDetailList(modifier: Modifier, plays: List<Play>) {
        if (plays.isNullOrEmpty()) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Game has not happened yet",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = modifier.height(300.dp),
                //userScrollEnabled = false,
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp) // Padding around the list
            ) {
                items(items = plays!!, key = { it.playEndTime.toString() }) { play ->
                    play.result?.description?.let { Text(text = it) }
                }

            }
        }
    }

}