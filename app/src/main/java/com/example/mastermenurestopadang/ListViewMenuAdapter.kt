package com.example.mastermenurestopadang

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListViewMenuAdapter (
    context: Context,
    private val layout:Int,
    private val listMenuPadang:List<MenuPadang>
) : ArrayAdapter<MenuPadang>(context, layout, listMenuPadang) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        lateinit var holder: MenuPadangHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(layout, parent, false) as View
            holder = MenuPadangHolder(
                v.findViewById(R.id.menu_image_iv),
                v.findViewById(R.id.nama_tv),
                v.findViewById(R.id.deskripsi_tv),
                v.findViewById(R.id.harga_tv)
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as MenuPadangHolder
        }

        val menu = listMenuPadang[position]
        holder.menuIv.setImageResource(R.drawable.nasi_padang)
        holder.namaTv.text = menu.nama
        holder.deskripsiTv.text = menu.deskripsi
        holder.hargaTv.text = "Rp." + menu.harga.toString() + ",00"

        return v
    }
}

data class MenuPadangHolder(
    val menuIv: ImageView,
    val namaTv: TextView,
    val deskripsiTv: TextView,
    val hargaTv: TextView
)