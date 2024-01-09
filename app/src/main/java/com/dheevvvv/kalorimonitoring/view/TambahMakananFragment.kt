package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentTambahMakananBinding
import com.dheevvvv.kalorimonitoring.view.adapter.DaftarMakananAdapter
import com.dheevvvv.kalorimonitoring.view.adapter.MakananDikonsumsiAdapter
import com.dheevvvv.kalorimonitoring.viewmodel.DaftarMakananViewModel


class TambahMakananFragment : Fragment() {
    private lateinit var binding: FragmentTambahMakananBinding
    private val daftarMakananViewModel:DaftarMakananViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTambahMakananBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daftarMakananViewModel.fetchListMakanan()
        daftarMakananViewModel.makananList.observe(viewLifecycleOwner, Observer {
            val adapter = DaftarMakananAdapter(it)
            binding.rvDaftarMakanan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvDaftarMakanan.adapter = adapter
            adapter.onClick = {item->
                val namaMakanan = item.namaMakanan
                val bundle = Bundle()
                bundle.putString("namaMakanan", namaMakanan)
                bundle.putString("jumlahKalori", item.jumlahKalori)
                bundle.putString("takaranSaji", item.takaranSaji)
                findNavController().navigate(R.id.action_tambahMakananFragment_to_formTambahMakananFragment, bundle)
            }
        })
    }


}