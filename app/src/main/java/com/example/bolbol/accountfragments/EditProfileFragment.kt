package com.example.bolbol.accountfragments


import android.Manifest
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import android.view.WindowManager

import android.view.Gravity



/**
 * A simple [Fragment] subclass.
 */
class EditProfileFragment : Fragment() {

    var imageURI:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(com.example.bolbol.R.layout.fragment_edit_profile, container, false)

        root.edit_profile_back.setOnClickListener(){
            activity!!.finish()
        }

        root.edit_profile_image.setOnClickListener(){
            val dialog = Dialog(context!!)
            var window= dialog.getWindow()!!
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val wlp = window.getAttributes()

            wlp.gravity = Gravity.BOTTOM
            wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
            window.attributes = wlp

            dialog.setContentView(com.example.bolbol.R.layout.set_profile_image_dialog)
            dialog.show()


        }

        return root
    }




}
