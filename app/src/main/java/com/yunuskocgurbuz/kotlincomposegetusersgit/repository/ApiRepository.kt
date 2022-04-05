package com.yunuskocgurbuz.kotlincomposegetusersgit.repository

import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersdetailmodel.UsersDetailModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel.UsersModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.service.ServiceAPI
import com.yunuskocgurbuz.kotlincomposeimageapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ApiRepository @Inject constructor(
    private val api: ServiceAPI
){

    suspend fun getUsersList(): Resource<UsersModel> {
        val response = try {
            api.getUsersList()

        }catch (e: Exception){


            return Resource.Error("Error API connect!!!" + e.toString())

        }
        return Resource.Success(response)
    }

    suspend fun getUserDetail(user_name: String): Resource<UsersDetailModel>{
        val response = try {
            api.getUserDetail(user_name)

        }catch (e: Exception){

            return Resource.Error("Error API connect!!")
        }
        return Resource.Success(response)
    }

}