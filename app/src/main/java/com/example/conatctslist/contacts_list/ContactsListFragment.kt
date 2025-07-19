package com.example.conatctslist.contacts_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.conatctslist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ContactsListFragment : Fragment() {

    private lateinit var fabAddContact: FloatingActionButton



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddContact = view.findViewById(R.id.fab_add_contact)

        fabAddContact.setOnClickListener {
            findNavController().navigate(R.id.action_contactsListFragment_to_addContactFragment)
        }
    }
}