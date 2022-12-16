package hs.project.locationinviewmodel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import hs.project.viewmodeltest.R
import hs.project.viewmodeltest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private val homeFragment by lazy {
        HomeFragment()
    }

    private val secondFragment by lazy {
        SecondFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener(this)
        binding.btnSecond.setOnClickListener(this)

        replaceFragment(homeFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.container, fragment).commitAllowingStateLoss()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnHome.id -> {
                replaceFragment(homeFragment)
            }
            binding.btnSecond.id -> {
                replaceFragment(secondFragment)
            }
        }
    }

}