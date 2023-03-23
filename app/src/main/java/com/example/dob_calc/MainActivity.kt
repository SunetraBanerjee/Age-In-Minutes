package com.example.dob_calc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView?=null
    private var tvageInMinutes: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndatepick : Button = findViewById(R.id.btndatepick)

        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvageInMinutes=findViewById(R.id.tvageInMinutes)


        btndatepick.setOnClickListener {
            clickdatepicker()
        }
    }

    private fun clickdatepicker(){

        val mycal=Calendar.getInstance()
        val year=mycal.get(Calendar.YEAR)
        val month=mycal.get(Calendar.MONTH)
        val day=mycal.get(Calendar.DAY_OF_MONTH)
        val dpd= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,syear,smonth,dayOfMonth->

                Toast.makeText(this, "day:$dayOfMonth month:${smonth+1} year:$syear", Toast.LENGTH_LONG).show()

                val selectedDate="$dayOfMonth/${smonth+1}/$syear"
                tvSelectedDate?.text=selectedDate

                val sdf=SimpleDateFormat("dd/MM/yyyy",
                    Locale.ENGLISH)

                val thedate=sdf.parse(selectedDate)

                val selectedDateinminutes=thedate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateinMinutes=currentDate.time/60000
                val difference = currentDateinMinutes-selectedDateinminutes

                tvageInMinutes?.text=difference.toString()

            },
            year,
            month,
            day
        )


        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()



    }
}