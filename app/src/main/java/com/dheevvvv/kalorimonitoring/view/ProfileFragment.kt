package com.dheevvvv.kalorimonitoring.view

import android.content.ContentValues
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
import com.google.firebase.firestore.FirebaseFirestore
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


        fetchUserByEmail(email)

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

    fun fetchUserByEmail(email: String) {
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")

        val targetEmail = email

        val query = usersCollection.whereEqualTo("email", targetEmail)

        query.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // Data dari dokumen
                    nama = document.getString("nama").toString()
                    beratBadan = document.getString("beratBadanSaatIni").toString()
                    tujuan = document.getString("tujuanDiet").toString()

                }
            }
            .addOnFailureListener { e ->
                //
                //
            }

    }




}