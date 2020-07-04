package com.example.bolbol.accountfragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.bolbol.R
import kotlinx.android.synthetic.main.fragment_who_us_terms_conditions.view.*

/**
 * A simple [Fragment] subclass.
 */
class WhoUsTermsConditionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_who_us_terms_conditions, container, false)

        val sharedPref = activity!!.getSharedPreferences("open_profile_control_act", AppCompatActivity.MODE_PRIVATE)
        val editprofile = sharedPref.getInt("replace_fragment",-1)

        if (editprofile==4){
            root.who_us_terms_title.setText("Who Us")
            root.who_us_terms_text.setText("who_us")
        }else if (editprofile==5){
            root.who_us_terms_title.setText("Terms and Condition")
            root.who_us_terms_text.setText("Terms and Condition")
        }

        root.who_us_terms_back.setOnClickListener(){
            activity!!.finish()
        }

        return root
    }


}
