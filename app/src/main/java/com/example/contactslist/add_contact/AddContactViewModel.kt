package com.example.contactslist.add_contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.contactslist.data.model.Contact
import com.example.contactslist.data.repo.IContactRepository
import kotlinx.coroutines.launch

class AddContactViewModel(private val repo: IContactRepository): ViewModel() {

     private val _insertionResult = MutableLiveData<Long>()
     val insertionResult: LiveData<Long> = _insertionResult

     fun saveContact(contact: Contact) {
         viewModelScope.launch {
             _insertionResult.value = repo.insertContact(contact)
         }

     }
}

class AddContactViewModelFactory(private val repo: IContactRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddContactViewModel(repo) as T
    }
}