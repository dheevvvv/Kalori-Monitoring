package com.dheevvvv.kalorimonitoring.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentGetStartedBinding
import com.dheevvvv.kalorimonitoring.viewmodel.GetStartedViewModel
import java.util.*


class GetStartedFragment : Fragment() {
    private lateinit var binding:FragmentGetStartedBinding
    lateinit var getStartedViewModel: GetStartedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGetStartedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStartedViewModel = ViewModelProvider(requireActivity()).get(GetStartedViewModel::class.java)

        val dataSpSatuanBerat = arrayOf("Kg", "Ons", "Gram", "Pound")

        val adapterSpSatuanBerat = ArrayAdapter(requireContext(), R.layout.spinner_item, dataSpSatuanBerat)
        adapterSpSatuanBerat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSatuanBeratbadanSaatIni.adapter = adapterSpSatuanBerat

        binding.spSatuanBeratBadanDiinginkan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedValue = dataSpSatuanBerat[position]
                val inputBeratBadanSaatIni = binding.etBeratBadan.text.toString()
                getStartedViewModel._beratBadanSaatIni.postValue(inputBeratBadanSaatIni + selectedValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val adapterSpSatuanBeratDiinginkan = ArrayAdapter(requireContext(),R.layout.spinner_item, dataSpSatuanBerat)
        adapterSpSatuanBerat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSatuanBeratBadanDiinginkan.adapter = adapterSpSatuanBeratDiinginkan

        binding.spSatuanBeratBadanDiinginkan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedValue = dataSpSatuanBerat[position]
                val inputBeratBadanSaatDiinginkan = binding.etBeratBadanDiinginkan.text.toString()
                getStartedViewModel._beratBadanYangDiinginkan.postValue(inputBeratBadanSaatDiinginkan + selectedValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val dataSpTujuanDiet = arrayOf("Cutting", "Bulking", "Maintaining")

        val adapterSpSTujuanDiet = ArrayAdapter(requireContext(), R.layout.spinner_item, dataSpTujuanDiet)
        adapterSpSTujuanDiet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spTujuanDiet.adapter = adapterSpSTujuanDiet

        binding.spTujuanDiet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedValue = dataSpTujuanDiet[position]
                getStartedViewModel._tujuanDiet.postValue(selectedValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val inputNama = binding.etNama.text.toString()
        getStartedViewModel._nama.postValue(inputNama)

        binding.etTanggalTargetapaian.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                getStartedViewModel._tanggalTargetCapaian.postValue(selectedDate)
                binding.etTanggalTargetapaian.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()

        }

        val inputKaloriHarian = binding.etJumlahTargetKaloriHarian.text.toString()
        getStartedViewModel._jumlahTargetkaloriHarian.postValue(inputKaloriHarian)

        binding.btnSimpanGetStarted.setOnClickListener {
            if (
                getStartedViewModel.nama.value!!.isEmpty()
                || getStartedViewModel.beratBadanSaatIni.value!!.isEmpty()
                || getStartedViewModel.beratBadanYangDiinginkan.value!!.isEmpty()
                || getStartedViewModel.tujuanDiet.value!!.isEmpty()
                || getStartedViewModel.tanggalTargetCapaian.value!!.isEmpty()
                || getStartedViewModel.jumlahTargetkaloriHarian.value!!.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else{
                findNavController().navigate(R.id.action_getStartedFragment_to_homeFragment)
            }
        }

    }

}