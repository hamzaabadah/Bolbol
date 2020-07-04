package com.example.bolbol.apiclasses

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.bolbol.R

object Utility {
    // Check if connected to internet
    fun isOnline(context: Context): Boolean {
        val connMgr =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun startNewActivity(context: Context, clazz: Class<*>) {
        val intent = Intent(context, clazz)
        context.startActivity(intent)
    }

}