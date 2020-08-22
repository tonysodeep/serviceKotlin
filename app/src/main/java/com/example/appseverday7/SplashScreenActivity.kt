package com.example.appseverday7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import java.lang.Exception

class SplashScreenActivity : AppCompatActivity() {
    var loginCount : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            val myPreferecen = MyPreferecen(this)
            loginCount  = myPreferecen.getLoginCount()
            if(loginCount == 0) {
                startLoginActivity()
                Log.d("AAA","login screen")
            } else {
                startProfileActivity()
               Log.d("AAA","user screen")
            }
        },1500)

    }
    fun startLoginActivity() {
        val intent = Intent(this, LoginUserActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun startProfileActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}