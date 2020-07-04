package com.example.bolbol.userfragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bolbol.R

/**
 * A simple [Fragment] subclass.
 */
class ChangePasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_change_password, container, false)

        return root
    }

    fun replaceFragment(fragment:Fragment){
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.loginContainer,
            fragment).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .addToBackStack(null).commit()
    }


}
