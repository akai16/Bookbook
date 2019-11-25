package com.example.bookbook.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bookbook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> {
                    showFragment(UserProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_map-> {
                    Toast.makeText(this, "Map -> Not implemented yet", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_search-> {
                    Toast.makeText(this, "Search -> Not implemented yet", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    return@setOnNavigationItemSelectedListener false
                }

            }
        }

    }


    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.frame_container, fragment
        ).commit()
    }

}
