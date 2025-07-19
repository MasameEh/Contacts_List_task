package com.example.contactslist.contacts_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.conatctslist.R
import com.example.contactslist.data.model.Contact

class ContactsRecyclerViewAdapter (
    private val context: Context,
    private val contactsList: MutableList<Contact>,
    private val onDeleteClickListener: (Contact) -> Unit,
    private val onContactClickListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder>(){

    private var filteredList: MutableList<Contact> = contactsList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.contact_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return filteredList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = filteredList[position]
        holder.contactName.text = contact.name
        holder.contactPhone.text = contact.phoneNumber
        holder.deleteBtn.setOnClickListener {
            onDeleteClickListener(contact)
        }
        holder.contactCard.setOnClickListener{
            onContactClickListener(contact)
        }
    }

    fun submitList(contacts: List<Contact>?) {
        contactsList.clear()
        if (contacts != null) {
            contactsList.addAll(contacts)
        }
        filter("")
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            contactsList.toMutableList()
        } else {
            contactsList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.phoneNumber.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
    // ViewHolder class to hold the view for each contact item
    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {

        val contactName: TextView = itemView.findViewById(R.id.tv_contact_name)
        val contactPhone: TextView = itemView.findViewById(R.id.tv_contact_phone)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.btn_delete_contact)
        val contactCard: CardView = itemView.findViewById(R.id.contact_card)
    }

}