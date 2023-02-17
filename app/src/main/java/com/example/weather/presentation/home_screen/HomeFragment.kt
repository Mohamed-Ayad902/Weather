package com.example.weather.presentation.home_screen

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.common.collectLatest
import com.example.weather.common.hide
import com.example.weather.common.show
import com.example.weather.common.showToast
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.presentation.adapters.HoursAdapter
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

private const val TAG = "HomeFragment mohamed"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()
    private lateinit var hoursAdapter: HoursAdapter
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.apply {
            imageView.setImageResource(args.image)
            tvWelcome.text = args.welcomeText
            currentBlurView.apply {
                outlineProvider = ViewOutlineProvider.BACKGROUND
                clipToPadding = true
                setupWith(
                    binding.rootLayout,
                    RenderScriptBlur(requireContext())
                )
                    .setBlurRadius(16f)
            }
            detailsBlurView.apply {
                outlineProvider = ViewOutlineProvider.BACKGROUND
                clipToPadding = true
                setupWith(
                    binding.rootLayout,
                    RenderScriptBlur(requireContext())
                )
                    .setBlurRadius(16f)
            }
        }
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.hoursRecyclerView.apply {
            hoursAdapter = HoursAdapter()
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = hoursAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.getForecast()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        viewModel.state.collectLatest(viewLifecycleOwner) { state ->
            Log.d(TAG, "onViewCreated: state -> $state")
            binding.apply {
                if (state.isLoading) {
                    progressBar.show()
                } else {
                    progressBar.hide()
                }
                if (state.error != null && state.weather == null) {
                    showToast(state.error)
                }
                if (state.weather != null) {
                    tvCondetion.text = state.weather.currentCondition
                    tvPressure.text = "${state.weather.currentPressure}%"
                    tvHumidity.text = "${state.weather.currentHumidity}%"
                    tvWind.text = "${state.weather.currentWindSpeed}km/h"
                    Glide.with(requireContext()).load(state.weather.currentConditionImage)
                        .placeholder(
                            R.drawable.logo
                        ).error(R.drawable.ic_launcher_foreground)
                        .into(ivWeatherImage)
                    tvCityName.text = state.weather.cityName
                    tvRegionCountry.text = state.weather.cityDetails
                    tvTemp.text = "${state.weather.currentTemp}°"
                    hoursAdapter.differ.submitList(state.weather.forecastDays[0].hours)
                }
            }
        }
    }

}