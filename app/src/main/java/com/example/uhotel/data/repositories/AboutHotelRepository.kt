package com.example.uhotel.data.repositories

import com.example.uhotel.data.API.ApiInterface

class AboutHotelRepository constructor(private val retrofitService: ApiInterface) {
    fun getInfoAboutRoom() = retrofitService.getRooms()
    fun getInfoAboutHotel() = retrofitService.getHotels()
}