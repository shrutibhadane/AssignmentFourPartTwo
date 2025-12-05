package com.assignment_four.assignmentfourparttwo

import android.app.Application
import android.graphics.Typeface
import android.text.InputType
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.text.TextLayoutInput
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.junit.Assert.assertTrue
import org.junit.Before
import org.robolectric.annotation.Config
import kotlin.intArrayOf


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class AddContactActivityTest {

    private lateinit var activity: AddContactActivity
    private lateinit var parent: LinearLayout
    private lateinit var tvAddNewContactTitle: TextView
    private lateinit var tilName: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var btnSaveContact: Button

    @Before
    fun setup() {
        // Set the theme before creating the activity
        val activityController = Robolectric.buildActivity(AddContactActivity::class.java)
        activityController.create().start().resume().visible()
        activity = activityController.get()

        // Use setTheme to override with AppCompat theme
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)

        parent = activity.findViewById(R.id.add_contact_layout)
        tvAddNewContactTitle = activity.findViewById(R.id.tvAddNewContactTitle)
        tilName = activity.findViewById(R.id.tilName)
        tilPhone = activity.findViewById(R.id.tilPhone)
        tilEmail = activity.findViewById(R.id.tilEmail)
        etName = activity.findViewById(R.id.etName)
        etPhone = activity.findViewById(R.id.etPhone)
        etEmail = activity.findViewById(R.id.etEmail)
        btnSaveContact = activity.findViewById(R.id.btnSaveContact)

        // Force measure & layout
        val widthSpec = View.MeasureSpec.makeMeasureSpec(500, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST)

        parent.measure(widthSpec, heightSpec)
        parent.layout(0, 0, parent.measuredWidth, parent.measuredHeight)
    }

    // Activity Tests
    @Test
    fun `activity should not be null`() {
        assertNotNull(activity)
    }

    @Test
    fun `views should be initialized`() {
        assertNotNull(parent)
        assertNotNull(tvAddNewContactTitle)
        assertNotNull(etName)
        assertNotNull(etPhone)
        assertNotNull(etEmail)
        assertNotNull(btnSaveContact)
    }

    // Layout Tests
    @Test
    fun `parent layout should be LinearLayout`() {
        val parent = tvAddNewContactTitle.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Title TextView Tests
    @Test
    fun `title should have correct text`() {
        val expectedText = activity.getString(R.string.add_new_contact)
        assertEquals(expectedText, tvAddNewContactTitle.text.toString())
    }

    @Test
    fun `title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
        assertEquals(expectedColor, tvAddNewContactTitle.currentTextColor)
    }

    @Test
    fun `title should have correct text size`() {
        assertEquals(spToPx(24), tvAddNewContactTitle.textSize, 0.1f)
    }

    @Test
    fun `title should have correct text style`() {
        assertEquals(Typeface.BOLD, tvAddNewContactTitle.typeface.style)
    }

    // Name Text Testcases
    @Test
    fun `tilName and etName should not be null`() {
        assertNotNull(tilName)
        assertNotNull(etName)
    }

    @Test
    fun `tilName should have correct text hint`() {
        val expectedText = activity.getString(R.string.name)
        assertEquals(expectedText, tilName.hint.toString())
    }

    @Test
    fun `etName should have correct text style`() {
        assertEquals(Typeface.NORMAL, etName.typeface.style)
    }

    @Test
    fun `etName should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.black)
        assertEquals(expectedColor, etName.currentTextColor)
    }

    @Test
    fun `etName should have correct text size`() {
        assertEquals(spToPx(16), etName.textSize, 0.1f)
    }

    @Test
    fun `etName should have correct input type`() {
        val expectedInputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        assertEquals(expectedInputType, etName.inputType)
    }

    // Phone Number Text Testcases
    @Test
    fun `tilPhone and etPhone should not be null`() {
        assertNotNull(tilPhone)
        assertNotNull(etPhone)
    }

    @Test
    fun `tilPhone should have correct text hint`() {
        val expectedText = activity.getString(R.string.phone_number)
        assertEquals(expectedText, tilPhone.hint.toString())
    }

    @Test
    fun `etPhone should have correct text style`() {
        assertEquals(Typeface.NORMAL, etPhone.typeface.style)
    }

    @Test
    fun `etPhone should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.black)
        assertEquals(expectedColor, etPhone.currentTextColor)
    }

    @Test
    fun `etPhone should have correct text size`() {
        assertEquals(spToPx(16), etPhone.textSize, 0.1f)
    }

    @Test
    fun `etPhone should have correct input type`() {
        val expectedInputType = InputType.TYPE_CLASS_PHONE
        assertEquals(expectedInputType, etPhone.inputType)
    }

    // Email Text Testcases
    @Test
    fun `tilEmail and etEmail should not be null`() {
        assertNotNull(tilEmail)
        assertNotNull(etEmail)
    }

    @Test
    fun `tilEmail should have correct text hint`() {
        val expectedText = activity.getString(R.string.email_address)
        assertEquals(expectedText, tilEmail.hint.toString())
    }

    @Test
    fun `etEmail should have correct text style`() {
        assertEquals(Typeface.NORMAL, etEmail.typeface.style)
    }

    @Test
    fun `etEmail should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.black)
        assertEquals(expectedColor, etEmail.currentTextColor)
    }

    @Test
    fun `etEmail should have correct text size`() {
        assertEquals(spToPx(16), etEmail.textSize, 0.1f)
    }

    @Test
    fun `etEmail should have correct input type`() {
        val expectedInputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        assertEquals(expectedInputType, etEmail.inputType)
    }

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

    // Add Contact Button Tests
    @Test
    fun `save contact button should have correct text`() {
        val expectedText = activity.getString(R.string.save_contact)
        assertEquals(expectedText, btnSaveContact.text.toString())
    }

    @Test
    fun `save contact button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnSaveContact.currentTextColor)
    }

    @Test
    fun `save contact button should have correct background tint`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.colorPrimary)

        // Extract tint from button backgroundTintList
        val backgroundTintList = btnSaveContact.backgroundTintList
        val actualColor = backgroundTintList?.defaultColor

        assertEquals(expectedColor, actualColor)
    }

    @Test
    fun `save contact button should have correct text size`() {
        assertEquals(spToPx(16), btnSaveContact.textSize, 0.1f)
    }

    // Helper functions
    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            activity.resources.displayMetrics
        ).toInt()
    }

    private fun spToPx(sp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            activity.resources.displayMetrics
        )
    }
}
