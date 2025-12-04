package com.assignment_four.assignmentfourparttwo

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class EditContactActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var dbHelper: DatabaseHelper
    private var contactId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        dbHelper = DatabaseHelper(this)

        etName = findViewById(R.id.etEditName)
        etPhone = findViewById(R.id.etEditPhone)
        etEmail = findViewById(R.id.etEditEmail)
        btnUpdate = findViewById(R.id.btnUpdateContact)
        btnDelete = findViewById(R.id.btnDeleteContact)

        // Get data from Intent
        contactId = intent.getIntExtra("CONTACT_ID", 0)
        etName.setText(intent.getStringExtra("CONTACT_NAME"))
        etPhone.setText(intent.getStringExtra("CONTACT_PHONE"))
        etEmail.setText(intent.getStringExtra("CONTACT_EMAIL"))

        btnUpdate.setOnClickListener {
            if (validateInput()) {
                updateContact()
            }
        }

        btnDelete.setOnClickListener {
            deleteContact()
        }
    }

    private fun validateInput(): Boolean {
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()

        if (name.isEmpty()) {
            etName.error = "Name is required"
            return false
        }

        if (phone.length != 10 || !phone.all { it.isDigit() }) {
            etPhone.error = "Enter a valid 10-digit phone number"
            return false
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Enter a valid email address"
            return false
        }

        return true
    }

    private fun updateContact() {
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()

        val contact = Contact(id = contactId, name = name, phone = phone, email = email)
        val rows = dbHelper.updateContact(contact)

        if (rows > 0) {
            Toast.makeText(this, "Contact Updated Successfully", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Error Updating Contact", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteContact() {
        dbHelper.deleteContact(contactId)
        Toast.makeText(this, "Contact Deleted Successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
