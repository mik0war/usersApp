package ru.mik0war.flaskapp

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("/user/create/mob")
    suspend fun createUser(@Body userData: UserData)

    @GET("/user/all")
    suspend fun getUsers() : List<UserData>
}