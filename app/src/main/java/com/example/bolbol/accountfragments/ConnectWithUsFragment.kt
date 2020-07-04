package com.example.bolbol.accountfragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bolbol.R
import kotlinx.android.synthetic.main.fragment_connect_with_us.view.*

/**
 * A simple [Fragment] subclass.
 */
class ConnectWithUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_connect_with_us, container, false)
        root.contact_with_us_back.setOnClickListener(){
            activity!!.finish()
        }
        return root
    }


}
