package com.example.ycontact.ui.contactlist

import com.example.ycontact.domain.models.ContactItem

data class ContactListUiState(
    var list: List<ContactItem> = emptyList()
)