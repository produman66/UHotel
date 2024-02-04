package com.example.uhotel.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uhotel.data.models.Room
import com.example.uhotel.data.models.Rooms
import com.example.uhotel.databinding.ItemNewTuristBinding
import com.example.uhotel.databinding.ItemRoomBinding
import com.example.uhotel.imageslider.constants.ScaleTypes
import com.example.uhotel.imageslider.models.SlideModel


class RoomAdapter : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    private var roomList = ArrayList<Room>()
    private var onClickListener: OnClickListener? = null

    fun setRoomList(roomList: List<Room>) {
        this.roomList = roomList as ArrayList<Room>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context))
        binding.itemRoom.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = roomList[position]
        holder.binding.name.text = room.name
        val imageList = ArrayList<SlideModel>()
        room.image_urls.forEach {
            imageList.add(SlideModel(it, ScaleTypes.CENTER_CROP))
        }
        holder.binding.imageSlider.setImageList(imageList)
        holder.binding.name.text = room.name
        holder.binding.price.text =  "от ${room.price} ₽"
        holder.binding.priceForIt.text =  room.price_per
        holder.binding.name.text = room.name



        holder.binding.btnChooseRoom.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, room)
            }
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

}