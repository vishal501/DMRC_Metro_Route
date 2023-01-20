package com.dmrcmetro.utils

import com.dmrcmetro.models.Stations

interface OnClickListener {
    fun onClickEvent(stations: Stations)
}