package com.sportzInteractive.task.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale






class DateFormatUtils {
    companion object {
        const val transactionTime: String = "hh:mm aa d MMM yyyy"
        const val displayDate: String = "dd MMM"

        fun getDisplayDate(stringDate: String?): String {
            var returnDateTime = ""
            try {
                val parseFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val displayFormat = SimpleDateFormat("dd MMM", Locale.US)
                val date = parseFormat.parse(stringDate)
                if (date != null)
                    returnDateTime = displayFormat.format(date)

            } catch (e: ParseException) {

            }
            return returnDateTime
        }

    }
}