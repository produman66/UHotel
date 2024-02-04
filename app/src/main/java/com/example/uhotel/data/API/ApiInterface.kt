package com.example.uhotel.data.API

import com.example.uhotel.data.models.AboutHotel
import com.example.uhotel.data.models.Booking
import com.example.uhotel.data.models.Room
import com.example.uhotel.data.models.Rooms
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface ApiInterface {

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    fun getRooms() : Call<Rooms>

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    fun getHotels() :Call<AboutHotel>

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    fun getBookingHotels() :Call<Booking>

    companion object {

        var BASE_URL = "https://run.mocky.io/v3/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}



