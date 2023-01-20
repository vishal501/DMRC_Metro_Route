package com.dmrcmetro.ui

import com.dmrcmetro.api.ApiInterface
import javax.inject.Inject

class RouteRepository
@Inject
constructor(private val api : ApiInterface){

    suspend fun getPath(from : String, to : String) = api.getPosts(from,to)

//    private val pathLiveData = MutableLiveData<metroModel>()
//
//    fun path() : LiveData<metroModel>{
//        return pathLiveData
//    }
//    suspend fun getPath(from : String, to : String){
//        val result = apiInterface.getPosts(from,to)
//        if (result.body() != null){
//            pathLiveData.postValue(result.body())
//        }
//    }
}