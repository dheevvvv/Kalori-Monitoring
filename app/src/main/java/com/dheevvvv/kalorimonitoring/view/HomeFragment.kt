package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentHomeBinding
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.model.UserData
import com.dheevvvv.kalorimonitoring.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userManager: UserManager
    private val userViewModel: UserViewModel by viewModels()
    var targetKalori:String = ""
    var email:String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userManager = UserManager.getInstance(requireContext())


        binding.bottomNavMenu.setOnNavigationItemSelectedListener {item->
            when(item.itemId){
                R.id.accountFragment -> {
                    findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                    false
                }

                R.id.historyFragment -> {
                    findNavController().navigate(R.id.action_homeFragment_to_riwayatFragment)
                    false
                }

                else -> true
            }
        }

        lifecycleScope.launch {
            email = userManager.userEmail.collect{
                if (it!=null){
                    email = it.toString()
                }
            }.toString()
        }

        getDataFromFirestore(email)
        binding.tvKaloriTarget.setText(targetKalori)
    }

    private fun getDataFromFirestore(email: String) {

        lifecycleScope.launch {
            val userData: UserData? = userViewModel.getUserByEmail(email)

            if (userData != null) {
                targetKalori = userData.jumlahTargetKalori.toString()
            } else {
                //
            }
        }
    }


}