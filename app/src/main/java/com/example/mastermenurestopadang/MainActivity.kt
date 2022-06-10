package com.example.mastermenurestopadang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    var users: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras != null){
            val bundle = intent.extras
            if (bundle != null) {
                users = bundle.getParcelableArrayList<User>("listUser") as ArrayList<User>
            }
        }

        Toast.makeText(this, "Welcome Admin!!", Toast.LENGTH_SHORT).show()
        
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
            else if (it.itemId == R.id.update_bottom_nav_menu) {
                val fragment = UpdateMenuFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).setReorderingAllowed(true).commit()
            }
            else if (it.itemId == R.id.delete_bottom_nav_menu) {
                val fragment = DeleteMenuFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).setReorderingAllowed(true).commit()
            }
            else if (it.itemId == R.id.list_user_bottom_nav_menu) {
                val fragment = UserFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("listUser", users)
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).setReorderingAllowed(true).commit()
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
            val bundle = Bundle()
            bundle.putParcelableArrayList("listUser", users)
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        return true
    }
}