package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentSplashBinding
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.viewmodel.UserViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userManager = UserManager.getInstance(requireContext())


        Handler().postDelayed({

            lifecycleScope.launch {
                if (userManager.isLoggedIn().first()){
                    val role = userManager.getRole()
                    if (role == "user"){
                        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    } else{
                        findNavController().navigate(R.id.action_splashFragment_to_adminHomeFragment)
                    }

                }else{
                    userManager.clearData()
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        },2500)
    }


}