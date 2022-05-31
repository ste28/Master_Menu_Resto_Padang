package com.example.mastermenurestopadang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class DeleteMenuFragment : Fragment() {
    lateinit var idDeleteSpinner: Spinner
    lateinit var namaMenuDeleteEt: EditText
    lateinit var deskripsiMenuDeleteEt: EditText
    lateinit var hargaMenuDeleteEt: EditText
    lateinit var deleteBtn: Button
    lateinit var showHasilDelete: ListView
    lateinit var idSpinnerAdapter: ArrayAdapter<String>
    var idx = -1
    val menuDelete: ArrayList<MenuPadang> = ArrayList()
    val menuDiDelete: ArrayList<MenuPadang> = ArrayList()
    val menuId: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_delete_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idDeleteSpinner = view.findViewById(R.id.idMenuDelete_spin)
        namaMenuDeleteEt = view.findViewById(R.id.nama_delete_et)
        deskripsiMenuDeleteEt = view.findViewById(R.id.deskripsi_delete_et)
        hargaMenuDeleteEt = view.findViewById(R.id.harga_delete_et)
        deleteBtn = view.findViewById(R.id.delete_btn)
        showHasilDelete = view.findViewById(R.id.showHasilDelete_lv)
        val menuAdapter:ListMenuAdapter = ListMenuAdapter(view.context, R.layout.list_menu_item, menuDiDelete)
        showHasilDelete.adapter = menuAdapter
        idSpinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, menuId)
        idDeleteSpinner.adapter = idSpinnerAdapter

        val strReq = object : StringRequest(
            Method.GET,
            "http://192.168.0.5:3000/api/menu",
            Response.Listener {
                val response = JSONArray(it)
                for (i in 0 until response.length()) {
                    val m: JSONObject = response.getJSONObject(i)
                    val id = m.getString("id")
                    val nama = m.getString("nama")
                    val deskripsi = m.getString("deskripsi")
                    val harga = m.getInt("harga")
                    val newMenu = MenuPadang(id, nama, deskripsi, harga)
                    menuId.add(id)
                    menuDelete.add(newMenu)
                }
                idSpinnerAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                it.printStackTrace()
                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(view.context)
        queue.add(strReq)

        idDeleteSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                deleteEt(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        deleteBtn.setOnClickListener {
            deleteMenu(view, menuAdapter, idx)
        }
    }

    fun deleteEt(i:Int) {
        if (i >= 0) {
            idx = i
            namaMenuDeleteEt.setText(menuDelete[i].nama)
            deskripsiMenuDeleteEt.setText(menuDelete[i].deskripsi)
            hargaMenuDeleteEt.setText(menuDelete[i].harga.toString())
        }
    }

    fun deleteMenu(view: View, menuAdapter: ListMenuAdapter, i:Int) {
        if (i >= 0) {
            val index = i + 1
            val id = "F00$index"
            val strReq = object : StringRequest(
                Method.DELETE,
                "http://192.168.0.5:3000/api/menu/$id",
                Response.Listener {
                    val m = JSONObject(it)
                    val id = m.getString("id")
                    val nama = m.getString("nama")
                    val deskripsi = m.getString("deskripsi")
                    val harga = m.getInt("harga")
                    val men = MenuPadang(id, nama, deskripsi, harga)
                    menuDiDelete.add(men)
                    menuAdapter.notifyDataSetChanged()
                },
                Response.ErrorListener {
                    it.printStackTrace()
                    Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
                }
            ){}
            val queue: RequestQueue = Volley.newRequestQueue(view.context)
            queue.add(strReq)
            menuId.removeAt(i)
            idSpinnerAdapter.notifyDataSetChanged()
        }
    }
}