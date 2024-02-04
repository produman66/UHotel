package com.example.uhotel.ui.view

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uhotel.R
import com.example.uhotel.data.API.ApiInterface
import com.example.uhotel.data.models.Room
import com.example.uhotel.data.repositories.AboutHotelRepository
import com.example.uhotel.databinding.FragmentHotelRoomsBinding
import com.example.uhotel.ui.adapters.OnClickListener

import com.example.uhotel.ui.adapters.RoomAdapter

import com.example.uhotel.ui.view_models.RoomViewModel


class HotelRoomsFragment : Fragment() {

    private var binding: FragmentHotelRoomsBinding? = null

    private lateinit var viewModel: RoomViewModel
    private lateinit var roomAdapter : RoomAdapter

    private lateinit var nameHotel: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHotelRoomsBinding.inflate(inflater, container, false)

        unpackArguments(arguments)
        prepareRecyclerView()
        initViewModel()

        binding!!.toolbar.setNavigationIcon(R.drawable.arrow_left);
        binding!!.toolbarText.text = nameHotel


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }

        roomAdapter.setOnClickListener(object :
            OnClickListener {
            override fun onClick(position: Int, model: Room) {
                val bundle = bundleOf("nameHotel" to binding!!.toolbarText.text, "nameRoom" to model.name)
                findNavController().navigate(R.id.action_hotelRoomsFragment_to_bookingFragment, bundle)
            }
        })

    }

    private fun unpackArguments(arguments: Bundle?) {
        nameHotel = arguments?.getString("nameHotelText").toString()
    }

    private fun prepareRecyclerView() {
        roomAdapter = RoomAdapter()
        binding!!.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = roomAdapter
        }
        binding!!.recyclerView.addItemDecoration(SpaceItemDecoration(8))
    }


    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[RoomViewModel::class.java]
        viewModel.getPopularRooms()
        viewModel.observeMovieLiveData().observe(viewLifecycleOwner, Observer { roomList ->
            roomAdapter.setRoomList(roomList)
        })
    }
}


//Класс отвечающий за отступы айтема из recyclerView
class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
        outRect.bottom = space
    }
}


