package com.example.uhotel.ui.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uhotel.R
import com.example.uhotel.databinding.FragmentBookingBinding
import com.example.uhotel.imageslider.constants.ScaleTypes
import com.example.uhotel.imageslider.models.SlideModel
import com.example.uhotel.ui.adapters.AdapterTurist
import com.example.uhotel.ui.adapters.RoomAdapter
import com.example.uhotel.ui.view_models.RoomViewModel
import com.example.uhotel.ui.view_models.ViewModelBooking
import com.example.uhotel.ui.view_models.ViewModelHotel

class BookingFragment : Fragment() {

    var binding: FragmentBookingBinding? = null
    private var viewModel: ViewModelBooking? = null
    private var adapterTurist: AdapterTurist? = null

    private lateinit var nameHotel: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentBookingBinding.inflate(inflater, container, false)

        unpackArguments(arguments)
        initViewModel()
        binding!!.toolbar.setNavigationIcon(R.drawable.arrow_left)
        binding!!.nameHotel.text = nameHotel
        prepareRecyclerView()

        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding!!.btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_bookingFragment_to_paidFragment)
        }

        binding!!.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }

       binding!!.addButton.setOnClickListener{

           adapterTurist?.addTuristList(adapterTurist?.itemCount!!)
       }





        binding!!.emailText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val email =  binding!!.emailText.text.toString()

                val emailPattern = "[a-zA-Z]+[0-9]*@mail\\.ru".toRegex()
                val isValidEmail = email.matches(emailPattern)

                // Если почта некорректна, закрашиваем поле в красный цвет
                if (!isValidEmail) {
                    binding!!.textEmailLayout.setBackgroundResource(R.drawable.custom_input_error)
                }
                else {
                    binding!!.textEmailLayout.setBackgroundResource(R.drawable.custom_input)
                }
            }
        }

    }


    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(ViewModelBooking::class.java)
        viewModel?.roomData?.observe(viewLifecycleOwner, Observer { room ->
            binding?.adressHotelText?.text = room.hotel_adress
            binding?.departure?.text = room.departure
            binding?.arrivalCountry?.text = room.arrival_country
            binding?.departure?.text = "${room.tour_date_start} - ${room.tour_date_stop}"
            binding?.numberOfNights?.text = room.number_of_nights.toString()
            binding?.nameHotel?.text = nameHotel
            binding?.textNameHotel?.text = nameHotel
            binding?.textFood?.text = room.nutrition
            binding?.nameRoom?.text = room.room
            binding?.sumTurAns?.text = room.tour_price.toString()
            binding?.sumToplivAns?.text = room.fuel_charge.toString()
            binding?.sumServesAns?.text = room.service_charge.toString()
            binding?.sumAns?.text = (room.tour_price + room.fuel_charge + room.service_charge).toString()
            binding?.btnPay?.text = "Оплатить ${binding?.sumAns?.text} ₽"
        })
        viewModel?.loadData()
        binding!!.recycler.addItemDecoration(SpaceItemDecoration(8))
    }

    private fun unpackArguments(arguments: Bundle?) {
        nameHotel = arguments?.getString("nameHotel").toString()
    }

    private fun prepareRecyclerView() {
        adapterTurist = AdapterTurist()
        binding!!.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterTurist
        }
    }



}
