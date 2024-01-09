package com.dheevvvv.kalorimonitoring.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dheevvvv.kalorimonitoring.databinding.ItemDaftarMakananBinding
import com.dheevvvv.kalorimonitoring.databinding.ItemRiwayatMakananBinding
import com.dheevvvv.kalorimonitoring.room_database.MakananDikonsumsiData

class MakananDikonsumsiAdapter(private val listMakananDikonsumsi: List<MakananDikonsumsiData>):RecyclerView.Adapter<MakananDikonsumsiAdapter.ViewHolder>() {
    var onClick: ((MakananDikonsumsiData)->Unit)? = null
    class ViewHolder(var binding: ItemRiwayatMakananBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRiwayatMakananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMakananDikonsumsi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listMakananDikonsumsi[position]
        holder.binding.tvNamaMakanan.text = list.namaMakanan
        holder.binding.tvJumlahKalori.text = list.jumlahKalori
        holder.binding.tvJumlahKalori.text = list.jumlahKalori
        holder.binding.onClickDetail.setOnClickListener {
            onClick?.invoke(list)
        }
    }

}