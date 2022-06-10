package com.example.mastermenurestopadang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {
    lateinit var userBottomNavigationView: BottomNavigationView
    var user:User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        if (intent.extras != null) {
            val bundle = intent.extras
            if (bundle != null) {
                user = bundle.getParcelable<User>("user")!!
            }
        }

        Toast.makeText(this, "Welcome ${user?.nama}", Toast.LENGTH_SHORT).show()

        val fragment = ListMenuFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView3, fragment).setReorderingAllowed(true).commit()

        userBottomNavigationView = findViewById(R.id.bottomNavUser)
        userBottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.list_menu_bottom_nav_menu) {
                val fragment = ListMenuFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView3, fragment).setReorderingAllowed(true).commit()
            }
            else if (it.itemId == R.id.profile_user_bottom_nav_menu) {
                val fragment = ProfileFragment()
                val bundle = Bundle()
                bundle.putParcelable("user", user)
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView3, fragment).setReorderingAllowed(true).commit()
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_user_admin, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_menu) {
            finish()
        }
        return true
    }
}