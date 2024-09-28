package ru.mik0war.flaskapp

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mik0war.flaskapp.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    lateinit var binding : ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = UsersListAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.2.110:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(UserService::class.java)

            val usersData = withContext(Dispatchers.IO){
                return@withContext service.getUsers()
            }

            adapter.update(usersData)
        }

        binding.buttonToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}