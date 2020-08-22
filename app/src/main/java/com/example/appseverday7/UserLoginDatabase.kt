package com.example.appseverday7

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class UserLoginDatabase(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserInfo.db"

        //table
        private const val TABLE_NAME = "User_Login"
        private const val COL_ACCOUNT_ID = "id"
        private const val COL_ACCOUNTNAME = "Account_Name"
        private const val COL_FULLNAME = "Full_Name"
        private const val COL_ACCOUNTPASS = "Password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS ${TABLE_NAME} (${COL_ACCOUNT_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${COL_ACCOUNTNAME} TEXT, ${COL_ACCOUNTPASS} TEXT, ${COL_FULLNAME} TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }




}