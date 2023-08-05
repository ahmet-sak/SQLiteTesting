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

           // myDatabase.execSQL("INSERT INTO students (name, age, studentNo) VALUES ('Can', 18, 153)")
           // myDatabase.execSQL("INSERT INTO students (name, age, studentNo) VALUES ('Temel', 19, 154)")
           // myDatabase.execSQL("INSERT INTO students (name, age, studentNo) VALUES ('Jesica', 19, 159)")
           // myDatabase.execSQL("INSERT INTO students (name, age, studentNo) VALUES ('Julia', 18, 162)")

            //val cursor= myDatabase.rawQuery("SELECT * FROM students WHERE age= 18",null)
            //val cursor= myDatabase.rawQuery("SELECT * FROM students WHERE name LIKE 'J%'",null)
            //val cursor= myDatabase.rawQuery("SELECT * FROM students WHERE name LIKE '%n' ",null)
            //val cursor= myDatabase.rawQuery("SELECT * FROM students WHERE studentNo>153 AND studentNo<160 ",null)

            myDatabase.execSQL("UPDATE students SET name = 'John Wick' WHERE name = 'Temel'")
            myDatabase.execSQL("UPDATE students SET studentNo = 160 WHERE name = 'Julia'")

            myDatabase.execSQL("DELETE FROM students WHERE id=5")

            val cursor= myDatabase.rawQuery("SELECT * FROM students ",null)


            val cursorNameRowIndex= cursor.getColumnIndex("name")
            val cursorAgeRowIndex= cursor.getColumnIndex("age")
            val cursorStudentNoRowIndex= cursor.getColumnIndex("studentNo")
            val cursorIdRowIndex= cursor.getColumnIndex("id")


            while (cursor.moveToNext()){
                println("Öğrenci id: ${cursor.getInt(cursorIdRowIndex)} adı: ${cursor.getString(cursorNameRowIndex)}  " +
                        "yaşı: ${cursor.getInt(cursorAgeRowIndex)}, numarası: ${cursor.getInt(cursorStudentNoRowIndex)}")
            }

            cursor.close()

        }catch (e: Exception){
            println(e.message)
        }


    }
}