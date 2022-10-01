package com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.draggable_viewmodel

import androidx.lifecycle.ViewModel
import com.mahdi.snickersshop.ui.presentation.add_to_cart_screen.draggable_viewmodel.model.CardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DragViewModel @Inject constructor():ViewModel() {
     private val _state = MutableStateFlow(CardModel())
     val state :StateFlow<CardModel> = _state

     private val _revealedCardIdsList = MutableStateFlow(listOf<Int>())
     val revealedCardIdsList: StateFlow<List<Int>> get() = _revealedCardIdsList

     fun onItemExpanded(cardId: Int) {
          if (_revealedCardIdsList.value.contains(cardId)) return
          _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
               list.add(cardId)
          }
     }

     fun onItemCollapsed(cardId: Int) {
          if (!_revealedCardIdsList.value.contains(cardId)) return
          _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
               list.remove(cardId)
          }
     }
}