package com.dmrcmetro.Utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.dmrcmetro.models.MetroStationModel
import com.dmrcmetro.models.graph.Edge
import com.dmrcmetro.models.graph.Vertex

class MetroGraph{

    val metroStationHashMap: HashMap<Vertex, ArrayList<Edge>> = HashMap<Vertex, ArrayList<Edge>>()

    fun addMetroEdgePath( metroStationModel: MetroStationModel,edge: Edge){
        val addVertex = Vertex( metroModel = metroStationModel)
        if(metroStationHashMap.containsKey(addVertex)){
           val connectMetroStation =  metroStationHashMap.getValue(addVertex)
            connectMetroStation.add(edge)
        }else{
            Log.d("sdsdf","drfdfg")
        }
    }

    fun addMetroStationVertex(metroStationModel: MetroStationModel ){
        val addNewVertex = Vertex( metroModel = metroStationModel)
        if(!metroStationHashMap.containsKey(addNewVertex)){
            metroStationHashMap[addNewVertex] = arrayListOf()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun print(){
        metroStationHashMap.forEach { vertex, edges ->
            Log.d("metro station", "${vertex.metroModel.stationName} - ${edges}}")
        }
    }

}