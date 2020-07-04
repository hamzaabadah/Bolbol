package com.example.bolbol.orderfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.bolbol.R
import kotlinx.android.synthetic.main.fragment_new_order.view.*

/**
 * A simple [Fragment] subclass.
 */
class NewOrderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_new_order, container, false)

        val preferences = activity!!.getSharedPreferences("place_id", AppCompatActivity.MODE_PRIVATE)
        var id_place= preferences.getInt("id_place",0)


        return root
    }

}
