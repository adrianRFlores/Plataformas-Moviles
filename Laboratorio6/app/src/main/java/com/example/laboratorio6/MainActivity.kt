package com.example.laboratorio6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav_bottomNavActivity)
        setCurrentFragment(HomeFragment())

        if (savedInstanceState == null) {
            setCurrentFragment(HomeFragment())
        }

        setListeners()
    }

    private fun setListeners() {
        bottomNav.setOnItemSelectedListener {
            // Dependiendo el item del menu, mostramos el fragment deseado
            when(it.itemId) {
                R.id.item_home -> setCurrentFragment(HomeFragment())
                R.id.item_search -> setCurrentFragment(SearchFragment())
                R.id.item_yourLibrary -> setCurrentFragment(LibraryFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment)
        }
    }

}