package com.yunuskocgurbuz.kotlincomposegetusersgit.view

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.yunuskocgurbuz.kotlincomposegetusersgit.entity.FavoriteUsersEntity
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel.Item
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel.UsersModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel.FavoriteUsersViewModelFactory
import com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel.SQLiteViewModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel.UsersListViewModel

@Composable
fun UsersListScreen(navController: NavController) {

    //for SQLite connection
    val context = LocalContext.current
    val sqliteViewModel: SQLiteViewModel = viewModel(
        factory = FavoriteUsersViewModelFactory(context.applicationContext as Application)
    )


    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Users",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth()

                )

                Button(onClick = {
                    navController.navigate(
                        "favorite_users_screen"
                    )
                }) {
                    Text(text = "Go to my favorite")
                }

            Spacer(modifier = Modifier.height(5.dp))


            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            )

            LoadUsersList(navController, sqliteViewModel)

        }
    }
}






@Composable
fun LoadUsersList(navController: NavController,sqliteViewModel: SQLiteViewModel, viewModel: UsersListViewModel = hiltViewModel()){

    val usersList by remember { viewModel.usersList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (errorMessage.isNotEmpty()) {
            RetryViewMessage(error = errorMessage) {
            }
        }
    }

    Column() {
        LoadUsersList(navController, usersList, sqliteViewModel)
    }

}


@Composable
fun LoadUsersList(navController: NavController, usersList: List<Item>, sqliteViewModel: SQLiteViewModel) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        var refreshing by remember { mutableStateOf(false) }
        LaunchedEffect(refreshing) {
            if (refreshing) {
                // delay(100)
                refreshing = false
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshing),
            onRefresh = { refreshing = true }
        ) {

            Column() {

                LazyColumn(
                ) {
                    items(usersList) { usersList ->
                        UsersListRow(usersList, navController, sqliteViewModel)
                    }
                }

            }
        }
    }
}

@Composable
fun UsersListRow(usersList: Item, navController: NavController, sqliteViewModel: SQLiteViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                navController.navigate(
                    "user_detail_screen/${usersList.login}"
                )
            },
        backgroundColor = Color.LightGray,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {

        Row{
            Image(
                painter = rememberImagePainter(data = usersList.avatar_url),
                contentDescription = "image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(10.dp, 0.dp, 0.dp, 0.dp)),
                contentScale = ContentScale.FillBounds
            )

            Column{

                Text(
                    text = usersList.login,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(3.dp, 20.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                val insertFavoriteUserData = listOf(FavoriteUsersEntity(usersList.id.toLong() ,usersList.login))

                Button(onClick = {
                    sqliteViewModel.AddFavoriteUser(insertFavoriteUserData)
                }) {
                    Text(text = "My Favorite")

                }


            }
        }

    }
    
}


@Composable
fun RetryViewMessage(
    error: String,
    onRetry: () -> Unit
) {
    Column() {
        Spacer(modifier = Modifier.height(10.dp))
        Text("Ops! Something went wrong.", color = Color.Red, fontSize = 20.sp)
    }
}