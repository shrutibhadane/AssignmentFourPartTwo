package com.assignment_four.assignmentfourparttwo

import android.app.Application
import android.content.Intent
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import kotlin.jvm.java
import kotlin.jvm.javaClass

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class MainActivityTest {

    private lateinit var activity: MainActivity
    private lateinit var parent: LinearLayout
    private lateinit var tvTitleText: TextView
    private lateinit var btnAddContact: Button
    private lateinit var btnViewContacts: Button

    @Before
    fun setup() {
        // Set the theme before creating the activity
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        activityController.create().start().resume().visible()
        activity = activityController.get()

        // Use setTheme to override with AppCompat theme
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)

        parent = activity.findViewById(R.id.main_layout)
        tvTitleText = activity.findViewById(R.id.tvTitleText)
        btnAddContact = activity.findViewById(R.id.btnAddContact)
        btnViewContacts = activity.findViewById(R.id.btnViewContacts)

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
        assertNotNull(tvTitleText)
        assertNotNull(btnAddContact)
        assertNotNull(btnViewContacts)
    }

    // Layout Tests
    @Test
    fun `parent layout should be LinearLayout`() {
        val parent = tvTitleText.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Title TextView Tests
    @Test
    fun `title should have correct text`() {
        val expectedText = activity.getString(R.string.app_name)
        assertEquals(expectedText, tvTitleText.text.toString())
    }

    @Test
    fun `title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
        assertEquals(expectedColor, tvTitleText.currentTextColor)
    }

    @Test
    fun `title should have correct text size`() {
        assertEquals(spToPx(32), tvTitleText.textSize, 0.1f)
    }

    @Test
    fun `title should have correct text style`() {
        assertEquals(Typeface.BOLD, tvTitleText.typeface.style)
    }

    // Add Contact Button Tests
    @Test
    fun `add contact button should have correct text`() {
        val expectedText = activity.getString(R.string.add_contact)
        assertEquals(expectedText, btnAddContact.text.toString())
    }

    @Test
    fun `add contact button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnAddContact.currentTextColor)
    }

    @Test
    fun `add contact button should have correct background tint`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.colorPrimary)

        // Extract tint from button backgroundTintList
        val backgroundTintList = btnAddContact.backgroundTintList
        val actualColor = backgroundTintList?.defaultColor

        assertEquals(expectedColor, actualColor)
    }

    @Test
    fun `add contact button should have correct text size`() {
        assertEquals(spToPx(16), btnAddContact.textSize, 0.1f)
    }

    @Test
    fun `add contact button should have correct margin`() {
        val params = btnAddContact.layoutParams as LinearLayout.LayoutParams
        assertEquals(dpToPx(16), params.bottomMargin)
    }

    // View Contacts Button Tests
    @Test
    fun `View contacts button should have correct text`() {
        val expectedText = activity.getString(R.string.view_contacts)
        assertEquals(expectedText, btnViewContacts.text.toString())
    }

    @Test
    fun `View contacts button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnViewContacts.currentTextColor)
    }

    @Test
    fun `View contacts button should have correct background`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.colorPrimary)

        // Extract tint from button backgroundTintList
        val backgroundTintList = btnViewContacts.backgroundTintList
        val actualColor = backgroundTintList?.defaultColor

        assertEquals(expectedColor, actualColor)
    }

    @Test
    fun `View contacts button should have correct text size`() {
        assertEquals(spToPx(16), btnViewContacts.textSize, 0.1f)
    }

    // Navigation Tests
    @Test
    fun `clicking add contact button should navigate to AddContactActivity`() {
        btnAddContact.performClick()

        val expectedIntent = Intent(activity, AddContactActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `add contact button should be clickable`() {
        assertEquals(true, btnAddContact.hasOnClickListeners())
    }

    @Test
    fun `clicking view contact button should navigate to ViewContactsActivity`() {
        btnViewContacts.performClick()

        val expectedIntent = Intent(activity, ViewContactsActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `view contact button should be clickable`() {
        assertEquals(true, btnViewContacts.hasOnClickListeners())
    }

    @Test
    fun `buttons should be focusable for accessibility`() {
        assertEquals(true, btnAddContact.isFocusable)
        assertEquals(true, btnViewContacts.isFocusable)
    }

    @Test
    fun `activity should finish when finish is called`() {
        activity.finish()
        assertEquals(true, activity.isFinishing)
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