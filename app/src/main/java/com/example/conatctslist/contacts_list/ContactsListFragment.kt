package com.example.conatctslist.contacts_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conatctslist.R
import com.example.conatctslist.add_contact.AddContactViewModel
import com.example.conatctslist.add_contact.AddContactViewModelFactory
import com.example.conatctslist.data.local.ContactLocalDataSource
import com.example.conatctslist.data.local.db.ContactsDatabase
import com.example.conatctslist.data.repo.ContactRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ContactsListFragment : Fragment() {

    private lateinit var fabAddContact: FloatingActionButton
    private lateinit var viewModel: ContactsListViewModel
    private lateinit var contactAdapter: ContactsRecyclerViewAdapter
    private lateinit var contactsRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ContactsListViewModelFactory(
            ContactRepository(
                ContactLocalDataSource(
                    contactDao = ContactsDatabase.getInstance(requireContext()).getContactDao()
                )
            )
        )
        viewModel = ViewModelProvider(this, factory)[ContactsListViewModel::class.java]

    }

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

        initElements()
        viewModel.contactsList.observe(viewLifecycleOwner) { contacts ->
            if (contacts.isEmpty()) {
                contactsRv.visibility = View.GONE
            } else {
                contactsRv.visibility = View.VISIBLE
            }
            Log.i("TAG", "onViewCreated: $contacts")
            contactAdapter.submitList(contacts)
        }
    }



    private fun initElements(){
        contactAdapter = ContactsRecyclerViewAdapter(
            context = requireContext(),
            contactsList = mutableListOf(),
            onDeleteClickListener = { contact ->
                viewModel.deleteContact(contact)
            }
        )

        contactsRv = view?.findViewById(R.id.rv_contacts) ?: return
        contactsRv.layoutManager = LinearLayoutManager(requireContext())
        contactsRv.adapter = contactAdapter
    }
}