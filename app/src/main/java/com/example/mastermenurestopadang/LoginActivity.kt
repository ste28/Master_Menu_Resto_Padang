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

        supportActionBar?.setTitle("Welcome to The La'Uda Padang")

        if (intent.extras != null){
            val bundle = intent.extras
            if (bundle != null) {
                users = bundle.getParcelableArrayList<User>("listUser") as ArrayList<User>
            }
        }
        
        loginFragment = supportFragmentManager.findFragmentById(R.id.login_frag) as LoginFragment
        loginFragment.onLoginListener = {username, password ->
            if (username == "admin" && password == "admin") {
                Toast.makeText(this, "Welcome Admin!!", Toast.LENGTH_SHORT).show()
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
                    val bundle = Bundle()
                    bundle.putParcelable("user", u)
                    val intent = Intent(this, UserActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Username atau Password anda salah!", Toast.LENGTH_SHORT).show()
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