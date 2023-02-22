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


class GameTeamListAdapter(private val list: List<Map<String, Any>>) : Adapter<ViewHolder>() {


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ItemView {
        var item_name: TextView? = null
        var item_id: TextView? = null
        var item_age: TextView? = null
        var item_sex: TextView? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val convertView = LayoutInflater.from(parent.context).inflate(R.layout.activity_game_team_item_layout, null)
        return SQLViewHolder(convertView)
    }

    inner class SQLViewHolder(convertView: View): RecyclerView.ViewHolder(convertView) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = ItemView()
        items.item_name = holder.itemView.findViewById<View>(R.id.item_name) as TextView?
        items.item_age = holder.itemView.findViewById<View>(R.id.item_age) as TextView?
        items.item_sex = holder.itemView.findViewById<View>(R.id.item_sex) as TextView?
        items.item_id = holder.itemView.findViewById<View>(R.id.item_id) as TextView?
        items.item_name!!.text = list[position]["sname"].toString()
        items.item_age!!.text = list[position]["sage"].toString()
        items.item_sex!!.text = list[position]["ssex"].toString()
        items.item_id!!.text = list[position]["id"].toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
