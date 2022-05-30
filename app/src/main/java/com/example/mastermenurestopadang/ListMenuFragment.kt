package com.example.mastermenurestopadang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class ListMenuFragment : Fragment() {
    lateinit var namaMenuEt: EditText
    lateinit var cariButton: Button
    lateinit var menuLv: ListView
    val menu:ArrayList<MenuPadang> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_list_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuLv = view.findViewById(R.id.menu_lv)
        val menuAdapter:ListMenuAdapter = ListMenuAdapter(view.context, R.layout.list_menu_item, menu)
        menuLv.adapter = menuAdapter

        val strReq = object : StringRequest(
            Method.GET,
            "http://192.168.0.5:3000/api/menu",
            Response.Listener {
//                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                val response = JSONArray(it)
                menu.clear()
                for (i in 0 until response.length()) {
                    val m: JSONObject = response.getJSONObject(i)
                    val id = m.getString("id")
                    val nama = m.getString("nama")
                    val deskripsi = m.getString("deskripsi")
                    val harga = m.getInt("harga")
                    val newMenu = MenuPadang(id, nama, deskripsi, harga)
                    menu.add(newMenu)
                }
                menuAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                it.printStackTrace()
                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(view.context)
        queue.add(strReq)
    }
}