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

class UpdateMenuFragment : Fragment() {
    lateinit var idSpinner: Spinner
    lateinit var namaMenuUpdateEt: EditText
    lateinit var deskripsiMenuUpdateEt: EditText
    lateinit var hargaMenuUpdateEt: EditText
    lateinit var updateBtn: Button
    lateinit var showHasilUpdate: ListView
    lateinit var idSpinnerAdapter: ArrayAdapter<String>
    var idx = -1
    val menuUpdate: ArrayList<MenuPadang> = ArrayList()
    val menuDiupdate: ArrayList<MenuPadang> = ArrayList()
    val menuId: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_update_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idSpinner = view.findViewById(R.id.idMenuDelete_spin)
        namaMenuUpdateEt = view.findViewById(R.id.nama_delete_et)
        deskripsiMenuUpdateEt = view.findViewById(R.id.deskripsi_delete_et)
        hargaMenuUpdateEt = view.findViewById(R.id.harga_delete_et)
        updateBtn = view.findViewById(R.id.delete_btn)
        showHasilUpdate = view.findViewById(R.id.showHasilDelete_lv)
        val menuAdapter:ListViewMenuAdapter = ListViewMenuAdapter(view.context, R.layout.list_menu_item, menuDiupdate)
        showHasilUpdate.adapter = menuAdapter
        idSpinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, menuId)
        idSpinner.adapter = idSpinnerAdapter

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
                    menuUpdate.add(newMenu)
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

        idSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateEt(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        updateBtn.setOnClickListener {
            updateMenu(view, menuAdapter, idx)
        }
    }

    fun updateEt(i:Int) {
        if (i >= 0) {
            idx = i
            namaMenuUpdateEt.setText(menuUpdate[i].nama)
            deskripsiMenuUpdateEt.setText(menuUpdate[i].deskripsi)
            hargaMenuUpdateEt.setText(menuUpdate[i].harga.toString())
        }
    }

    fun updateMenu(view: View, menuAdapter: ListViewMenuAdapter, i:Int) {
        if (i >= 0) {
            val index = i + 1
            val id = "F00$index"
            val strReq = object : StringRequest(
                Method.PUT,
                "http://192.168.0.5:3000/api/menu/$id",
                Response.Listener {
                    val m = JSONObject(it)
                    val id = m.getString("id")
                    val nama = m.getString("nama")
                    val deskripsi = m.getString("deskripsi")
                    val harga = m.getInt("harga")
                    val men = MenuPadang(id, nama, deskripsi, harga)
                    menuDiupdate.add(men)
                    menuAdapter.notifyDataSetChanged()
                    Toast.makeText(view.context, "Sukses mengubah $nama", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
                    it.printStackTrace()
                    Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val param:MutableMap<String, String> = HashMap()
                    param["nama"] = namaMenuUpdateEt.text.toString()
                    param["deskripsi"] = deskripsiMenuUpdateEt.text.toString()
                    param["harga"] = hargaMenuUpdateEt.text.toString()
                    return param
                }
            }
            val queue: RequestQueue = Volley.newRequestQueue(view.context)
            queue.add(strReq)
        }
    }
}