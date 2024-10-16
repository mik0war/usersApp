package ru.mik0war.flaskapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mik0war.flaskapp.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    lateinit var binding : ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val baseUrl = (application as UsersApp).baseUrl

        val lambda : (id: Int) -> Unit = { id ->

            val intent = Intent(this, ItemListActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("user_id", id)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        val adapter = UsersListAdapter(baseUrl, emptyList(), lambda)
        binding.recyclerView.adapter = adapter

        val listViewModel = (application as UsersApp).listViewModel

        listViewModel.showUsers(adapter)

        binding.buttonToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}