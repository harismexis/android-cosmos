package com.harismexis.cosmos.workshared.util.datepicker

import android.app.DatePickerDialog
import android.content.Context
import com.harismexis.cosmos.workshared.util.functional.Action3
import com.harismexis.cosmos.workshared.util.general.getCalendarFromDateOrCurrent
import java.util.*

fun showDatePicker(
    context: Context,
    date: String,
    onSelected: Action3<Int, Int, Int>
) {
    val calendar = getCalendarFromDateOrCurrent(date)
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val picker = DatePickerDialog(
        context,
        { _, yearOf, monthOfYear, dayOfMonth ->
            onSelected.call(yearOf, monthOfYear, dayOfMonth)
        },
        year,
        month,
        day
    )
    picker.datePicker.maxDate = Calendar.getInstance().timeInMillis
    picker.show()
}