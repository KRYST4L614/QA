package com.example.randomuser.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.randomuser.R
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.databinding.ActivityMainBinding
import com.example.randomuser.ui.fragments.MainFragment
import com.example.randomuser.ui.fragments.UserInfoFragment

class MainActivity : AppCompatActivity(), MainFragment.Callback {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment.newInstance())
                .commit()
        }
    }

    override fun userSelected(userEntity: UserEntity) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, UserInfoFragment.newInstance(userEntity))
            .addToBackStack(null)
            .commit()
    }
}