package com.llimapons.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.llimapons.auth.presentation.email_verification.EmailVerificationRoot
import com.llimapons.auth.presentation.register.RegisterRoot
import com.llimapons.auth.presentation.register_success.RegisterSuccessRoot

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onLoginSuccess: () -> Unit,
){
    navigation<AuthGraphRoutes.Graph>(
        startDestination = AuthGraphRoutes.Register
    ){
        composable<AuthGraphRoutes.Register> {
            RegisterRoot(
                onRegisterSuccess = { email ->
                    navController.navigate(AuthGraphRoutes.RegisterSuccess(email))
                }
            )
        }
        composable<AuthGraphRoutes.RegisterSuccess> {
            RegisterSuccessRoot()
        }

        composable<AuthGraphRoutes.EmailVerification>(
            deepLinks = listOf(
                navDeepLink {
                    this.uriPattern = "https://echo.llimapons.com/api/auth/verify?token={token}"
                },
                navDeepLink {
                    this.uriPattern = "echo://echo.llimapons.com/api/auth/verify?token={token}"
                },
            )
        ) {
            EmailVerificationRoot()
        }
    }

}