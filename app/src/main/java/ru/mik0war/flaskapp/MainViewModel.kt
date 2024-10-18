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
            val login = binding.loginText.text.toString()
            val pass = binding.passwordText.text.toString()

            repository.createUser(login, pass)

            binding.loginText.text.clear()
            binding.passwordText.text.clear()
        } catch (e: Exception) {
            Toast.makeText(
                binding.root.context, "Login already exists",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}