package com.dheevvvv.kalorimonitoring.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dheevvvv.kalorimonitoring.databinding.ItemDaftarMakananBinding
import com.dheevvvv.kalorimonitoring.model.DaftarMakanan

class DaftarMakananAdapter(private val listDaftarMakanan: List<DaftarMakanan>): RecyclerView.Adapter<DaftarMakananAdapter.ViewHolder>() {
    var onClick: ((DaftarMakanan)->Unit)? = null
    class ViewHolder(val binding: ItemDaftarMakananBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDaftarMakananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listDaftarMakanan.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listDaftarMakanan[position]
        holder.binding.tvNamaMakanan.text = list.namaMakanan
        holder.binding.tvJumlahKalori.text = list.jumlahKalori
        holder.binding.tvJumlahKalori.text = list.jumlahKalori
        holder.binding.tvJamMakan.text = list.jamMakan
        Glide.with(holder.itemView).load({{list.gambarPath}}).into(holder.binding.ivGambar)
        holder.binding.onClickDetail.setOnClickListener {
            onClick?.invoke(list)
        }
    }

}