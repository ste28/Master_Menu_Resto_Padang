package com.example.mastermenurestopadang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {
    lateinit var usernameEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    var onLoginListener: ((username:String, password:String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameEt = view.findViewById(R.id.usernameLogin_et)
        passwordEt = view.findViewById(R.id.passwordLogin_et)
        loginBtn = view.findViewById(R.id.do_regist_btn)

        loginBtn.setOnClickListener {
            val username = usernameEt.text.toString()
            val password = passwordEt.text.toString()
            onLoginListener?.invoke(username, password)
        }
    }
}