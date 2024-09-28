package ru.mik0war.flaskapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import ru.mik0war.flaskapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {

            val login = binding.editTextText.text.toString()
            val pass = binding.editTextText2.text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.9:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(UserService::class.java)

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    withContext(Dispatchers.IO) {
                        service.createUser(UserData(login, pass, "/uploads/СДО.PNG"))
                    }

                    binding.editTextText.text.clear()
                    binding.editTextText2.text.clear()
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity, "Login already exists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        binding.buttonToList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

    }
}