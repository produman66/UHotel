package com.example.uhotel.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.uhotel.R
import com.example.uhotel.data.models.Room
import com.example.uhotel.databinding.ItemNewTuristBinding
import com.example.uhotel.databinding.ItemRoomBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle.parent

class AdapterTurist : RecyclerView.Adapter<AdapterTurist.ViewHolder>() {

    private var turistList = mutableListOf<Int>()
    private var onClickListener: OnClickListener? = null

    fun addTuristList(tur: Int) {
        turistList.add(tur)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemNewTuristBinding) : RecyclerView.ViewHolder(binding.root) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ItemNewTuristBinding.inflate(LayoutInflater.from(viewGroup.context))
        binding.baseCardview.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.heading.text = "Турист ${turistList[position] + 1}"
        holder.binding.arrowButton.setOnClickListener{
            if (holder.binding.hiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(holder.binding.baseCardview, AutoTransition())
                holder.binding.hiddenView.visibility = View.GONE
                holder.binding.arrowButton.setImageResource(R.drawable.arrow_down_blue)
            } else {
                TransitionManager.beginDelayedTransition(holder.binding.baseCardview, AutoTransition())
                holder.binding.hiddenView.visibility = View.VISIBLE
                holder.binding.arrowButton.setImageResource(R.drawable.arrow_top_blue)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return turistList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

}