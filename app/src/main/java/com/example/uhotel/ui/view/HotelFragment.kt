package com.example.uhotel.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.uhotel.R
import com.example.uhotel.imageslider.constants.ScaleTypes
import com.example.uhotel.imageslider.models.SlideModel
import com.example.uhotel.databinding.FragmentHotelBinding
import com.example.uhotel.ui.view_models.ViewModelHotel

class HotelFragment : Fragment() {

    private var binding: FragmentHotelBinding? = null
    private var viewModel: ViewModelHotel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHotelBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ViewModelHotel::class.java)
        viewModel?.loadData()
        viewModel?.roomData?.observe(viewLifecycleOwner, Observer { room ->
            val imageList = ArrayList<SlideModel>()
            room.image_urls.forEach{
                imageList.add(SlideModel(it,  ScaleTypes.CENTER_CROP))
            }
            binding?.imageSlider?.setImageList(imageList)
            binding?.adressHotelText?.text = room.adress
            binding?.priceForIt?.text = room.price_for_it
            binding?.minimalPric?.text = "от ${room.minimal_price} ₽"
            binding?.rating?.text = room.rating.toString()
            binding?.ratingName?.text = room.rating_name
            binding?.descriptionHotel?.text = room.about_the_hotel.description
        })
        viewModel?.loadData()


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = bundleOf("nameHotelText" to binding!!.nameHotelText.text)

        binding!!.btnChooseRoom.setOnClickListener {
            findNavController().navigate(R.id.action_hotelFragment_to_hotelRoomsFragment, bundle)
        }



    }
}