package ru.mik0war.flaskapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val service: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getUsers(): List<UserData>{
        return withContext(dispatcher){
            return@withContext service.getUsers()
        }
    }

    suspend fun getUser(id: Int): UserData{
        return withContext(dispatcher){
            return@withContext service.getUser(id)
        }
    }

    suspend fun createUser(login: String, password: String){
        withContext(dispatcher) {
            service.createUser(UserData(login, password, "/uploads/СДО.PNG"))
        }
    }
}