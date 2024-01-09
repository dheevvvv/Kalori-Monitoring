package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentTabLayoutLoginRegisterBinding
import com.dheevvvv.kalorimonitoring.view.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout


class TabLayoutLoginRegisterFragment : Fragment() {
    private lateinit var binding: FragmentTabLayoutLoginRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTabLayoutLoginRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TabAdapter(requireActivity().supportFragmentManager)
        val viewPager = binding.viewPagerlg
        viewPager.adapter = adapter
        binding.tabLayoutlg.setupWithViewPager(viewPager)

    }


}