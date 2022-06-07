package com.example.mastermenurestopadang

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListMenuAdapter(
    val context: Context,
    val listMenu: List<MenuPadang>
) : RecyclerView.Adapter<ListMenuAdapter.ListViewHolder>(){
    class ListViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var menuImageView: ImageView = view.findViewById(R.id.menu_image_iv)
        var namaMenuTv: TextView = view.findViewById(R.id.nama_tv)
        var deskripsiMenuTv: TextView = view.findViewById(R.id.deskripsi_tv)
        var hargaMenuTv: TextView = view.findViewById(R.id.harga_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.list_menu_item, parent, false)
        val menuHolder:ListViewHolder = ListViewHolder(item)
        return menuHolder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.menuImageView.setImageResource(R.drawable.nasi_padang)
        holder.namaMenuTv.text = menu.nama
        holder.deskripsiMenuTv.text = menu.deskripsi
        holder.hargaMenuTv.text = "Rp. " + menu.harga + ",00"
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}