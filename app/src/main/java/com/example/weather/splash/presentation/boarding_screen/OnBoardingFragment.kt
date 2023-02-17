package com.example.weather.splash.presentation.boarding_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weather.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private val viewModel: OnBoardingViewModel by viewModels()
    private val args: OnBoardingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater, container, false)
        binding.blurView.apply {
            outlineProvider = ViewOutlineProvider.BACKGROUND
            clipToPadding = true
            setupWith(
                binding.rootLayout,
                RenderScriptBlur(requireContext())
            )
                .setBlurRadius(16f)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            viewModel.stopShowingOnBoarding()
            findNavController().navigate(
                OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment(
                    args.image,
                    args.welcomeText
                )
            )
        }
    }

}