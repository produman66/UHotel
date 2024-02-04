package com.example.uhotel.ui.adapters

import com.example.uhotel.data.models.Room

interface OnClickListener {
    fun onClick(position: Int, model: Room)
}