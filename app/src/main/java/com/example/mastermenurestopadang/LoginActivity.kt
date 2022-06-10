package com.example.mastermenurestopadang

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class LoginActivity : AppCompatActivity() {
    var users:ArrayList<User> = ArrayList()
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (intent.extras != null){
            val bundle = intent.extras
            if (bundle != null) {
                users = bundle.getParcelableArrayList<User>("listUser") as ArrayList<User>
            }
        }
        
        loginFragment = supportFragmentManager.findFragmentById(R.id.login_frag) as LoginFragment
        loginFragment.onLoginListener = {username, password ->
            if (username == "admin" && password == "admin") {
                val bundle = Bundle()
                bundle.putParcelableArrayList("listUser", users)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            else {
                var u = users.find {
                    return@find it.username == username && it.password == password
                }

                if (u != null) {
//                    val intent = Intent(this, MainActivity2::class.java)
//                    intent.putExtra("user", u)
//                    startActivity(intent)
                    for (i in 0 until users.count()){
                        Toast.makeText(this, users[i].username, Toast.LENGTH_SHORT).show()
                    }
                    Toast.makeText(this, "Login Sukses bro", Toast.LENGTH_SHORT).show()
                }
                else {
                    for (i in 0 until users.count()){
                        Toast.makeText(this, users[i].username, Toast.LENGTH_SHORT).show()
                    }
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
            val bundle = Bundle()
            bundle.putParcelableArrayList("listUser", users)
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        } else if (item.itemId == R.id.register_menu) {
            val bundle = Bundle()
            bundle.putParcelableArrayList("listUser", users)
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        return true
    }
}