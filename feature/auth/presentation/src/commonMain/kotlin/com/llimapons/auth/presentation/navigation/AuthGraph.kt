package com.llimapons.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.llimapons.auth.presentation.email_verification.EmailVerificationRoot
import com.llimapons.auth.presentation.forgot_password.ForgotPasswordRoot
import com.llimapons.auth.presentation.login.LoginRoot
import com.llimapons.auth.presentation.register.RegisterRoot
import com.llimapons.auth.presentation.register_success.RegisterSuccessRoot
import com.llimapons.auth.presentation.reset_password.ResetPasswordRoot

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onLoginSuccess: () -> Unit,
) {
    navigation<AuthGraphRoutes.Graph>(
        startDestination = AuthGraphRoutes.Login
    ) {
        composable<AuthGraphRoutes.Login> {
            LoginRoot(
                onLoginSuccess = onLoginSuccess,
                onCreateAccountClick = {
                    navController.navigate(AuthGraphRoutes.Register) {
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(AuthGraphRoutes.ForgotPassword)
                }
            )
        }

        composable<AuthGraphRoutes.Register> {
            RegisterRoot(
                onRegisterSuccess = { email ->
                    navController.navigate(AuthGraphRoutes.RegisterSuccess(email))
                },
                onLoginClick = {
                    navController.navigate(AuthGraphRoutes.Login) {
                        popUpTo(AuthGraphRoutes.Register) {
                            inclusive = true
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        composable<AuthGraphRoutes.RegisterSuccess> {
            RegisterSuccessRoot(
                onLoginClick = {
                    navController.navigate(AuthGraphRoutes.Login) {
                        popUpTo(AuthGraphRoutes.RegisterSuccess) {
                            inclusive = true
                        }
                    }
                }
            )
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
            EmailVerificationRoot(
                onLoginClick = {
                    navController.navigate(AuthGraphRoutes.Login) {
                        popUpTo(AuthGraphRoutes.EmailVerification) {
                            inclusive = true
                        }
                    }
                },
                onCloseClick = {
                    navController.navigate(AuthGraphRoutes.Login) {
                        popUpTo(AuthGraphRoutes.EmailVerification) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<AuthGraphRoutes.ForgotPassword> {
            ForgotPasswordRoot()
        }

        composable<AuthGraphRoutes.ResetPassword>(
            deepLinks = listOf(
                navDeepLink {
                    this.uriPattern = "https://echo.llimapons.com/api/auth/reset-password?token={token}"
                },
                navDeepLink {
                    this.uriPattern = "echo://echo.llimapons.com/api/auth/reset-password?token={token}"
                },
            )
        )  {
            ResetPasswordRoot()
        }


    }

}