package com.example.bolbol.userfragments



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.bolbol.apiclasses.MySingleton
import com.example.bolbol.apiclasses.Utility
import com.example.bolbol.appcontrolactivity.AppControlActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.json.JSONException
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(com.example.bolbol.R.layout.fragment_login, container, false)


        var mobileNumLogin:String= root.log_in_user_name.getText().toString()
        var passwordLogin:String= root.log_in_password.getText().toString()

        //val activity = activity as AppCompatActivity?
        //root.toolbar.setTitle("تسجيل دخول")
       // activity!!.setSupportActionBar(root.toolbar)


        //activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        root.log_in_button_sing_up.setOnClickListener(){
            replaceFragment(SingupFragment())
        }

        root.log_in_button_login.setOnClickListener(){
            //val intent = Intent(activity!!, AppControlActivity::class.java)
            //startActivity(intent)
            //activity!!.finish()
            login()

        }

        root.log_in_forget_password.setOnClickListener(){
            replaceFragment(RestorePasswordFragment())
        }

        return root
    }

     fun replaceFragment(fragment:Fragment){
        activity!!.supportFragmentManager.beginTransaction().replace(com.example.bolbol.R.id.loginContainer,
            fragment).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .addToBackStack(null).commit()
    }

    fun replaceFragmentWithBackStack(fragment:Fragment){
        activity!!.supportFragmentManager.beginTransaction().replace(com.example.bolbol.R.id.loginContainer,
            fragment).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .commit()
    }

    private fun login(){

        var mobileNumLogin = log_in_user_name.text.toString()
        var passwordLogin = log_in_password.text.toString()

        if (mobileNumLogin.isEmpty()){
            Toast.makeText(context!!, "empty mobile", Toast.LENGTH_LONG).show()
        }else if (passwordLogin.isEmpty()){
            Toast.makeText(context!!, "empty pass", Toast.LENGTH_LONG).show()
        }else{
            val data = JSONObject()
            try {
                data.put("mobile",mobileNumLogin)
                data.put("password",passwordLogin)
            }catch (e: JSONException){
                e.printStackTrace()
            }

            val urlLogin = "http://bolbol.effect.ps/api/v1/customers/login"
            var tokn=""
            if (Utility.isOnline(context!!)){
                val jsonObjectRequest= JsonObjectRequest(Request.Method.POST,urlLogin,data,
                    Response.Listener {response ->
                        try
                        {
                            if (response.getBoolean("status")) {
                                Log.d("Tag",response.toString())
                                val array = response.getJSONObject("data")
                                var token = array.getString("token")

                                val sharedPreferences= activity!!.getSharedPreferences("isLogin", Context.MODE_PRIVATE)
                                val editorLogin= sharedPreferences.edit()
                                editorLogin.putString("token",token)
                                editorLogin.apply()

                                val intent = Intent(activity!!, AppControlActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                activity!!.finish()

                                Toast.makeText(context!!, response.getString("message"), Toast.LENGTH_SHORT).show()


                            } else {
                                Toast.makeText(context!!, response.getString("message"), Toast.LENGTH_SHORT).show()
                            }

                        } catch (e: JSONException)
                        {
                            e.printStackTrace()
                        }

                    } ,Response.ErrorListener {
                        Toast.makeText(context!!, "nologn", Toast.LENGTH_LONG).show()
                    }
                )

                MySingleton.getInstance(context!!).addToRequestQueue(jsonObjectRequest)
            }else{
                Toast.makeText(context, "no_connection", Toast.LENGTH_LONG).show()
            }


        }




    }


}


