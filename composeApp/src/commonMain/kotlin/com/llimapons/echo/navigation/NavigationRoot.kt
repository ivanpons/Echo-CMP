package com.llimapons.echo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.llimapons.auth.presentation.navigation.AuthGraphRoutes
import com.llimapons.auth.presentation.navigation.authGraph

@Composable
fun NavigationRoot(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = AuthGraphRoutes.Graph
    ){
        authGraph(
            navController = navController,
            onLoginSuccess = {}
        )
    }
}