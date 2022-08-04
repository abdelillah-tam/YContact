package com.example.ycontact.data

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import com.example.ycontact.domain.models.ContactItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContactRepositoryImpl : ContactRepository {


    override fun getAllContact(contentResolver: ContentResolver): Flow<List<ContactItem>>  = flow{
        val PROJECTION = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTENT_TYPE
        )
        val listOfContacts = mutableListOf<ContactItem>()
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        val idIndex = cursor!!.getColumnIndex(ContactsContract.Contacts._ID)
        val lookupKeyIndex = cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
        val displayNamePrimary = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
        val mimeType = cursor.getColumnIndex(ContactsContract.Contacts.CONTENT_TYPE)
        while (cursor.moveToNext()){
            listOfContacts.add(ContactItem(cursor.getString(idIndex), cursor.getString(lookupKeyIndex), cursor.getString(displayNamePrimary)))
        }
        cursor.close()
        emit(listOfContacts)

    }

}