package com.yunuskocgurbuz.kotlincomposegetusersgit.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersdetailmodel.UsersDetailModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel.UserDetailViewModel
import com.yunuskocgurbuz.kotlincomposeimageapp.util.Resource

@Composable
fun UserDetailScreen(user_name: String,
                     viewModel: UserDetailViewModel = hiltViewModel()
                     ) {

    val userDetail by produceState<Resource<UsersDetailModel>>(initialValue = Resource.Loading()){
        value = viewModel.getUserDetail(user_name)
    }
    
    UserDetail(userDetail)

}

@Composable
fun UserDetail(userDetail: Resource<UsersDetailModel>) {

    LazyColumn() {
        item{
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Column {
                    when(userDetail) {

                        is Resource.Success -> {

                            val selectedUser = userDetail.data!!

                            Image(painter = rememberImagePainter(data = selectedUser.avatar_url),
                                contentDescription = "image",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(250.dp)
                            )

                            Column {

                                Text(text = selectedUser.name ?: "",
                                    modifier = Modifier.padding(7.dp),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Left
                                )

                                Text(text = selectedUser.company ?: "",
                                    modifier = Modifier.padding(7.dp),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Left
                                )

                                Text(text = selectedUser.location ?: "",
                                    modifier = Modifier.padding(7.dp),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Left
                                )

                                Text(text = selectedUser.created_at ?: "",
                                    modifier = Modifier.padding(7.dp),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Left
                                )

                                Text(text = selectedUser.updated_at ?: "",
                                    modifier = Modifier.padding(7.dp),
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Left
                                )

                            }
                        }

                        is Resource.Error -> {
                            Text(text = userDetail.message!!)
                        }

                        is Resource.Loading -> {
                            CircularProgressIndicator(
                                color = MaterialTheme.colors.primary,
                            )
                        }
                    }
                }
            }
        }
    }

}
