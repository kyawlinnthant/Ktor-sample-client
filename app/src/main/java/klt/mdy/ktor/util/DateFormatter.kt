package klt.mdy.ktor.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {
    @SuppressLint("SimpleDateFormat")
    fun convertStringToReadableDate(dateString: String): String {
        return if (Build.VERSION.SDK_INT >= 26) {
            LocalDateTime.parse(
                dateString,
                DateTimeFormatter.ISO_DATE_TIME
            ).format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            )
        } else {
            SimpleDateFormat("dd.MM.yyyy HH:mm")
                .format(
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .parse(dateString)!!
                )
        }
    }

}