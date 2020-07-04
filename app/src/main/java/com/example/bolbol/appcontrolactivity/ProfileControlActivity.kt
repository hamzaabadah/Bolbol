package com.example.bolbol.appcontrolactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bolbol.R
import com.example.bolbol.accountfragments.ChangePassProfileFragment
import com.example.bolbol.accountfragments.ConnectWithUsFragment
import com.example.bolbol.accountfragments.EditProfileFragment
import com.example.bolbol.accountfragments.WhoUsTermsConditionsFragment

class ProfileControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_control)

        val sharedPref = getSharedPreferences("open_profile_control_act", MODE_PRIVATE)
        val editprofile = sharedPref.getInt("replace_fragment",-1)


        if (editprofile==1){
            replaceFragment(EditProfileFragment())

        }else if (editprofile==2){
            replaceFragment(ChangePassProfileFragment())
        }else if(editprofile==3){
            replaceFragment(ConnectWithUsFragment())
        }else if (editprofile==4){
            replaceFragment(WhoUsTermsConditionsFragment())
        }else {
            replaceFragment(WhoUsTermsConditionsFragment())
        }



    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.profile_activity_container,
            fragment).commit()
    }
}
