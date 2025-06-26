package nicholos.tyler.philliesupdater

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.text.format
import java.util.Locale

class DateHelper {
    companion object {
        fun formatIsoDateToDisplayString(isoDateString: String): String? {
            return try {
                // 1. Define a formatter for the input ISO 8601 string
                //    "uuuu" is for year, "MM" for month, "dd" for day,
                //    "'T'" for literal T, "HH" for hour, "mm" for minute, "ss" for second,
                //    "X" for zone offset (Z means UTC or +00:00)
                //    Using OffsetDateTime because the input string has 'Z' (UTC offset)
                val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
                val offsetDateTime = OffsetDateTime.parse(isoDateString, inputFormatter)

                // 2. Define a formatter for the desired output string
                //    "MMMM" for full month name (e.g., "May")
                //    "dd" for day of the month (e.g., "28")
                //    "uuuu" for year (e.g., "2025")
                //    Using Locale.ENGLISH to ensure month name is in English,
                //    you can change this to Locale.getDefault() or other locales.
                val outputFormatter = DateTimeFormatter.ofPattern(
                    "MMMM dd uuuu",
                    Locale.ENGLISH
                )

                // 3. Format the OffsetDateTime object to the desired string
                offsetDateTime.format(outputFormatter)
            } catch (e: Exception) {
                // Handle potential parsing exceptions (e.g., if the input string is not valid)
                e.printStackTrace() // Log the error
                null // Or return a default string like "Invalid Date"
            }
        }

        fun formatIsoDateToTimeString(isoDateString: String): String? {
            return try {
                val zonedDateTime = ZonedDateTime.parse(isoDateString)
                    .withZoneSameInstant(ZoneId.systemDefault())
                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val time = zonedDateTime.toLocalTime().format(formatter)
                return time.toString()
            } catch (e: Exception) {
                println(e.message)
                return ""
            }
        }
    }
}