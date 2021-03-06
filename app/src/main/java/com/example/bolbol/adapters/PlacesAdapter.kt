package com.example.bolbol.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bolbol.R
import com.example.bolbol.apiclasses.Utility
import com.example.bolbol.appcontrolactivity.OrderActivity
import com.example.bolbol.models.Places
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main_navigation.view.*
import kotlinx.android.synthetic.main.row_places.view.*


class PlacesAdapter (var context: Context, var data: ArrayList<Places>):
    RecyclerView.Adapter<PlacesAdapter.MyViewHolder>() {

    //private var selectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.row_places, parent, false)
        return MyViewHolder(root).listen{position, type ->
            val item = data[position]
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PlacesAdapter.MyViewHolder, position: Int) {
        Picasso.get()
            .load(data[position].full_path_logo)
            .into(holder.full_path_logo)

        holder.namePlace.text = data[position].name
        holder.descriptionPlace.text= data[position].description

        holder.linear_place_row.tag = ""+position

//        holder.linear_place_row.setOnClickListener(View.OnClickListener { view ->
//
//            val pos = view.tag.toString().toInt()
//            selectedPos = pos
//        })
//
//        if (selectedPos == position) {
////            val preferences = context.getSharedPreferences("place_id", AppCompatActivity.MODE_PRIVATE)
////            val id_place = data[position].id
////            val editor= preferences.edit()
////            editor.putInt("id_place",id_place)
////            editor.apply()
////            //var intent = Intent(context!!,OrderActivity::class.java)
//            //context.startActivity(intent)
//
//            Toast.makeText(context,"hsa",Toast.LENGTH_SHORT).show()
//            Utility.startNewActivity(context,OrderActivity::class.java)
//
//        }else{
//            selectedPos=-1
//        }

    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
            Toast.makeText(context,"hsa",Toast.LENGTH_SHORT).show()
            Utility.startNewActivity(context,OrderActivity::class.java)
        }
        return this
    }

    class MyViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        val full_path_logo = itemView.place_logo
        val namePlace= itemView.name_place
        val descriptionPlace = itemView.description_place

        val linear_place_row= itemView.linear_place_row


    }

//
//    class ViewHolder(itemView: View, onNoteListener: RecyclerViewOnItemClick) :
//        RecyclerView.ViewHolder(itemView), View.OnClickListener {
//        var category_type = itemView.category_type
//        var linear_row = itemView.linear_row
//
//        var onNoteListener: RecyclerViewOnItemClick
//        override fun onClick(view: View) {
//            onNoteListener.onItemClick(adapterPosition)
//        }
//
//        init {
//            this.onNoteListener = onNoteListener
//            itemView.setOnClickListener(this)
//        }
//
//
//    }


}
