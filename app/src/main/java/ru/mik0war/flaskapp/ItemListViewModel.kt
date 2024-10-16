package ru.mik0war.flaskapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mik0war.flaskapp.databinding.ActivityItemListBinding

class ItemListViewModel(
    private val repository: Repository,
    private val baseUrl: String
) : ViewModel() {

    fun getUserData(id: Int, binding: ActivityItemListBinding) = viewModelScope.launch(Dispatchers.Main){

        val userData = repository.getUser(id)

        binding.passView.text = userData.password
        binding.loginView.text = userData.login

        Picasso.get()
            .load(baseUrl + userData.link)
            .into(binding.imageView)

    }

}