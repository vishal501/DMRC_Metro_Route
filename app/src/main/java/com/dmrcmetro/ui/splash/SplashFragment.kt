package com.dmrcmetro.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmrcmetro.R
import com.dmrcmetro.databinding.FragmentHomeBinding
import com.dmrcmetro.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }


}