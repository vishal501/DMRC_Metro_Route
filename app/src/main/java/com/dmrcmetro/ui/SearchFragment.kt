package com.dmrcmetro.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmrcmetro.R
import com.dmrcmetro.adapter.StationsAdapter
import com.dmrcmetro.databinding.FragmentSearchBinding
import com.dmrcmetro.models.Stations
import com.dmrcmetro.utils.OnClickListener
import java.util.Locale.filter

class SearchFragment : Fragment() {

    lateinit var myAdapter: StationsAdapter
    private var stations = ArrayList<Stations>()
    private lateinit var binding: FragmentSearchBinding
//    private var search = false
//    var sourceEntered = false
    private val listener = object : OnClickListener {
        override fun onClickEvent(stations: Stations) {
            Toast.makeText(requireContext(),stations.name,Toast.LENGTH_SHORT).show()
            when(arguments?.getInt("fromm")){
                0 -> { findNavController().previousBackStackEntry?.savedStateHandle?.set("from",stations.name) }
                else -> {findNavController().previousBackStackEntry?.savedStateHandle?.set("to",stations.name)}
            }
            findNavController().popBackStack()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserList()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                //Triggered when text changed at et_to edittext
                Log.d("Listview", "Is this From list view working")
                filter(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })
    }

    private fun getUserList() {
        binding.rvStationName.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        stations = Stations.getUserList()
        myAdapter = StationsAdapter(stations)
        myAdapter.setListener(listener)
        binding.rvStationName.adapter = myAdapter
    }


    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredNames = ArrayList<Stations>()
        //looping through existing elements and adding the element to filtered list
        stations.filterTo(filteredNames) {
            //if the existing elements contains the search input
            it.name.lowercase().contains(text.lowercase())
        }
        //calling a method of the adapter class and passing the filtered list
        myAdapter.filterList(filteredNames)
    }
}