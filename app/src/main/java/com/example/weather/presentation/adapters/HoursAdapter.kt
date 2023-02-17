package com.example.weather.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.common.loadImage
import com.example.weather.databinding.ItemHourBinding
import com.example.weather.domain.models.Hour
import eightbitlab.com.blurview.RenderScriptBlur

class HoursAdapter : RecyclerView.Adapter<HoursAdapter.HomeVH>() {

    inner class HomeVH(val binding: ItemHourBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : ItemCallback<Hour>() {
        override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeVH(ItemHourBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = differ.currentList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        val hour = differ.currentList[position]
        holder.binding.apply {
            tvTemp.text = "${hour.currentTemp}°"
            imageView.loadImage(hour.currentConditionImage)
            tvTime.text = getTimeFormatted(hour.time)

            blurView.apply {
                outlineProvider = ViewOutlineProvider.BACKGROUND
                clipToPadding = true
                setupWith(
                    rootLayout,
                    RenderScriptBlur(holder.itemView.context)
                )
                    .setBlurRadius(16f)
            }

        }
    }

    private fun getTimeFormatted(hour: String) =
        when (hour.substring(hour.indexOf(" ") + 1)) {
            "00:00" -> "12:00AM"
            "01:00" -> "1:00AM"
            "02:00" -> "2:00AM"
            "03:00" -> "3:00AM"
            "04:00" -> "4:00AM"
            "05:00" -> "5:00AM"
            "06:00" -> "6:00AM"
            "07:00" -> "7:00AM"
            "08:00" -> "8:00AM"
            "09:00" -> "9:00AM"
            "10:00" -> "10:00AM"
            "11:00" -> "11:00AM"
            "12:00" -> "12:00PM"
            "13:00" -> "1:00PM"
            "14:00" -> "2:00PM"
            "15:00" -> "3:00PM"
            "16:00" -> "4:00PM"
            "17:00" -> "5:00PM"
            "18:00" -> "6:00PM"
            "19:00" -> "7:00pm"
            "20:00" -> "8:00PM"
            "21:00" -> "9:00PM"
            "22:00" -> "10:00PM"
            "23:00" -> "11:00PM"
            "24:00" -> "12:00PM"
            else -> hour
        }

}