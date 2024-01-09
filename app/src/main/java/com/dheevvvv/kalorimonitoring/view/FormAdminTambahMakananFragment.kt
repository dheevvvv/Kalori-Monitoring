package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentFormAdminTambahMakananBinding
import com.google.firebase.firestore.FirebaseFirestore


class FormAdminTambahMakananFragment : Fragment() {
    private lateinit var binding: FragmentFormAdminTambahMakananBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormAdminTambahMakananBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTambahMakanan.setOnClickListener {
            simpan()
        }
    }

    private fun simpan(){
        val db = FirebaseFirestore.getInstance()

        val makananCollection = db.collection("makanan")

        val namaMakanan = binding.namaMakanan.text.toString()
        val jumlahKalori = binding.jumlahKalori.text.toString()
        val takaranSaji = binding.takaranSaji.text.toString()

        val dataMakanan = hashMapOf(
            "namaMakanan" to namaMakanan,
            "jumlahKalori" to jumlahKalori,
            "takaranSaji" to takaranSaji
        )


        makananCollection.add(dataMakanan)
            .addOnSuccessListener { documentReference ->

                val newDocumentId = documentReference.id
                findNavController().navigate(R.id.action_formAdminTambahMakananFragment_to_adminHomeFragment)
            }
            .addOnFailureListener { e ->
                //
                //
            }

    }

}