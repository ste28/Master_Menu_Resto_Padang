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
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class AddMenuFragment : Fragment() {
    lateinit var idAddEt: EditText
    lateinit var namaMenuAddEt: EditText
    lateinit var deskripsiMenuAddEt: EditText
    lateinit var hargaMenuAddEt: EditText
    lateinit var tambahBtn: Button
    lateinit var showHasil: ListView
    val menuAdd: ArrayList<MenuPadang> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idAddEt = view.findViewById(R.id.id_add_et)
        namaMenuAddEt = view.findViewById(R.id.nama_delete_et)
        deskripsiMenuAddEt = view.findViewById(R.id.deskripsi_delete_et)
        hargaMenuAddEt = view.findViewById(R.id.harga_delete_et)
        tambahBtn = view.findViewById(R.id.delete_btn)
        showHasil = view.findViewById(R.id.showHasilDelete_lv)
        val menuAdapter:ListViewMenuAdapter = ListViewMenuAdapter(view.context, R.layout.list_menu_item, menuAdd)
        showHasil.adapter = menuAdapter

        tambahBtn.setOnClickListener {
            val strReq = object : StringRequest(
                Method.POST,
                "http://192.168.0.5:3000/api/menu",
                Response.Listener {
                    val m = JSONObject(it)
                    val id = m.getString("id")
                    val nama = m.getString("nama")
                    val deskripsi = m.getString("deskripsi")
                    val harga = m.getInt("harga")
                    val men = MenuPadang(id, nama, deskripsi, harga)
                    menuAdd.add(men)
                    menuAdapter.notifyDataSetChanged()
                    refreshEt(idAddEt, namaMenuAddEt, deskripsiMenuAddEt, hargaMenuAddEt)
                },
                Response.ErrorListener {
                    it.printStackTrace()
                    Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val param:MutableMap<String, String> = HashMap()
                    param["id"] = idAddEt.text.toString()
                    param["nama"] = namaMenuAddEt.text.toString()
                    param["deskripsi"] = deskripsiMenuAddEt.text.toString()
                    param["harga"] = hargaMenuAddEt.text.toString()
                    return param
                }
            }
            val queue: RequestQueue = Volley.newRequestQueue(view.context)
            queue.add(strReq)
        }
    }

    fun refreshEt(idAddEt: EditText, namaMenuAddEt: EditText, deskripsiMenuAddEt: EditText, hargaMenuAddEt: EditText) {
        idAddEt.setText("")
        namaMenuAddEt.setText("")
        deskripsiMenuAddEt.setText("")
        hargaMenuAddEt.setText("")
    }
}