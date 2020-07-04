package com.example.bolbol.userfragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

import com.example.bolbol.R
import com.example.bolbol.apiclasses.MySingleton
import com.example.bolbol.appcontrolactivity.AppControlActivity
import com.example.bolbol.models.Customer
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.conent_singup_scrolling.view.*
import org.json.JSONException
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class SingupFragment : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_singup, container, false)

        //var name:String?,var phoneNumber: Int, var email: String?,
        //                    var password:String? , var confirmPassword:String? ,
        //                    var cityid:Int

        root.sing_up_button_singup.setOnClickListener(){

            var nameCustomer = root.sing_up_full_name.text.toString()
            var passwordCustomer= root.sing_up_password.text.toString()
            var phoneNumberCustomer= root.sing_up_phone.text.toString()
            var emailCustomer = root.sing_up_email.text.toString()
            var cityid= root.sing_up_city.text.toString()

            if (nameCustomer.isEmpty())
                    Toast.makeText(context!!, "name is empty ", Toast.LENGTH_LONG).show()
                else if (passwordCustomer.isEmpty())
                    Toast.makeText(context!!, "pass is empty ", Toast.LENGTH_LONG).show()
                else if (phoneNumberCustomer.isEmpty())
                    Toast.makeText(context!!, "phone is empty ", Toast.LENGTH_LONG).show()
                else if (emailCustomer.isEmpty())
                    Toast.makeText(context!!, "phone is empty ", Toast.LENGTH_LONG).show()
                else if (cityid.isEmpty())
                    Toast.makeText(context!!, "city is empty ", Toast.LENGTH_LONG).show()
                else
                    rigesterCustomer(nameCustomer,phoneNumberCustomer,emailCustomer,passwordCustomer,passwordCustomer,cityid)

        }



        return root
    }

    private fun rigesterCustomer(name:String, phone:String,email:String, password:String, confirmpassword:String, cityid:String){
        val urlRegister= "http://bolbol.effect.ps/api/v1/customers/register"

        val data = JSONObject()
        try {
            data.put("name",name)
            data.put("mobile",phone)
            data.put("email",email)
            data.put("password",password)
            data.put("password_confirmation",confirmpassword)
            data.put("city_id",cityid)
        }catch (e: JSONException){
            e.printStackTrace()
        }

        val jsonObjectRequest= JsonObjectRequest(Request.Method.POST,urlRegister,data,
            Response.Listener { response ->
                try
                {
                    if (response.getBoolean("status")) {
                        Toast.makeText(context!!, response.getString("message"), Toast.LENGTH_LONG).show()


                    } else {
                        Toast.makeText(context!!, response.getString("message"), Toast.LENGTH_LONG).show()
                    }

                } catch (e: JSONException)
                {
                    e.printStackTrace()
                }

            } , Response.ErrorListener {
                Toast.makeText(context!!, "no register", Toast.LENGTH_LONG).show()
            }
        )

        MySingleton.getInstance(context!!).addToRequestQueue(jsonObjectRequest)
    }

}
