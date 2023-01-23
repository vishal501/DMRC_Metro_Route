package com.dmrcmetro

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.dmrcmetro.Utils.MetroGraph
import com.dmrcmetro.databinding.ActivityMainBinding
import com.dmrcmetro.models.MetroStationModel
import com.dmrcmetro.models.graph.Edge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val graph = MetroGraph()

        val palamModel = MetroStationModel(
            stationId = 1,
            stationName = "Palam",
            metroLineName = "Magenta",
            isInterChangeHere = false,
        )
        val sadarBazarModel = MetroStationModel(
            stationId = 2,
            stationName = "sadar bazar",
            metroLineName = "Magenta",
            isInterChangeHere = false,
        )
        val dashRathpuriModel = MetroStationModel(
            stationId = 3,
            stationName = "dash rathpuri",
            metroLineName = "Magenta",
            isInterChangeHere = false,
        )
        graph.addMetroStationVertex(palamModel)
        graph.addMetroStationVertex(sadarBazarModel)
        graph.addMetroStationVertex(dashRathpuriModel)

        graph.addMetroEdgePath(palamModel, Edge(
            sourceId = palamModel,
            destinationId = sadarBazarModel
        ))

        graph.addMetroEdgePath(palamModel, Edge(
            sourceId = palamModel,
            destinationId = dashRathpuriModel
        ))

        graph.metroStationHashMap.forEach { vertex, edges ->
            Log.d("shubham", "${vertex} - ${edges}")
        }

        graph.print()



    }


}