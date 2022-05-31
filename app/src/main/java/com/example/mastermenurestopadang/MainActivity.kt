package com.example.mastermenurestopadang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ListMenuFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).setReorderingAllowed(true).commit()

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.list_bottom_nav_menu) {
                val fragment = ListMenuFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).setReorderingAllowed(true).commit()
            }
            else if (it.itemId == R.id.add_bottom_nav_menu) {
                val fragment = AddMenuFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).setReorderingAllowed(true).commit()
            }
            return@setOnItemSelectedListener true
        }
    }
}