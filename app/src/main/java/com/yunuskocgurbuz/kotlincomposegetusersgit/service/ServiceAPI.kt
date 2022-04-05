package com.yunuskocgurbuz.kotlincomposegetusersgit.service

import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersdetailmodel.UsersDetailModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel.UsersModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceAPI {



    @GET("search/users?q=lagos&page=1")
    suspend fun getUsersList(): UsersModel

    //https://api.github.com/users/alagos
    @GET("users/{user_name}")
    suspend fun getUserDetail(
        @Path(value = "user_name", encoded = true) user_name: String,
    ): UsersDetailModel




}