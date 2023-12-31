package com.dheevvvv.kalorimonitoring.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dheevvvv.kalorimonitoring.view.INTRO_STRING_OBJECT
import com.dheevvvv.kalorimonitoring.view.ViewPageFragment

class WelcomingAdapter(fragment: Fragment, val listOfPagerContents: List<Array<String>>, val mPageNumber: Int) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return mPageNumber
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPageFragment()

        when(position){
            0 ->
                fragment.arguments = Bundle().apply {
                    putStringArray(INTRO_STRING_OBJECT, listOfPagerContents[0])
                }
            1 ->
                fragment.arguments = Bundle().apply {
                    putStringArray(INTRO_STRING_OBJECT, listOfPagerContents[1])
                }
            2 ->
                fragment.arguments = Bundle().apply {
                    putStringArray(INTRO_STRING_OBJECT, listOfPagerContents[2])
                }
            3 ->
                fragment.arguments = Bundle().apply {
                    putStringArray(INTRO_STRING_OBJECT, listOfPagerContents[3])
                }
        }
        return fragment
    }

}