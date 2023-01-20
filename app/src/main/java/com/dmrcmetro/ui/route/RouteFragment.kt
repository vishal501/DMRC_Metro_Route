package com.dmrcmetro.ui.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmrcmetro.R
import com.dmrcmetro.adapter.MyAdapter
import com.dmrcmetro.databinding.FragmentRouteBinding
import com.dmrcmetro.ui.BaseFragment
import com.dmrcmetro.ui.RouteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RouteFragment : BaseFragment() {

    @Inject
    lateinit var myAdapter: MyAdapter
    val viewModel: RouteViewModel by viewModels()
    private lateinit var binding: FragmentRouteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRouteBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if(onButtonPressed(view,true)){
//            setUpRecyclerView()
//        }

        val from = arguments?.getString("froms")
        val to = arguments?.getString("tos")
        viewModel.setRoute(from.toString(),to.toString())
        setUpRecyclerView()
        findNavController().previousBackStackEntry?.savedStateHandle?.set("from",from)
        findNavController().previousBackStackEntry?.savedStateHandle?.set("to",to)
    }

    private fun setUpRecyclerView() {
//        myAdapter = MyAdapter()


        binding.rvMetroRoute.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }

        viewModel.responseMetro.observe(requireActivity()) { response ->
//            myAdapter.tv = listOf(listPath)
            if (response != null) {
                myAdapter.routeList(response)
            }
        }
    }

}