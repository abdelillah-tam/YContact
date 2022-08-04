package com.example.ycontact.ui.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ycontact.R
import com.example.ycontact.domain.models.ContactItem
import com.google.android.material.textview.MaterialTextView

class ContactRecviewAdapter : RecyclerView.Adapter<ContactRecviewAdapter.ViewHolder>() {

    private var contactList = emptyList<ContactItem>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactName = holder.itemView.findViewById(R.id.contactName) as MaterialTextView

        val contactItem = contactList[position]
        contactName.text = contactItem.displayName
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setContacts(contacts : List<ContactItem>){
        contactList = contacts
        notifyDataSetChanged()
    }
}