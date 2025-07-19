package com.example.contactslist.contact_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.conatctslist.R

class ContactDetailsFragment : Fragment() {

    private val args: ContactDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact = args.contact

        val nameTv: TextView = view.findViewById(R.id.tv_contact_details_name)
        val phoneTv: TextView = view.findViewById(R.id.tv_contact_details_phone)

        nameTv.text = contact.name
        phoneTv.text = contact.phoneNumber
    }
}