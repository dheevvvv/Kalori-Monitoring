package com.dheevvvv.kalorimonitoring.view

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class GetStartedFragment : Fragment() {
    private lateinit var binding:FragmentGetStartedBinding
    lateinit var getStartedViewModel: GetStartedViewModel
    var tanggalTarget: String = ""
    var spBeratBadanSaatIni: String = ""
    var spBeratBadanDiinginkan: String = ""
    var tujuanDiet: String = ""
    var username:String = ""
    var email:String = ""
    var password:String = ""

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


        username = arguments?.getString("username").toString()
        email = arguments?.getString("email").toString()
        password = arguments?.getString("password").toString()


        val dataSpSatuanBerat = arrayOf("Kg", "Ons", "Gram", "Pound")

        val adapterSpSatuanBerat = ArrayAdapter(requireContext(), R.layout.spinner_item, dataSpSatuanBerat)
        adapterSpSatuanBerat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSatuanBeratbadanSaatIni.adapter = adapterSpSatuanBerat

        binding.spSatuanBeratbadanSaatIni.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedValue = dataSpSatuanBerat[position]
                spBeratBadanSaatIni = selectedValue
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
                spBeratBadanDiinginkan =  selectedValue
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
                tujuanDiet = (selectedValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.btnSimpanGetStarted.setOnClickListener {
           simpanData()
        }

        binding.etTanggalTargetapaian.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                tanggalTarget = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.etTanggalTargetapaian.text = tanggalTarget
            }, year, month, day)

            datePickerDialog.show()

        }

    }

    private fun simpanData() {
        val nama = binding.etNama.text.toString()
        val tujuanDiet = binding.spTujuanDiet.selectedItem.toString()
        val beratBadanSaatIni = binding.etBeratBadan.text.toString() + spBeratBadanSaatIni
        val beratBadanDiinginkan = binding.etBeratBadanDiinginkan.text.toString() + spBeratBadanDiinginkan


        val jumlahKalori = binding.etJumlahTargetKaloriHarian.text.toString() + "Kkal"

        // Validasi input
        if (nama.isEmpty() || beratBadanSaatIni.isEmpty() || beratBadanDiinginkan.isEmpty() || jumlahKalori.isEmpty() || tujuanDiet.isEmpty())  {
            Toast.makeText(requireContext(), "Mohon isi semua field", Toast.LENGTH_SHORT).show()

            // Fokus ke elemen yang kosong
            if (nama.isEmpty()) {
                binding.etNama.requestFocus()
            } else if (beratBadanSaatIni.isEmpty()) {
                binding.etBeratBadan.requestFocus()
            } else if (beratBadanDiinginkan.isEmpty()) {
                binding.etBeratBadanDiinginkan.requestFocus()
            } else if (jumlahKalori.isEmpty()) {
                binding.etJumlahTargetKaloriHarian.requestFocus()
            }
        } else {
            // Membuat objek data
            val data = hashMapOf(
                "username" to username,
                "email" to email,
                "password" to password,
                "nama" to nama,
                "beratBadanSaatIni" to beratBadanSaatIni,
                "beratBadanTarget" to beratBadanDiinginkan,
                "tujuanDiet" to tujuanDiet,
                "tanggalTarget" to tanggalTarget,
                "jumlahTargetKalori" to jumlahKalori,
                "role" to "user"
            )

            // Menyimpan data ke Firestore di koleksi "users"
            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "Data berhasil disimpan dengan ID: ${documentReference.id}")

                    Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
                    Toast.makeText(requireContext(), "Silahkan Masuk", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Gagal menyimpan data", e)

                    Toast.makeText(requireContext(), "Gagal menyimpan data", Toast.LENGTH_SHORT).show()
                }
        }
        }

}