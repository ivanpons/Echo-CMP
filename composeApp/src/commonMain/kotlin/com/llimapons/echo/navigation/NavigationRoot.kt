package com.llimapons.echo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.llimapons.auth.presentation.navigation.AuthGraphRoutes
import com.llimapons.auth.presentation.navigation.authGraph
import com.llimapons.chat.presentation.chat_list.ChatListRoute
import com.llimapons.chat.presentation.chat_list.ChatListScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any,
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        authGraph(
            navController = navController,
            onLoginSuccess = {
                navController.navigate(ChatListRoute){
                    popUpTo(AuthGraphRoutes.Graph){
                        inclusive = true
                    }
                }
            }
        )

        composable<ChatListRoute> {
            ChatListScreenRoot()
        }
    }
}