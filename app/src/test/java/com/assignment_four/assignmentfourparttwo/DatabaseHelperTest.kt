package com.assignment_four.assignmentfourparttwo

import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DatabaseHelperTest {

    private lateinit var dbHelper: DatabaseHelper

    @Before
    fun setUp() {
        dbHelper = DatabaseHelper(ApplicationProvider.getApplicationContext())
    }

    @After
    fun tearDown() {
        dbHelper.close()
    }

    @Test
    fun testAddAndRetrieveContact() {
        val contact = Contact(name = "John Doe", phone = "1234567890", email = "john@example.com")
        val id = dbHelper.addContact(contact)
        assertTrue(id > -1)

        val contacts = dbHelper.getAllContacts()
        assertEquals(1, contacts.size)
        assertEquals("John Doe", contacts[0].name)
    }

    @Test
    fun testUpdateContact() {
        val contact = Contact(name = "Jane Doe", phone = "0987654321", email = "jane@example.com")
        val id = dbHelper.addContact(contact)
        
        val updatedContact = Contact(id = id.toInt(), name = "Jane Smith", phone = "0987654321", email = "jane@example.com")
        val rows = dbHelper.updateContact(updatedContact)
        assertEquals(1, rows)

        val contacts = dbHelper.getAllContacts()
        assertEquals("Jane Smith", contacts[0].name)
    }

    @Test
    fun testDeleteContact() {
        val contact = Contact(name = "Bob", phone = "1112223333", email = "bob@example.com")
        val id = dbHelper.addContact(contact)
        
        dbHelper.deleteContact(id.toInt())
        val contacts = dbHelper.getAllContacts()
        assertEquals(0, contacts.size)
    }
}
