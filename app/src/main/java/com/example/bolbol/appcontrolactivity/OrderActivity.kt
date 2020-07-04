package com.example.bolbol.appcontrolactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bolbol.R
import com.example.bolbol.orderfragments.NewOrderFragment

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        replaceFragment(NewOrderFragment())
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.orderContainer,
            fragment).commit()
    }
}
