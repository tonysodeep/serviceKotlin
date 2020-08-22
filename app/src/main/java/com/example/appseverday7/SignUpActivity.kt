package com.example.appseverday7

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var helpers = UserLoginDatabase(applicationContext)

        bt_sign_up.setOnClickListener {
            var db = helpers.writableDatabase
            var cv = ContentValues()
            cv.apply {
                put("Full_Name",user_full_name.text.toString())
                put("Account_Name",user_acount_name.text.toString())
                put("Password",user_acount_pass.text.toString())
                var result = db.insert("User_Login",null,cv)
                if(result==-1.toLong())
                    Toast.makeText(applicationContext,"Fail",Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
            }
            db.close()
            val intent = Intent(applicationContext,MainActivity::class.java)
            intent.putExtra("userFullName",user_full_name.text.toString())
            startActivity(intent)
        }
    }


}