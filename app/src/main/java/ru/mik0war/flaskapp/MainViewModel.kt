package ru.mik0war.flaskapp

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mik0war.flaskapp.databinding.ActivityMainBinding

class MainViewModel(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun createUser(binding: ActivityMainBinding) = viewModelScope.launch(dispatcher) {
        try {
            val login = binding.editTextText.text.toString()
            val pass = binding.editTextText2.text.toString()

            repository.createUser(login, pass)

            binding.editTextText.text.clear()
            binding.editTextText2.text.clear()
        } catch (e: Exception) {
            Toast.makeText(
                binding.root.context, "Login already exists",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}