package com.dmrcmetro.models

data class MetroStationModel(
    val stationId: Int,
    val stationName: String,
    val metroLineName: String,
    val isInterChangeHere: Boolean,
    val interChangeDescription: String? = null,
    val walkingDistance: Float? = null,
    val timeToReachNextStation: Float? = null,
)
