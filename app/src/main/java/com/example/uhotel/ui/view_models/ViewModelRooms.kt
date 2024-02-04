package com.example.uhotel.ui.view_models

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uhotel.data.API.ApiInterface
import com.example.uhotel.data.models.AboutHotel
import com.example.uhotel.data.models.Room
import com.example.uhotel.data.models.Rooms
import com.example.uhotel.data.repositories.AboutHotelRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class ViewModelRooms constructor(private val repository: AboutHotelRepository) : ViewModel() {
//
//    val roomList = MutableLiveData<List<Room>>()
//    val errorMessage = MutableLiveData<String>()
//
//    fun getAllRooms() {
//        val response = repository.getInfoAboutRoom()
//        response.enqueue(object : Callback<List<Room>> {
//            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
//                roomList.postValue(response.body())
//                Log.d("loadTest", "ViewModel : True")
//            }
//
//            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
//                errorMessage.postValue(t.message)
//            }
//        })
//    }
//}
//
//class MyViewModelFactory constructor(private val repository: AboutHotelRepository) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(ViewModelRooms::class.java)) {
//            ViewModelRooms(this.repository) as T
//        } else {
//            throw IllegalArgumentException("ViewModel Not Found")
//        }
//    }
//}

class RoomViewModel : ViewModel() {
    private var roomLiveData = MutableLiveData<List<Room>>()
    fun getPopularRooms() {
        ApiInterface.create().getRooms().enqueue(object  : Callback<Rooms>{
            override fun onResponse(call: Call<Rooms>, response: Response<Rooms>) {
                if (response.body()!=null){
                    roomLiveData.value = response.body()!!.rooms
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Rooms>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<Room>> {
        return roomLiveData
    }
}

