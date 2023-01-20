package com.dmrcmetro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmrcmetro.R
import com.dmrcmetro.models.Stations
import com.dmrcmetro.utils.OnClickListener

class StationsAdapter( private var userList: ArrayList<Stations>) : RecyclerView.Adapter<StationsAdapter.ViewHolder>() {
    private var listener: OnClickListener? = null

    fun setListener(onClickListener: OnClickListener) {
        this.listener = onClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
        //Custom OnClickListener Event
        holder.itemView.setOnClickListener(View.OnClickListener {
            if (listener != null) {
                listener!!.onClickEvent(userList[position])
            }
        })
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: Stations) {
            val tvName = itemView.findViewById<TextView>(R.id.tvName)
            tvName.text = user.name;
//            tvAddress.text = user.address
        }
    }

    /*This method will filter the list and here we are passing the filtered data
        and assigning it to the list with notifyDataSetChanged method
    */
    fun filterList(filteredNames: ArrayList<Stations>) {
        this.userList = filteredNames
        notifyDataSetChanged()
    }
}