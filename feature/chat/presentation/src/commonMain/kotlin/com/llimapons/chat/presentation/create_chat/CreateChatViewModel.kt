package com.llimapons.chat.presentation.create_chat

import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llimapons.chat.domain.chat.ChatParticipantService
import com.llimapons.chat.domain.chat.ChatService
import com.llimapons.chat.presentation.mappers.toUi
import com.llimapons.core.domain.util.DataError
import com.llimapons.core.domain.util.onFailure
import com.llimapons.core.domain.util.onSuccess
import com.llimapons.core.presentation.util.UiText
import com.llimapons.core.presentation.util.toUiText
import echo.feature.chat.presentation.generated.resources.Res
import echo.feature.chat.presentation.generated.resources.error_participant_not_found
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@OptIn(FlowPreview::class)
class CreateChatViewModel(
    private val chatParticipantService: ChatParticipantService,
    private val chatService: ChatService
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val eventChannel = Channel<CreateChatEvent>()
    val events = eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(CreateChatState())

    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                searchFlow.launchIn(viewModelScope)
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CreateChatState()
        )

    private val searchFlow = snapshotFlow { _state.value.queryTextState.text.toString() }
        .debounce(1.seconds)
        .onEach { query ->
            performSearch(query = query)
        }

    fun onAction(action: CreateChatAction) {
        when (action) {
            CreateChatAction.OnAddClick -> addParticipant()
            CreateChatAction.OnCreateChatClick -> ceateChat()
            else -> Unit
        }
    }

    private fun ceateChat() {
        val userIds = state.value.selectedChatParticipants.map { it.id }

        if (userIds.isEmpty()) return

        viewModelScope.launch {
            _state.update { it.copy(
                isCreatingChat = true,
                canAddParticipant = false,
            ) }

            chatService
                .createChat(userIds)
                .onSuccess { chat ->
                    _state.update { it.copy(
                        isCreatingChat = false
                    ) }
                    eventChannel.send(CreateChatEvent.OnChatCreated(chat))
                }
                .onFailure { error ->
                    _state.update { it.copy(
                        createChatError = error.toUiText(),
                        isCreatingChat = false,
                        canAddParticipant = it.currentSearchResult != null && !it.isSearching,
                    ) }
                }
        }
    }

    private fun addParticipant() {
        state.value.currentSearchResult?.let { participant ->
            val isAlreadyPartOfChat = state.value.selectedChatParticipants.any{
                it.id == participant.id
            }
            if (!isAlreadyPartOfChat){
                _state.update {
                    it.copy(
                        selectedChatParticipants = it.selectedChatParticipants + participant,
                        canAddParticipant = false,
                        currentSearchResult = null
                    ) }
                _state.value.queryTextState.clearText()
            }
        }
    }

    private fun performSearch(query: String) {
        if (query.isBlank()) {
            _state.update { it.copy(
                currentSearchResult = null,
                canAddParticipant = false,
                searchError = null
            ) }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(
                isSearching = true,
                canAddParticipant = false,
            ) }

            chatParticipantService
                .searchParticipant(query)
                .onSuccess { participant ->
                    _state.update { it.copy(
                        currentSearchResult = participant.toUi(),
                        isSearching = false,
                        canAddParticipant = true,
                        searchError = null
                    )}
                }
                .onFailure { error ->
                    val errorMessage = when(error){
                        DataError.Remote.NOT_FOUND -> UiText.Resource(Res.string.error_participant_not_found)
                        else -> error.toUiText()
                    }
                    _state.update { it.copy(
                        isSearching = false,
                        searchError = errorMessage,
                        currentSearchResult = null,
                        canAddParticipant = false
                    ) }
                }
        }
    }
}
