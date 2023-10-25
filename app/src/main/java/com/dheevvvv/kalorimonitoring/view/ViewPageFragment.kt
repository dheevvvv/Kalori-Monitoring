package com.dheevvvv.kalorimonitoring.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentViewPageBinding


class ViewPageFragment : Fragment() {
    private lateinit var binding: FragmentViewPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.takeIf { it.containsKey(INTRO_STRING_OBJECT) }?.apply {
            binding.viewPagerHeader.text = getStringArray(INTRO_STRING_OBJECT)!![0]
            binding.viewPagerSubText.text = getStringArray(INTRO_STRING_OBJECT)!![1]
            change(getStringArray(INTRO_STRING_OBJECT)!![2])
        }

        binding.skipText.setOnClickListener {
            findNavController().navigate(R.id.action_welcomePageFragment_to_getStartedFragment)
        }
    }


    fun change(color:String){
        when(color)
        {
            "R" -> {
                binding.baseLinear.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.argentinian_blue))
                binding.ivWelcome.setImageResource(R.drawable.sunrise)
            }
            "G" -> {
                binding.baseLinear.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.cornflower_blue))
                binding.ivWelcome.setImageResource(R.drawable.sun)
            }

            "B" -> {
//                binding.baseLinear.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.earth_01))
                binding.baseLinear.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.earth_01))
                binding.ivWelcome.setImageResource(R.drawable.moon)
            }

            "D" -> {
                binding.baseLinear.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.electric_purple))
                binding.ivWelcome.setImageResource(R.drawable.running)
            }
        }
    }


}