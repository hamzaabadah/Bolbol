package com.example.bolbol.appcontrolfragments


import android.R.array
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.bolbol.R
import com.example.bolbol.adapters.CategoriesAdapter
import com.example.bolbol.apiclasses.ApiControl
import com.example.bolbol.apiclasses.MySingleton
import kotlinx.android.synthetic.main.fragment_main_navigation.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 */
class MainNavigationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_main_navigation, container, false)

        Log.d("tag","tag")
        ApiControl.getInstance(context!!).getAllCategories(root)
        root.rc_category.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        root.rc_category.setHasFixedSize(true)

        //ApiControl.getInstance(context!!).getAllPlaces(root,6)
        //root.rc_category_places.layoutManager = LinearLayoutManager(context)
        return root
    }



}
