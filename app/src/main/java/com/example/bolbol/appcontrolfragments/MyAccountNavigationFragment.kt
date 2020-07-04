package com.example.bolbol.appcontrolfragments


import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bolbol.MainActivity


import com.example.bolbol.appcontrolactivity.ProfileControlActivity
import kotlinx.android.synthetic.main.fragment_my_account_navigation.view.*

import com.android.volley.AuthFailureError
import java.util.*
import kotlin.collections.HashMap
import android.util.Log
import com.example.bolbol.appcontrolactivity.AppControlActivity
import kotlinx.android.synthetic.main.set_profile_image_dialog.*


/**
 * A simple [Fragment] subclass.
 */
class MyAccountNavigationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(com.example.bolbol.R.layout.fragment_my_account_navigation, container, false)

        val intent = Intent(activity!!, ProfileControlActivity::class.java)
        val preferences = activity!!.getSharedPreferences("open_profile_control_act", Context.MODE_PRIVATE)
        val editor = preferences.edit()


        root.my_account_edit_profile.setOnClickListener(){
            editor.putInt("replace_fragment",1)
            editor.apply()
            startActivity(intent)
        }

        root.my_account_change_pass.setOnClickListener(){
            editor.putInt("replace_fragment",2)
            editor.apply()
            startActivity(intent)
        }

        root.my_account_contact_with_us.setOnClickListener(){
            editor.putInt("replace_fragment",3)
            editor.apply()
            startActivity(intent)
        }

        root.my_account_who_are_we.setOnClickListener(){
            editor.putInt("replace_fragment",4)
            editor.apply()
            startActivity(intent)
        }

        root.my_account_terms_condition.setOnClickListener(){
            editor.putInt("replace_fragment",5)
            editor.apply()
            startActivity(intent)
        }
        /*
        root.my_account_logout.setOnClickListener(){
            logout()
        }
        */
        root.my_account_logout.setOnClickListener(){
            val dialog = Dialog(context!!)

            var window= dialog.getWindow()!!
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val wlp = window.getAttributes()

            wlp.gravity = Gravity.BOTTOM
            wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
            window.attributes = wlp

            dialog.setContentView(com.example.bolbol.R.layout.set_profile_image_dialog)
            dialog.photo_in_gallery.setText("Do you want to log out of the app?")
            dialog.photo_in_camera.setText("Yes, log out")
            dialog.photo_in_camera.setTextColor(Color.parseColor("#FF3B30"))
            dialog.show()

            dialog.photo_in_camera.setOnClickListener(){
                logout()
            }
            dialog.liner_gallery_camera.setOnClickListener(){
                dialog.hide()
            }

        }


        return root
    }

    fun replaceFragment(fragment: Fragment){
        activity!!.supportFragmentManager.beginTransaction().replace(com.example.bolbol.R.id.bottom_navigation_container,
            fragment).addToBackStack(null).commit()
    }

    private fun logout(){
        val urlLogout = "http://bolbol.effect.ps/api/v1/customers/logout"

        val logoutJsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, urlLogout, null,
            Response.Listener {responce->
                if (responce.getBoolean("status")){

                    val sharedPreferences= activity!!.getSharedPreferences("isLogin", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.clear().commit()

                    val intent = Intent(activity!!, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    activity!!.finish()
                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener {error ->
                Log.d("Tag", error.message.toString())

                Toast.makeText(context!!, error.message.toString(), Toast.LENGTH_LONG).show()
            })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()

                val preferences = activity!!.getSharedPreferences("isLogin", AppCompatActivity.MODE_PRIVATE)
                var token= preferences.getString("token","")
                val lang = Locale.getDefault().language
                Log.d("Tag",token.toString())
                Log.d("Tag",lang.toString())

                headers["Accept"]="application/json"
                headers["Accept-Language"]=lang
                headers["Authorization"]="Bearer "+token!!
                Log.d("Tag",headers.toString())
                return headers
            }
        }

        val queue = Volley.newRequestQueue(context!!)
        queue.add(logoutJsonObjectRequest)
    }

    fun requestWithSomeHttpHeaders() {

        val url = "http://bolbol.effect.ps/api/v1/customers/logout"
        val getRequest = object : JsonObjectRequest(Request.Method.GET, url,null,
            Response.Listener {
                // response

                val preferences = activity!!.getSharedPreferences("isLogin", AppCompatActivity.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.clear().commit()
                val intent = Intent(activity!!, MainActivity::class.java)
                startActivity(intent)
                activity!!.finish()
            },
            Response.ErrorListener { error ->
                Log.d("Tag", error.toString())
                Toast.makeText(context!!, error.message.toString(), Toast.LENGTH_LONG).show()

            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                val lang = Locale.getDefault().language
                val preferences = activity!!.getSharedPreferences("isLogin", AppCompatActivity.MODE_PRIVATE)
                val token= preferences.getString("token","")
                params["Accept"] = "application/json"
                params["Accept-Language"] = "en"
                params["Authorization"] = token!!
                return params
            }
        }
        val queue = Volley.newRequestQueue(context)
        queue.add(getRequest)

    }




}
