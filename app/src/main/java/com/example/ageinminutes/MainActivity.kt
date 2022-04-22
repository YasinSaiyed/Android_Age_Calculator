package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

            //Printing the selected date
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)

            //calculating the age
                var totalYears = year - selectedYear
                var totalMonths = month - selectedMonth
                totalYears = abs(totalYears)
                totalMonths = abs(totalMonths)
                totalAge.setText("$totalYears Years $totalMonths Months")

            //Date format and calculating the minutes
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateToMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            }, year,
            month,
            day).show()
    }
}

