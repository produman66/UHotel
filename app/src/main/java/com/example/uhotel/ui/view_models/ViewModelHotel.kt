package com.example.uhotel.ui.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uhotel.data.models.AboutHotel
import com.example.uhotel.data.API.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelHotel : ViewModel() {
    private val _roomData = MutableLiveData<AboutHotel>()
    val roomData: LiveData<AboutHotel> get() = _roomData

    fun loadData() {
        val apiInterface = ApiInterface.create().getHotels()

        apiInterface.enqueue(object : Callback<AboutHotel> {
            override fun onResponse(call: Call<AboutHotel>?, response: Response<AboutHotel>?) {
                _roomData.value = response?.body()

            }

            override fun onFailure(call: Call<AboutHotel>?, t: Throwable?) {
                Log.d("testLoads", "Moment Loading VM : ${t?.message}")
            }
        })
    }
}