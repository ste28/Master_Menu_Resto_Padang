package com.example.mastermenurestopadang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    val users:ArrayList<User> = ArrayList()
    lateinit var loginFragment: LoginFragment
    lateinit var registerFragment: RegisterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginFragment = supportFragmentManager.findFragmentById(R.id.login_regist_frag) as LoginFragment
        registerFragment = supportFragmentManager.findFragmentById(R.id.login_regist_frag) as RegisterFragment

        registerFragment.onRegisterListener = { username, nama, alamat, password ->
            var u = users.find {
                return@find it.username == username
            }

            if (u != null) {
                Toast.makeText(this, "Username sudah pernah digunakan!", Toast.LENGTH_SHORT).show()
            } else {
                val u = User(username, nama, alamat, password)
                users.add(u)
                val fragment = UserFragment()
                val bundle = Bundle()
                bundle.putParcelable("user", u)

                fragment.arguments = bundle
                Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
            }
        }

        loginFragment.onLoginListener = {username, password ->
            if (username == "admin" && password == "admin") {
                Toast.makeText(this, "Login admin berhasil!", Toast.LENGTH_SHORT).show()
            }
            else {
                var u = users.find {
                    return@find it.username == username && it.password == password
                }

                if (u != null) {
//                    val intent = Intent(this, MainActivity2::class.java)
//                    intent.putExtra("user", u)
//                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Login gagal!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_login_register, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.login_menu) {
            val fragment = LoginFragment()
            supportFragmentManager.beginTransaction().replace(R.id.login_regist_frag, fragment).setReorderingAllowed(true).commit()
        } else if (item.itemId == R.id.register_menu) {
            val fragment = RegisterFragment()
            supportFragmentManager.beginTransaction().replace(R.id.login_regist_frag, fragment).setReorderingAllowed(true).commit()
        }
        return true
    }
}