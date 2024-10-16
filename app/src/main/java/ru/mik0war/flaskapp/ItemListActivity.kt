package ru.mik0war.flaskapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mik0war.flaskapp.databinding.ActivityItemListBinding

class ItemListActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val viewModel = (application as UsersApp).itemListViewModel

        val id = intent.extras!!.getInt("user_id")
        viewModel.getUserData(id, binding)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }
}