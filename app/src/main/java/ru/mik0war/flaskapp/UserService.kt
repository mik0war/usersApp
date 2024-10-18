package ru.mik0war.flaskapp

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("/user/{user_id}")
    suspend fun getUser(@Path(value = "user_id", encoded = true) id: Int) : UserData

    @POST("/user/create/mob")
    suspend fun createUser(@Body userData: UserData)

    @GET("/user/all")
    suspend fun getUsers() : List<UserData>

}