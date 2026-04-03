package com.llimapons.echo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.llimapons.auth.presentation.navigation.AuthGraphRoutes
import com.llimapons.chat.presentation.chat_list.ChatListRoute
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.core.presentation.util.ObserveAsEvents
import com.llimapons.echo.navigation.DeeplinkListener
import com.llimapons.echo.navigation.NavigationRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    onAuthenticationChecked: () -> Unit = {},
    viewModel: MainViewModel = koinViewModel()
) {

    val navController = rememberNavController()
    DeeplinkListener(navController)

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isCheckingAuth){
        if (!state.isCheckingAuth){
            onAuthenticationChecked()
        }
    }

    ObserveAsEvents(viewModel.event){ event ->
        when(event){
            MainEvent.OnSessionExpired -> {
                navController.navigate(AuthGraphRoutes.Graph){
                    popUpTo(AuthGraphRoutes.Graph){
                        inclusive = false
                    }
                }
            }
        }
    }

    EchoTheme {
        if (!state.isCheckingAuth) {
            NavigationRoot(
                navController = navController,
                startDestination = if (state.isLoggedIn) {
                    ChatListRoute
                } else {
                    AuthGraphRoutes.Graph
                }
            )
        }
    }
}