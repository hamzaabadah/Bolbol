package com.example.bolbol.appcontrolactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bolbol.R
import com.example.bolbol.appcontrolfragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_app_control.*


class AppControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_control)

        var i = 0
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (i==0){
            nav_view.selectedItemId =R.id.navigation_main
        }else nav_view.selectedItemId= R.id.navigation_account


    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.navigation_main -> {
                    replaceFragment(MainNavigationFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications-> {
                    replaceFragment(NotificationNavigationFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_requests -> {
                    replaceFragment(RequestsNavigationFragment())
                    return@OnNavigationItemSelectedListener true
                }R.id.navigation_account -> {
                replaceFragment(MyAccountNavigationFragment())
                return@OnNavigationItemSelectedListener true
            }

            }
            false
        }

    fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.bottom_navigation_container,
            fragment).commit()
    }

}
