package com.assignment_four.assignmentfourparttwo

import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.junit.Assert.assertTrue

@RunWith(RobolectricTestRunner::class)
class AddContactActivityTest {

    @Test
    fun testAddContactValidation() {
        val activity = Robolectric.buildActivity(AddContactActivity::class.java).create().visible().get()
        
        val etName = activity.findViewById<TextInputEditText>(R.id.etName)
        val btnSave = activity.findViewById<Button>(R.id.btnSaveContact)
        
        // Test empty name
        btnSave.performClick()
        assertEquals("Name is required", etName.error)
    }

    @Test
    fun testAddContactSuccess() {
        val activity = Robolectric.buildActivity(AddContactActivity::class.java).create().visible().get()
        
        val etName = activity.findViewById<TextInputEditText>(R.id.etName)
        val etPhone = activity.findViewById<TextInputEditText>(R.id.etPhone)
        val etEmail = activity.findViewById<TextInputEditText>(R.id.etEmail)
        val btnSave = activity.findViewById<Button>(R.id.btnSaveContact)
        
        etName.setText("Test User")
        etPhone.setText("1234567890")
        etEmail.setText("test@example.com")
        
        btnSave.performClick()
        
        // Check if activity finished (meaning success)
        assertTrue(activity.isFinishing)
        
        // Verify DB insertion
        val dbHelper = DatabaseHelper(activity)
        val contacts = dbHelper.getAllContacts()
        assertEquals(1, contacts.size)
        assertEquals("Test User", contacts[0].name)
    }
}
