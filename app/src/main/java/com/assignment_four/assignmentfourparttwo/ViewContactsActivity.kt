package com.assignment_four.assignmentfourparttwo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewContactsActivity : AppCompatActivity() {

    private lateinit var rvContacts: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var adapter: ContactAdapter
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contacts)

        dbHelper = DatabaseHelper(this)
        rvContacts = findViewById(R.id.rvContacts)
        etSearch = findViewById(R.id.etSearch)

        rvContacts.layoutManager = LinearLayoutManager(this)
        
        loadContacts()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        loadContacts() // Refresh list when returning from Edit/Delete
    }

    private fun loadContacts() {
        val contacts = dbHelper.getAllContacts()
        adapter = ContactAdapter(contacts) { contact ->
            val intent = Intent(this, EditContactActivity::class.java)
            intent.putExtra("CONTACT_ID", contact.id)
            intent.putExtra("CONTACT_NAME", contact.name)
            intent.putExtra("CONTACT_PHONE", contact.phone)
            intent.putExtra("CONTACT_EMAIL", contact.email)
            startActivity(intent)
        }
        rvContacts.adapter = adapter
    }
}
