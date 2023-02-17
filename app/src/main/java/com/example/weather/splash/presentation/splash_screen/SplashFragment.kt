package com.example.weather.splash.presentation.splash_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weather.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            delay(3000)
            if (viewModel.shouldShowOnBoarding) {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment(
                        viewModel.backGroundImage.value!!,
                        viewModel.welcomeText.value!!
                    )
                )
            } else {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                        viewModel.backGroundImage.value!!,
                        viewModel.welcomeText.value!!
                    )
                )
            }
        }

    }

}