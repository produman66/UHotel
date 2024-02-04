package com.example.uhotel.ui.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uhotel.data.API.ApiInterface
import com.example.uhotel.data.models.AboutHotel
import com.example.uhotel.data.models.Booking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelBooking : ViewModel() {
    private val _roomData = MutableLiveData<Booking>()
    val roomData: LiveData<Booking> get() = _roomData

    fun loadData() {
        val apiInterface = ApiInterface.create().getBookingHotels()

        apiInterface.enqueue(object : Callback<Booking> {
            override fun onResponse(call: Call<Booking>?, response: Response<Booking>?) {
                _roomData.value = response?.body()

            }

            override fun onFailure(call: Call<Booking>?, t: Throwable?) {
                Log.d("testLoads", "Moment Loading VM : ${t?.message}")
            }
        })
    }
}