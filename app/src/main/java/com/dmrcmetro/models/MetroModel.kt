package com.dmrcmetro.models

import com.google.gson.annotations.SerializedName

data class MetroModel(
    @SerializedName("status"      ) var status      : Int?              = null,
    @SerializedName("line1"       ) var line1       : ArrayList<String> = arrayListOf(),
    @SerializedName("line2"       ) var line2       : ArrayList<String> = arrayListOf(),
    @SerializedName("interchange" ) var interchange : ArrayList<String> = arrayListOf(),
    @SerializedName("lineEnds"    ) var lineEnds    : ArrayList<String> = arrayListOf(),
    @SerializedName("path"        ) var path        : ArrayList<String> = arrayListOf(),
    @SerializedName("time"        ) var time        : Double?           = null
)
