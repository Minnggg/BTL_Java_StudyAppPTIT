package com.example.btl_java_studyapp.model

import java.text.SimpleDateFormat
import java.util.*
class  CalendarDateModel(var data: Date, var isSelected: Boolean = true) {
    val calendarDay: String
        get() = SimpleDateFormat("EE", Locale.getDefault()).format(data)

    val calendarYear: String
        get() = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(data)

    val calendarDate: String
        get() {
            val cal = Calendar.getInstance()
            cal.time = data
            return cal[Calendar.DAY_OF_MONTH].toString()
        }
}