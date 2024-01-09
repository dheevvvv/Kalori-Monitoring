package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentAdminHomeBinding
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.view.adapter.DaftarMakananAdapter
import com.dheevvvv.kalorimonitoring.viewmodel.DaftarMakananViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class AdminHomeFragment : Fragment() {
    private lateinit var binding: FragmentAdminHomeBinding
    private val daftarMakananViewModel: DaftarMakananViewModel by viewModels()
    private lateinit var userManager: UserManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager = UserManager.getInstance(requireContext())
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_adminHomeFragment_to_formAdminTambahMakananFragment)
        }

        daftarMakananViewModel.fetchListMakanan()
        daftarMakananViewModel.makananList.observe(viewLifecycleOwner, Observer {
            val adapter = DaftarMakananAdapter(it)
            binding.rvAdmin.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvAdmin.adapter = adapter

        })

        binding.btnOut.setOnClickListener {
            GlobalScope.async {
                userManager.clearData()
            }
            findNavController().navigate(R.id.action_adminHomeFragment_to_loginFragment)
            Toast.makeText(context, "Logging Out", Toast.LENGTH_SHORT).show()
        }
    }

    }

