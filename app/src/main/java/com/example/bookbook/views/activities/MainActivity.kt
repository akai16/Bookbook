package com.example.bookbook.views.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bookbook.R
import com.example.bookbook.adapters.BookSearchAdapter
import com.example.bookbook.views.fragments.SearchBookFragment
import com.example.bookbook.views.fragments.SearchFragment
import com.example.bookbook.views.fragments.UserProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val profileFragment = UserProfileFragment()
    val searchFragment = SearchFragment()
    var active: Fragment = profileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, searchFragment)
            .hide(searchFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, profileFragment)
            .commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(active)
                        .show(profileFragment)
                        .commit()
                    active = profileFragment
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_search-> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(active)
                        .show(searchFragment)
                        .commit()
                    active = searchFragment
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_map-> {
                    Toast.makeText(this, "Map -> Not implemented yet", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }

                else -> {showFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener false
                }

            }
        }

        // Open Activity with Profile Tab
        bottomNavigationView.selectedItemId = R.id.nav_profile

        // Avoid reselecting the current page, thus avoiding fragment recreation
        bottomNavigationView.setOnNavigationItemReselectedListener {}

    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.frame_container, fragment
        ).commit()
    }

}
