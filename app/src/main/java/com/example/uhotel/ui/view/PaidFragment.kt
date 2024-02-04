package com.example.uhotel.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.uhotel.R
import com.example.uhotel.databinding.FragmentPaidBinding

class PaidFragment : Fragment() {

    private var binding: FragmentPaidBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentPaidBinding.inflate(inflater, container, false)
        binding!!.toolbar.setNavigationIcon(R.drawable.arrow_left);


        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }
    }
}