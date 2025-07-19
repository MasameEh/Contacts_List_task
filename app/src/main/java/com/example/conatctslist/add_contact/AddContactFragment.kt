package com.example.conatctslist.add_contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.conatctslist.R
import com.example.conatctslist.data.local.ContactLocalDataSource
import com.example.conatctslist.data.local.db.ContactsDatabase
import com.example.conatctslist.data.model.Contact
import com.example.conatctslist.data.repo.ContactRepository


class AddContactFragment : Fragment() {

    private lateinit var viewModel: AddContactViewModel
    private lateinit var addContactBtn: Button
    private lateinit var nameEt: EditText
    private lateinit var phoneEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.create(
            this,
            AddContactViewModelFactory(
                ContactRepository(
                    ContactLocalDataSource(
                        contactDao = ContactsDatabase.getInstance(requireContext()).getContactDao(
                        )
                    )
                )
            )
        )[AddContactViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
    }


    private fun initElements() {
        addContactBtn = view?.findViewById(R.id.btn_add_contact) ?: return
        nameEt = view?.findViewById(R.id.et_name) ?: return
        phoneEt = view?.findViewById(R.id.et_phone) ?: return

        addContactBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val phone = phoneEt.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val contact = Contact(phone, name)
                viewModel.saveContact(contact)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe insertion result
        viewModel.insertionResult.observe(viewLifecycleOwner) { result ->
            if (result > 0) {
                Toast.makeText(requireContext(), "Contact added successfully", Toast.LENGTH_SHORT).show()
                nameEt.text.clear()
                phoneEt.text.clear()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Failed to add contact", Toast.LENGTH_SHORT).show()
            }
        }
    }
}