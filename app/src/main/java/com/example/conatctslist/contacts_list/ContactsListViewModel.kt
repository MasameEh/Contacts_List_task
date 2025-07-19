package com.example.conatctslist.contacts_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.conatctslist.data.model.Contact
import com.example.conatctslist.data.repo.IContactRepository
import kotlinx.coroutines.launch

class ContactsListViewModel(private val repo: IContactRepository): ViewModel() {

    val contactsList: LiveData<List<Contact>> = repo.getContacts()

    private val _deleteResult = MutableLiveData<Int>()
    val deleteResult: LiveData<Int> = _deleteResult

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            _deleteResult.value = repo.deleteContact(contact)
        }
    }
}

class ContactsListViewModelFactory(private val repo: IContactRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsListViewModel(repo) as T
    }
}