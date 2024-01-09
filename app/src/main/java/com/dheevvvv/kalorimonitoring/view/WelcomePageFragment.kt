package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentWelcomePageBinding
import com.dheevvvv.kalorimonitoring.view.adapter.WelcomingAdapter
import com.google.android.material.tabs.TabLayoutMediator


class WelcomePageFragment : Fragment() {
    private lateinit var binding: FragmentWelcomePageBinding
    private val mPageNumbers = 4
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomePageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter =
            WelcomingAdapter(this,getListOfPagerContents(),mPageNumbers)
        val viewPager = binding.viewPager
        val tabsLayout = binding.tabLayout
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabsLayout, viewPager)
        {tab, position ->}.attach()

        tabsLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), com.google.android.material.R.color.mtrl_btn_transparent_bg_color))

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == pagerAdapter.itemCount - 1) {
                    binding.btnNext.visibility = View.VISIBLE
                } else {
                    binding.btnNext.visibility = View.GONE
                }
            }
        })

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_welcomePageFragment_to_loginFragment)
        }

    }


    fun getListOfPagerContents(): List<Array<String>> {

        val ar1 = arrayOf(getString(R.string.intro_title_1), getString(R.string.intro_sub_title_1),"R" )
        val ar2 = arrayOf(getString(R.string.intro_title_2), getString(R.string.intro_sub_title_2) ,"G")
        val ar3 = arrayOf(getString(R.string.intro_title_3), getString(R.string.intro_sub_title_3) ,"B")
        val ar4 = arrayOf(getString(R.string.intro_title_4), getString(R.string.intro_sub_title_4) ,"D")
        return listOf(ar1,ar2,ar3, ar4)
    }


}