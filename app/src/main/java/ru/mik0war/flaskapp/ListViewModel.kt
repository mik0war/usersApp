package ru.mik0war.flaskapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun showUsers(adapter: UsersListAdapter) = viewModelScope.launch(dispatcher) {
        val usersData = repository.getUsers()

        adapter.update(usersData)
    }
}