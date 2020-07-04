package com.example.bolbol.appcontrolactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.bolbol.MainActivity
import com.example.bolbol.R
import kotlin.math.log

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            // Check if user logged in before or not
            val preferences = getSharedPreferences("isLogin", MODE_PRIVATE)
            var logged_in= preferences.getString("token","")

            if (logged_in?.isNotEmpty()!!) {
                val AppControlIntent = Intent(this@SplashActivity, AppControlActivity::class.java)
                AppControlIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(AppControlIntent)
                finish()

            }else{
                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(mainIntent)
                finish()
            }



        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
