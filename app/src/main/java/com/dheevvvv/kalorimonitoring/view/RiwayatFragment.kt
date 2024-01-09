package com.dheevvvv.kalorimonitoring.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentRiwayatBinding
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.room_database.MakananDikonsumsiData
import com.dheevvvv.kalorimonitoring.view.adapter.MakananDikonsumsiAdapter
import com.dheevvvv.kalorimonitoring.viewmodel.MakananDikonsumsiViewModel
import kotlinx.coroutines.launch


class RiwayatFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatBinding
    private lateinit var adapter: MakananDikonsumsiAdapter
    private val makananDikonsumsiViewModel:MakananDikonsumsiViewModel by viewModels()
    private lateinit var userManager: UserManager
    var email:String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRiwayatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userManager = UserManager.getInstance(requireContext())

        binding.btnTambahMakanan.setOnClickListener {
            findNavController().navigate(R.id.action_riwayatFragment_to_tambahMakananFragment)
        }

        lifecycleScope.launch {
            userManager.userEmail.collect{
                if (it!=null){
                    email = it.toString()
                }
            }.toString()
        }.toString()

        makananDikonsumsiViewModel.getMakananDikonsumsi()
        makananDikonsumsiViewModel.listMakananDikonsumsi.observe(viewLifecycleOwner, Observer {
            if (it!= null){
                adapter = MakananDikonsumsiAdapter(it)
                binding.rvRiwayatMakanan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvRiwayatMakanan.adapter = adapter

                adapter.onEditClick = {item->
                    val namaMakanan = item.namaMakanan
                    val bundle = Bundle()
                    bundle.putString("namaMakanan", namaMakanan)
                    bundle.putString("jumlahKalori", item.jumlahKalori)
                    bundle.putString("takaranSaji", item.takaranSaji)
                    findNavController().navigate(R.id.action_riwayatFragment_to_formUpdateMakananFragment, bundle)
                }
                adapter.onDeleteClick = {item->
                    AlertDialog.Builder(requireContext())
                        .setTitle("Konfirmasi Hapus")
                        .setMessage("Apakah Anda yakin ingin menghapus ${item.namaMakanan}?")
                        .setPositiveButton("Ya") { _, _ ->
                            makananDikonsumsiViewModel.deleteMakananDikonsumsi(item)

                        }
                        .setNegativeButton("Tidak", null)
                        .show()
                }
            }
        })


    }



}