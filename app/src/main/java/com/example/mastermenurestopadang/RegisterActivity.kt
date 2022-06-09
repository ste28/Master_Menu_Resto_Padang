package com.example.mastermenurestopadang

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
//    val users:ArrayList<User> = ArrayList()
    lateinit var registerFragment: RegisterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val users: ArrayList<User>? = intent.getStringArrayListExtra("listUser")

        registerFragment = supportFragmentManager.findFragmentById(R.id.regist_frag) as RegisterFragment
        registerFragment.onRegisterListener = {username, nama, alamat, password ->
            var u = users?.find {
                return@find it.username == username
            }

            if (u != null) {
                Toast.makeText(this, "Username sudah pernah digunakan!", Toast.LENGTH_SHORT).show()
            }
            else {
                val u = User(username, nama, alamat, password)
                users?.add(u)
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("newUser", users)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_login_register, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.login_menu) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.register_menu) {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}