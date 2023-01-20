package com.dmrcmetro.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmrcmetro.models.MetroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel
@Inject
constructor(private val repository: RouteRepository): ViewModel() {


    private val _response = MutableLiveData<MetroModel>()

    val responseMetro: LiveData<MetroModel>
        get() = _response

    fun setRoute(from : String,to : String){
        getAllPath(from, to)
    }


    private fun getAllPath(from : String,to : String) = viewModelScope.launch{
        repository.getPath(from,to).let { response ->
            if(response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("tag","getAllPaths Error: ${response.errorBody()}")
            }
        }
    }
}