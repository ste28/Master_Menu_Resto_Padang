package com.example.mastermenurestopadang

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.net.URI


class ProfileFragment : Fragment() {
    lateinit var profileUsernameTextView: TextView
    lateinit var profileNamaTextView: TextView
    lateinit var profileAlamatTextView: TextView
    lateinit var contactCSButton: Button
    var user:User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileUsernameTextView = view.findViewById(R.id.profileUsername_tv)
        profileNamaTextView = view.findViewById(R.id.profileNama_tv)
        profileAlamatTextView = view.findViewById(R.id.profileAlamat_tv)
        contactCSButton = view.findViewById(R.id.callCS_btn)

        user = arguments?.getParcelable("user")
        profileUsernameTextView.setText("Username: " + user?.username)
        profileNamaTextView.setText("Nama: " + user?.nama)
        profileAlamatTextView.setText("Alamat: " + user?.alamat)

        contactCSButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:03124567898"))
            startActivity(intent)
        }
    }
}