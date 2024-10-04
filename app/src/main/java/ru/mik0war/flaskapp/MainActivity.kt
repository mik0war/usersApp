package ru.mik0war.flaskapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mik0war.flaskapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val mainViewModel = (application as UsersApp).mainViewModel

        binding.button.setOnClickListener {
            mainViewModel.createUser(binding)
        }

        binding.buttonToList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

    }
}