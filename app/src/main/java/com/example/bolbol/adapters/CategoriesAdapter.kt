package com.example.bolbol.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bolbol.R
import com.example.bolbol.apiclasses.ApiControl
import com.example.bolbol.models.Category
import kotlinx.android.synthetic.main.fragment_main_navigation.view.*
import kotlinx.android.synthetic.main.row_all_categories.view.*

class CategoriesAdapter (var context: Context, var data: ArrayList<Category> , var root : View):
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    private var selectedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.MyViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.row_all_categories, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.category_type.text = data[position].name
        holder.linear_row.tag = ""+position

        holder.linear_row.setOnClickListener(View.OnClickListener { view ->
            val pos = view.tag.toString().toInt()
            selectedPos = pos
            notifyDataSetChanged()
        })

        if (selectedPos == position) {
            holder.category_type.setTextColor(Color.parseColor("#007AFF"))
            ApiControl.getInstance(context!!).getAllPlaces(root,data[position].id)
            root.rc_category_places.layoutManager = LinearLayoutManager(context)

        } else {
            holder.category_type.setTextColor(Color.parseColor("#555555"))
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_type= itemView.category_type
        val linear_row= itemView.linear_row
    }


    fun setSelectedPos(selectedPos: Int) {
        this.selectedPos = selectedPos
        notifyDataSetChanged()
    }

}