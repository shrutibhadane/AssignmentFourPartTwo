package com.assignment_four.assignmentfourparttwo

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class AddContactActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var btnSaveContact: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        dbHelper = DatabaseHelper(this)

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        btnSaveContact = findViewById(R.id.btnSaveContact)

        btnSaveContact.setOnClickListener {
            if (validateInput()) {
                saveContact()
            }
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

    private fun saveContact() {
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()

        val contact = Contact(name = name, phone = phone, email = email)
        val result = dbHelper.addContact(contact)

        if (result > -1) {
            Toast.makeText(this, "Contact Saved Successfully", Toast.LENGTH_SHORT).show()
            finish() // Go back to previous screen (Home or List)
        } else {
            Toast.makeText(this, "Error Saving Contact", Toast.LENGTH_SHORT).show()
        }
    }
}
