package com.example.appseverday7

import android.content.Context

class MyPreferecen(context: Context) {
    val PREFERNCE_NAME = "SharedPreference"
    val PREFERNCE_LOGIN_COUNT = "LoginCount"
    val preferecen = context.getSharedPreferences(PREFERNCE_NAME,Context.MODE_PRIVATE)
    fun getLoginCount():Int{
        return preferecen.getInt(PREFERNCE_LOGIN_COUNT,0)
    }
    fun setLoginCount(count : Int){
        val editor = preferecen.edit()
        editor.putInt(PREFERNCE_LOGIN_COUNT,count)
        editor.apply()
    }
}