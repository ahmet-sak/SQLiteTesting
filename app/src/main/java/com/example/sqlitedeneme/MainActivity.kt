package com.example.sqlitedeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {
            val myDatabase= this.openOrCreateDatabase("People", MODE_PRIVATE,null)
            myDatabase.execSQL("Create Table If Not Exists students (id INTEGER PRIMARY KEY, name VARCHAR, age INTEGER, studentNo INTEGER)")

            //myDatabase.execSQL("INSERT INTO students (name, age, studentNo) VALUES ('Can', 18, 153)")
            //myDatabase.execSQL("INSERT INTO students (name, age, studentNo) VALUES ('Temel', 18, 154)")

            val cursor= myDatabase.rawQuery("SELECT * FROM students",null)

            val cursorNameRowIndex= cursor.getColumnIndex("name")
            val cursorAgeRowIndex= cursor.getColumnIndex("age")
            val cursorStudentNoRowIndex= cursor.getColumnIndex("studentNo")

            while (cursor.moveToNext()){
                println("Öğrenci adı: ${cursor.getString(cursorNameRowIndex)}")
                println("Öğrenci yaşı: ${cursor.getInt(cursorAgeRowIndex)}")
                println("Öğrenci numarası: ${cursor.getInt(cursorStudentNoRowIndex)}")
            }

            cursor.close()



        }catch (e: Exception){
            println(e.message)
        }


    }
}