package com.dmrcmetro.models.graph

import com.dmrcmetro.models.MetroStationModel

data class Edge(
    val sourceId: MetroStationModel,
    val destinationId: MetroStationModel,
    val timeWeight: Float? = null ,
    val priceWeight: Int? = null,
)
