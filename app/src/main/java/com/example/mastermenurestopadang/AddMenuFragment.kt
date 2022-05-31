package com.example.mastermenurestopadang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment

class AddMenuFragment : Fragment() {
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


    }
}