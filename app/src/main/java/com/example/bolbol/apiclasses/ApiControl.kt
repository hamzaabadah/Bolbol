package com.example.bolbol.apiclasses

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.bolbol.adapters.CategoriesAdapter
import com.example.bolbol.adapters.PlacesAdapter
import com.example.bolbol.models.Category
import com.example.bolbol.models.Places
import kotlinx.android.synthetic.main.fragment_main_navigation.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ApiControl(var context: Context){

    companion object {
        @Volatile
        private var INSTANCE: ApiControl? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ApiControl(context).also {
                    INSTANCE = it
                }
            }
    }

     fun getAllCategories(root: View){
        val urlAllCategories = "http://bolbol.effect.ps/api/v1/categories"

        val getAllCategoriesJsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, urlAllCategories, null,
            Response.Listener { responce->
                if (responce.getBoolean("status")){

                    var data = ArrayList<Category>()

                    val array = responce.getJSONArray("data")
                    for (i in 0 until array.length()) {
                        var arrayIn= array.getJSONObject(i)
                        var name = arrayIn.getString("name")
                        var id = arrayIn.getInt("id")
                        var category = Category(id,name)
                        data.add(category)
                        Log.d("Tag", name)

                    }
                    Log.d("Tag", data.size.toString())
                    val categoriesAdapter = CategoriesAdapter(context!!, data, root)
                    root.rc_category.adapter = categoriesAdapter

                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                Log.d("Tag", error.message.toString())

                Toast.makeText(context!!, error.message.toString(), Toast.LENGTH_LONG).show()
            })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                val lang = Locale.getDefault().language
                Log.d("Tag",lang.toString())

                headers["Accept"]="application/json"
                headers["Accept-Language"]=lang
                Log.d("Tag",headers.toString())
                return headers
            }
        }

        MySingleton.getInstance(context!!).addToRequestQueue(getAllCategoriesJsonObjectRequest)

    }

    fun getAllPlaces(root: View, categoryid : Int){
        val urlAllPlaces = "http://bolbol.effect.ps/api/v1/places"

        val getAllPlacesJsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, urlAllPlaces, null,
            Response.Listener { responce->
                if (responce.getBoolean("status")){

                    var data = ArrayList<Places>()

                    val arrayAllPlaces = responce.getJSONObject("data")
                    for (i in 0 until arrayAllPlaces.length()) {
                        var arrayCustomPlace= arrayAllPlaces.getJSONArray("places")

                        for (j in 0 until arrayCustomPlace.length()){
                            var placeInfo= arrayCustomPlace.getJSONObject(j)
                            var category_id = placeInfo.getInt("category_id")
                            if (category_id== categoryid){
                                var idPlace = placeInfo.getInt("id")
                                var logoOfPlace = placeInfo.getString("full_path_logo")
                                var nameOfPlace = placeInfo.getString("name")
                                var descriptionOfPlace = placeInfo.getString("description")

                                var place = Places(idPlace,category_id,logoOfPlace,nameOfPlace,descriptionOfPlace)
                                data.add(place)
                            }
                        }

                    }
                    Log.d("Tag place", data.size.toString())
                    val placesAdapter = PlacesAdapter(context!!, data)
                    root.rc_category_places.adapter = placesAdapter

                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                Log.d("Tag AllPlaces", error.message.toString())

                Toast.makeText(context!!, error.message.toString(), Toast.LENGTH_LONG).show()
            })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                val lang = Locale.getDefault().language
                Log.d("Tag",lang.toString())

                headers["Accept"]="application/json"
                headers["Accept-Language"]=lang
                Log.d("Tag AllPlaces",headers.toString())
                return headers
            }
        }

        MySingleton.getInstance(context!!).addToRequestQueue(getAllPlacesJsonObjectRequest)
    }


    fun getPlaceInfo(root: View, placeid : Int){
        val urlAllPlaces = "http://bolbol.effect.ps/api/v1/places/$placeid"

        val getAllPlacesJsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, urlAllPlaces, null,
            Response.Listener { responce->
                if (responce.getBoolean("status")){

                    val array = responce.getJSONObject("data")
                    for (i in 0 until array.length()){
                        var full_path_logo = array.getString("full_path_logo")
                        var full_path_cover = array.getString("full_path_cover")
                        var city = array.getJSONObject("city")
                        for (j in 0 until city.length()){
                            var name = city.getString("name")
                        }
                    }

                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(context!!, responce.getString("message"), Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                Log.d("Tag infoPlaces", error.message.toString())

                Toast.makeText(context!!, error.message.toString(), Toast.LENGTH_LONG).show()
            })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                val lang = Locale.getDefault().language
                Log.d("Tag",lang.toString())

                headers["Accept"]="application/json"
                headers["Accept-Language"]=lang
                Log.d("Tag infoPlaces",headers.toString())
                return headers
            }
        }

        MySingleton.getInstance(context!!).addToRequestQueue(getAllPlacesJsonObjectRequest)
    }



}



