package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentProfileBinding
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.model.UserData
import com.dheevvvv.kalorimonitoring.viewmodel.UserViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userManager: UserManager
    private val userViewModel: UserViewModel by activityViewModels()
    var nama:String = ""
    var tujuan:String = ""
    var beratBadan:String = ""
    var email:String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userManager = UserManager.getInstance(requireContext())

        binding.bottomNavMenu.setOnNavigationItemSelectedListener {item->
            when(item.itemId){
                R.id.homeFragment -> {
                    findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
                    false
                }

                R.id.historyFragment -> {
                    findNavController().navigate(R.id.action_profileFragment_to_riwayatFragment)
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
        binding.tvNama.setText(nama)
        binding.tvBeratBadan.setText(beratBadan)
        binding.tvTujuan.setText(tujuan)



        binding.btnLogout.setOnClickListener {
            GlobalScope.async {
                userManager.clearData()
            }
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            Toast.makeText(context, "Logging Out", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataFromFirestore(email: String) {

        lifecycleScope.launch {
            val userData: UserData? = userViewModel.getUserByEmail(email)

            if (userData != null) {
                nama = userData.nama
                beratBadan = userData.beratBadanSaatIni.toString()
                tujuan = userData.tujuanDiet.toString()

            } else {
                Log.e("FirestoreData", "Data not found for email: $email")
            }
        }
    }

}