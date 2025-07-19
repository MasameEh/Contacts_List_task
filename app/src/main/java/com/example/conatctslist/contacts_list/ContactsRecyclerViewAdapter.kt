package com.example.conatctslist.contacts_list

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.conatctslist.R
import com.example.conatctslist.data.model.Contact

class ContactsRecyclerViewAdapter (
    private val context: Context,
    private val contactsList: MutableList<Contact>,
    private val onDeleteClickListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("TAG", "onCreateViewHolder: Creating ViewHolder")
        val view = LayoutInflater.from(context).inflate(
            R.layout.contact_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return contactsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactsList[position]
        holder.contactName.text = contact.name
        holder.contactPhone.text = contact.phoneNumber
        holder.deleteBtn.setOnClickListener {
            onDeleteClickListener(contact)
        }
    }

    fun submitList(contacts: List<Contact>?) {
        contactsList.clear()
        if (contacts != null) {
            contactsList.addAll(contacts)
        }
        notifyDataSetChanged()
    }

    // ViewHolder class to hold the view for each contact item
    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {

        val contactName: TextView = itemView.findViewById(R.id.tv_contact_name)
        val contactPhone: TextView = itemView.findViewById(R.id.tv_contact_phone)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.btn_delete_contact)

    }

}