package com.example.lxview.lxhome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lxview.R


/**
 * author: 李 祥
 * date:   2022/4/27 4:59 下午
 * description:
 */


class SqliteAdapter(private val context: Context, private val list: List<Map<String, Any>>) : Adapter<ViewHolder>() {


    private var name:TextView?=null
    private var id:TextView?=null
    private var sex:TextView?=null
    private var age:TextView?=null
    
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val convertView = LayoutInflater.from(parent.context).inflate(R.layout.activity_sqllite_item_layout, null)
        return SQLViewHolder(convertView)
    }

    inner class SQLViewHolder(convertView: View): RecyclerView.ViewHolder(convertView) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        name = holder.itemView.findViewById<View>(R.id.item_name) as TextView?
        age = holder.itemView.findViewById<View>(R.id.item_age) as TextView?
        sex = holder.itemView.findViewById<View>(R.id.item_sex) as TextView?
        id = holder.itemView.findViewById<View>(R.id.item_id) as TextView?
        name?.text = list[position]["sname"].toString()
        age?.text = list[position]["sage"].toString()
        sex?.text = list[position]["ssex"].toString()
        id?.text = list[position]["id"].toString()
    }

    fun setVisibility(boolean: Boolean){
        if (boolean){
            age?.visibility = View.VISIBLE
            id?.visibility = View.VISIBLE
            sex?.visibility = View.VISIBLE
        }else {
            age?.visibility = View.GONE
            id?.visibility = View.GONE
            sex?.visibility = View.GONE
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

}
