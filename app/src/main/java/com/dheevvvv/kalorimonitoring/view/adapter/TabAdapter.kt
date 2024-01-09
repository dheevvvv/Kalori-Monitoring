package com.dheevvvv.kalorimonitoring.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dheevvvv.kalorimonitoring.view.LoginFragment
import com.dheevvvv.kalorimonitoring.view.RegisterFragment

@Suppress("DEPRECATION")
class TabAdapter(fm:FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> LoginFragment()
            1 -> RegisterFragment()
            else -> throw java.lang.IllegalArgumentException("Invalid")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Login"
            1 -> "Register"
            else -> null
        }

    }
}