package com.example.ycontact.data

import android.content.ContentProvider
import android.content.ContentResolver
import android.database.Cursor
import com.example.ycontact.domain.models.ContactItem
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getAllContact(contentResolver: ContentResolver) : Flow<List<ContactItem>>
}