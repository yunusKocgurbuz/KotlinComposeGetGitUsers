package com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel

import androidx.lifecycle.ViewModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersdetailmodel.UsersDetailModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel.UsersModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.repository.ApiRepository
import com.yunuskocgurbuz.kotlincomposeimageapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    suspend fun getUserDetail(user_name: String): Resource<UsersDetailModel> {
        return repository.getUserDetail(user_name)
    }

}