package com.example.appseverday7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var imgaeFragment: ImageFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "app sever"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.icon_menu)
        }
        val drawerToggle : ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ){
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                setTitle(R.string.app_name)
            }
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setTitle(R.string.option)
            }
        }
        imgaeFragment = ImageFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment,imgaeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val header: View = navigationView.getHeaderView(0)
        val buttonLogout: Button = header.findViewById(R.id.bt_log_out)
        val textViewUserName : TextView = header.findViewById(R.id.user_name)
        val intent = intent
        textViewUserName.text = intent.getStringExtra("userFullName")
        buttonLogout.setOnClickListener {
            val myPreferecen = MyPreferecen(this)
            myPreferecen.setLoginCount(0)
            val intent = Intent(this,LoginUserActivity::class.java)
            startActivity(intent)
        }
    }

}