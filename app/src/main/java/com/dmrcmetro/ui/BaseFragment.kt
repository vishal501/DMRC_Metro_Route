package com.dmrcmetro.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dmrcmetro.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

//    protected abstract fun setDetails()
//protected abstract fun getClickHandler(): Any? //protected abstract Object getClickHandler();
//protected abstract fun getRowId(): Int  //protected abstract int getRowId();


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    @SuppressLint("ClickableViewAccessibility")
//    fun onTouchListener(view: View, uri: Int?, bundle: Bundle?){
////        setDetails()
//        val scaleUp = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_up)
//        val scaleDown = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_down)
//        view.setOnTouchListener { _, p1 ->
//            if (p1?.action == MotionEvent.ACTION_UP) {
//                view.startAnimation(scaleUp)
//                lifecycleScope.launch {
//                setDetails()
//                    delay(200)
//                findNavController().navigate(uri!!,bundle)
//                }
//            } else if (p1?.action == MotionEvent.ACTION_DOWN) {
//                view.startAnimation(scaleDown)
//            }
//            true
//        }
//    }

    @SuppressLint("ClickableViewAccessibility")
    fun onTouchChangeListener(view: View){
        val scaleUp = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_up)
        val scaleDown = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_down)
        view.setOnTouchListener { _, p1 ->
            if (p1?.action == MotionEvent.ACTION_UP) {
                view.startAnimation(scaleUp)
                view.performClick()
            } else if (p1?.action == MotionEvent.ACTION_DOWN) {
                view.startAnimation(scaleDown)
            }
            true
        }
    }

}