package com.dmrcmetro.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dmrcmetro.R
import com.dmrcmetro.adapter.StationsAdapter
import com.dmrcmetro.databinding.FragmentHomeBinding
import com.dmrcmetro.models.Stations
import com.dmrcmetro.ui.BaseFragment
import com.dmrcmetro.ui.RouteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    val bundle = Bundle()

//    @Inject
    lateinit var myAdapter: StationsAdapter
    private var stations = ArrayList<Stations>()
    private lateinit var binding: FragmentHomeBinding
    val viewModel: RouteViewModel by viewModels()
    var sourceEntered = false

//    private val listener = object : OnClickListener {
//        override fun onClickEvent(stations: Stations) {
//            if(!sourceEntered){
//                binding.etFrom.setText(stations.name)
//                binding.rvStationName.visibility = View.GONE
//                sourceEntered = true
//
//            } else {
//                binding.etTo.setText(stations.name)
//                binding.rvStationName.visibility = View.GONE
//                sourceEntered = false
//
//            }
//
//        }
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    fun onClickTest(){
        var from = binding.etFrom.text.toString()
        var to = binding.etTo.text.toString()
        val temp = from
        from = to
        to = temp
        binding.etFrom.text = from
        binding.etTo.text = to
    }

    fun searchPatient(v: View?, event: MotionEvent): Boolean {
        if (MotionEvent.ACTION_UP == event.action) {
            //your code
        }
        return true
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        onTouchChangeListener(binding.ivInterChange)
        binding.ivInterChange.setOnClickListener {
            onClickTest()
        }
        onTouchChangeListener(binding.btnCreateRoute)
        binding.btnCreateRoute.setOnClickListener {
            if (binding.etFrom.text.isEmpty() || binding.etTo.text.isEmpty()){
                Toast.makeText(requireContext(),"Please Enter Your Destination",Toast.LENGTH_SHORT).show()
            } else {
                val from = binding.etFrom.text.toString()
                val to = binding.etTo.text.toString()
                bundle.putString("froms", from)
                bundle.putString("tos", to)
                lifecycleScope.launch{
                    delay(300)
                    findNavController().navigate(R.id.action_homeFragment_to_routeFragment,bundle)
                }
            }

        }

        onTouchChangeListener(binding.ivCancel)
        binding.ivCancel.setOnClickListener {
            binding.etFrom.text = ""
            binding.etTo.text = ""
            checkText()
            findNavController().currentBackStackEntry?.savedStateHandle?.remove<String>("from")
            findNavController().currentBackStackEntry?.savedStateHandle?.remove<String>("to")
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            "from"
        )?.observe(viewLifecycleOwner) {
            if (it != null){
                binding.etFrom.text = it
            }

        }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            "to"
        )?.observe(viewLifecycleOwner) {
            if (it != null){
                binding.etTo.text = it
            }
        }


        binding.etFrom.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("fromm",0)
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment,bundle)
        }
        binding.etTo.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putInt("fromm",1)
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        checkText()
    }
    fun checkText(){
        if (binding.etFrom.text!!.isEmpty()){
            binding.tvA.setBackgroundResource(R.drawable.bg_curved_grey)
        } else{
            binding.tvA.setBackgroundResource(R.drawable.bd_curved_orange)
        }
        if (binding.etTo.text!!.isEmpty()){
            binding.tvB.setBackgroundResource(R.drawable.bg_curved_grey)
        } else{
            binding.tvB.setBackgroundResource(R.drawable.bd_curved_orange)
        }
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