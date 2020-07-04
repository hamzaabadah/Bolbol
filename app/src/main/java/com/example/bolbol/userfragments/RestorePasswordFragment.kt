package com.example.bolbol.userfragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bolbol.R
import kotlinx.android.synthetic.main.fragment_restore_password.view.*

/**
 * A simple [Fragment] subclass.
 */
class RestorePasswordFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val root= inflater.inflate(R.layout.fragment_restore_password, container, false)

        root.send_appointment_code.setOnClickListener(){
            replaceFragment(ChangePasswordFragment())
        }

        return root
    }

    fun replaceFragment(fragment:Fragment){
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.loginContainer,
            fragment).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .commit()
    }


}
