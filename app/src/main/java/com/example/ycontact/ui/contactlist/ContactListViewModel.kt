package com.example.ycontact.ui.contactlist

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ycontact.data.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel(){


    private val _state = MutableStateFlow(ContactListUiState())
    val state = _state.asStateFlow()


    fun getAllContacts(contentResolver: ContentResolver){
        viewModelScope.launch {
            contactRepository.getAllContact(contentResolver).collect{ contactList ->
                _state.update {
                    it.copy(list = contactList)
                }
            }
        }
    }

}