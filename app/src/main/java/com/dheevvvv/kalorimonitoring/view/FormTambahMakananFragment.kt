package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentFormTambahMakananBinding
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.room_database.MakananDikonsumsiData
import com.dheevvvv.kalorimonitoring.viewmodel.MakananDikonsumsiViewModel
import kotlinx.coroutines.launch


class FormTambahMakananFragment : Fragment() {
    private lateinit var binding:FragmentFormTambahMakananBinding
    private val makananDikonsumsiViewModel:MakananDikonsumsiViewModel by viewModels()
    private lateinit var userManager: UserManager
    var email:String = ""
    var spJamMakan = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormTambahMakananBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager = UserManager.getInstance(requireContext())

        val namaMakanan = arguments?.getString("namaMakanan")
        val takaranSaji = arguments?.getString("takaranSaji")
        val jumlahKalori = arguments?.getString("jumlahKalori")

        binding.namaMakanan.setText(namaMakanan)
        binding.jumlahKalori.setText(jumlahKalori)
        binding.takaranSaji.setText(takaranSaji)

        lifecycleScope.launch {
            userManager.userEmail.collect{
                if (it!=null){
                    email = it.toString()
                }
            }.toString()
        }.toString()

        val dataSpJamMakan = arrayOf("Pilih Waktu Makan", "Pagi", "Siang", "Malam")

        val adapterSpJamMakan = ArrayAdapter(requireContext(), R.layout.spinner_item, dataSpJamMakan)
        adapterSpJamMakan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spWaktuMakan.adapter = adapterSpJamMakan

        binding.spWaktuMakan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedValue = dataSpJamMakan[position]
                spJamMakan = selectedValue
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val data = MakananDikonsumsiData(
            email = email,
            namaMakanan = namaMakanan.toString(),
            gambarPath = "null",
            takaranSaji = takaranSaji.toString(),
            jumlahKalori = jumlahKalori.toString(),
            jamMakan = spJamMakan

        )

        binding.btnTambahMakanan.setOnClickListener {
            makananDikonsumsiViewModel.insertMakananDikonsumsi(data)
            Toast.makeText(context, "Data Makanan Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formTambahMakananFragment_to_riwayatFragment)
        }
    }





}