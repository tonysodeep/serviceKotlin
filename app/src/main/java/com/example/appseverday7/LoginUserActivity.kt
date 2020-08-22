package com.example.appseverday7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_user.*

class LoginUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        var helpers = UserLoginDatabase(applicationContext)
        var db = helpers.readableDatabase
        bt_login.setOnClickListener {
            var args = listOf<String>(et_account.text.toString(),et_password.text.toString()).toTypedArray()
            var rs = db.rawQuery("SELECT Account_Name,Password FROM User_Login WHERE Account_Name = ? AND Password = ?",args)
            if(rs.moveToNext()){
                val myPreferecen = MyPreferecen(this)
                val intent = Intent(this,MainActivity::class.java)
                myPreferecen.setLoginCount(1)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(applicationContext,"Login fail",Toast.LENGTH_LONG).show()
            }

        }
        tv_sign_up.setOnClickListener {
            val intent = Intent(this@LoginUserActivity,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}