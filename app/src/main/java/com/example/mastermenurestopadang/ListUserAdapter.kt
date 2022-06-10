package com.example.mastermenurestopadang

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListUserAdapter (
    context: Context,
    private val layout:Int,
    val ListUser: List<User>
) : ArrayAdapter<User>(context, layout, ListUser){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        lateinit var holder: UserHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(layout, parent, false) as View
            holder = UserHolder(
                v.findViewById(R.id.user_image_iv),
                v.findViewById(R.id.usernameUser_tv),
                v.findViewById(R.id.namaUser_tv),
                v.findViewById(R.id.alamatUser_tv)
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as UserHolder
        }

        val user = ListUser[position]
        holder.userIv.setImageResource(R.drawable.image_removebg_preview__18_)
        holder.usernameTv.text = user.username
        holder.namaTv.text = "Alamat: " + user.nama
        holder.alamatTv.text = "Alamat: " + user.alamat

        return v
    }
}

data class UserHolder(
    val userIv: ImageView,
    val usernameTv: TextView,
    val namaTv: TextView,
    val alamatTv: TextView
)