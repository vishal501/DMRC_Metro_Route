package com.dmrcmetro.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.dmrcmetro.R
import com.dmrcmetro.databinding.MetroItemsBinding
import com.dmrcmetro.models.MetroModel
import com.dmrcmetro.models.Stations
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MyAdapter
@Inject
constructor(@ActivityContext val context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null
    //    var metroModelList: ArrayList<String> = ArrayList()
//    private var stationList = ArrayList<Pair<String,Int>>()
    private var stationList = ArrayList<Pair<String,Int>>()

    @SuppressLint("NotifyDataSetChanged")
    fun routeList(metroModel: MetroModel) {

        val metroLineColor = mutableListOf<Int>()

        metroModel.line1.forEach {
            addMetroColor(metroLineColor, it)
        }
        if(metroModel.line2.isNotEmpty()){
            addMetroColor(metroLineColor, metroModel.line2[metroModel.line2.size-1])
        }

        var interChangeNumber = 0
        var currentMetroColor = 0
        var nextMetroColor = 1


        metroModel.path.forEach { stationName ->
            if(interChangeNumber >= metroModel.interchange.size){
                stationList.add(Pair(stationName, metroLineColor[currentMetroColor] ))
            }else if(!stationName.equals(metroModel.interchange[interChangeNumber],true)){
                stationList.add(Pair(stationName, metroLineColor[currentMetroColor] ))
            }
            else{
                stationList.add(Pair(stationName +"InterChange", metroLineColor[currentMetroColor]))
//                stationList.add(Pair(stationName +"\n InterChange Metro" , R.color.black ))
//                stationList.add(Pair(stationName, metroLineColor[nextMetroColor] ))
                interChangeNumber++
                currentMetroColor++
                nextMetroColor += currentMetroColor
//                stationList.add(Pair("InterChange Metro", ContextCompat.getColor(context, R.color.white) ))
            }
        }
        notifyDataSetChanged()
    }

    private fun addMetroColor(metroLineColor: MutableList<Int>, it: String) {
        when(it.lowercase()){
            "red" -> { metroLineColor.add(R.drawable.bg_red_circle)}
            "yellow" -> { metroLineColor.add(R.drawable.bg_yellow_circle)}
            "blue" -> { metroLineColor.add( R.drawable.bg_blue_circle)}
            "green" -> { metroLineColor.add( R.drawable.bg_green_circle)}
            "violet" -> { metroLineColor.add(R.drawable.bg_violet_circle)}
            "airport" -> { metroLineColor.add(R.drawable.bg_orange_circle)}
            "orange" -> { metroLineColor.add(R.drawable.bg_orange_circle)}
            "pink" -> { metroLineColor.add( R.drawable.bg_pink_circle)}
            "magenta" -> { metroLineColor.add( R.drawable.bg_magenta_circle)}
            "grey" -> { metroLineColor.add(R.drawable.bg_yellow_circle)}
            "silver" -> { metroLineColor.add( R.drawable.bg_grey_circle)}
            else -> { metroLineColor.add( R.drawable.bg_grey_circle)}
//            "red" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.red) )}
//            "yellow" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.yellow) )}
//            "blue" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.blue) )}
//            "green" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.green) )}
//            "violet" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.violet) )}
//            "airport" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.airport) )}
//            "orange" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.orange) )}
//            "pink" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.pink) )}
//            "magenta" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.magenta) )}
//            "grey" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.grey) )}
//            "silver" -> { metroLineColor.add( ContextCompat.getColor(context, R.color.silver) )}
//            else -> { metroLineColor.add( ContextCompat.getColor(context, R.color.purple_200) )}
        }
    }


    inner class MyViewHolder(val binding: MetroItemsBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(pair: Pair<String, Int>) {
            val txt = pair.first
            if(pair.first != null){
                val str = txt.substring(txt.length -1)
                binding.tvInterchange.text = str
            }

//            binding.tvInterchange.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    filter(charSequence.toString())
//                    productsList.get(getAdapterPosition()).setName(s)
//                    txtsave.setText(charSequence)
//                }
//
//
//
//
//                override fun afterTextChanged(s: Editable?) {
//                    TODO("Not yet implemented")
//                }
//            })

//            if(pair.first.contains("InterChange", false)){
//                binding.tvInterchange.text = "Interchange"
//                Log.e("aane",binding.tvInterchange.text.toString())
//            }
            val name = pair.first.replace("InterChange","", false)
            binding.tvMetroName.text = name.replace("InterChange","", false)

            binding.ivBgMetroIcon.setImageResource(pair.second)
            binding.cclRoot.setOnClickListener {
                onItemClick?.invoke(stationList[adapterPosition].first)
                Toast.makeText(context, stationList[adapterPosition].first, Toast.LENGTH_SHORT).show()
            }
        }

//        init {
//
//            itemView.setOnClickListener {
//                onItemClick?.invoke(stationList[adapterPosition].first)
//                Toast.makeText(context, stationList[adapterPosition].first, Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(stationList[position])
//        holder.binding.apply {
//            if(stationList[position].first.contains("InterChange", false)){
//                tvInterchange.text = "Interchange"
//            }
//            val name = stationList[position].first.replace("InterChange","", false)
//            tvMetroName.text = name.replace("InterChange","", false)
//
//            ivBgMetroIcon.setImageResource(stationList[position].second)
//        }
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MetroItemsBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}


