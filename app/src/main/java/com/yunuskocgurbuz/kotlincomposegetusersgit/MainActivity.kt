package com.yunuskocgurbuz.kotlincomposegetusersgit

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yunuskocgurbuz.kotlincomposegetusersgit.ui.theme.KotlinComposeGetUsersGITTheme
import com.yunuskocgurbuz.kotlincomposegetusersgit.view.FavoriteUsersScreen
import com.yunuskocgurbuz.kotlincomposegetusersgit.view.UserDetailScreen
import com.yunuskocgurbuz.kotlincomposegetusersgit.view.UsersListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinComposeGetUsersGITTheme {

                val navController = rememberNavController()
                NavHost(navController =  navController, startDestination = "users_list_screen"){

                    composable("users_list_screen"){

                        UsersListScreen(navController)

                    }

                    composable("favorite_users_screen"){

                        FavoriteUsersScreen()

                    }

                    composable("user_detail_screen/{user_name}", arguments = listOf(
                        navArgument("user_name"){
                            type = NavType.StringType
                        }
                    )){
                        val user_name = remember {
                            it.arguments?.getString("user_name")
                        }

                        UserDetailScreen(user_name ?: "")
                    }

                }

            }
        }
    }
}

