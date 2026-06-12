package com.llimapons.chat.presentation.chat_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llimapons.core.domain.auth.AuthService
import com.llimapons.core.domain.auth.SessionStorage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ChatListViewModel(
//    private val repository: ChatRepository,
    private val sessionStorage: SessionStorage,
//    private val deviceTokenService: DeviceTokenService,
    private val authService: AuthService,
//    private val chatParticipantRepository: ChatParticipantRepository
) : ViewModel() {

    private val eventChannel = Channel<ChatListEvent>()
    val events = eventChannel.receiveAsFlow()

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(ChatListState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ChatListState()
        )

    fun onAction(action: ChatListAction) {
        when (action) {
            is ChatListAction.OnSelectChat -> {
                _state.update { it.copy(
                    selectedChatId = action.chatId
                ) }
            }
            ChatListAction.OnUserAvatarClick -> {
                _state.update { it.copy(
                    isUserMenuOpen = true
                ) }
            }
            ChatListAction.OnLogoutClick -> showLogoutConfirmation()
            ChatListAction.OnDismissLogoutDialog -> {
                _state.update { it.copy(
                    showLogoutConfirmation = false
                ) }
            }
            ChatListAction.OnProfileSettingsClick,
            ChatListAction.OnDismissUserMenu -> {
                _state.update { it.copy(
                    isUserMenuOpen = false
                ) }
            }
            else -> Unit
        }
    }




    private fun showLogoutConfirmation() {
        _state.update { it.copy(
            isUserMenuOpen = false,
            showLogoutConfirmation = true
        ) }
    }

}