package com.example.mastermenurestopadang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {
    lateinit var usernameEt: EditText
    lateinit var namaUserEt: EditText
    lateinit var alamatEt: EditText
    lateinit var passwordEt: EditText
    lateinit var registerBtn: Button
    var onRegisterListener:((username:String, nama:String, alamat:String, password:String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameEt = view.findViewById(R.id.usernameRegist_et)
        namaUserEt = view.findViewById(R.id.namaRegist_et)
        alamatEt = view.findViewById(R.id.alamatRegist_et)
        passwordEt = view.findViewById(R.id.passwordRegist_et)
        registerBtn = view.findViewById(R.id.do_regist_btn)

        registerBtn.setOnClickListener {
            val username = usernameEt.text.toString()
            val nama = namaUserEt.text.toString()
            val alamat = alamatEt.text.toString()
            val password = passwordEt.text.toString()
            onRegisterListener?.invoke(username, nama, alamat, password)
        }
    }
}