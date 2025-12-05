package com.assignment_four.assignmentfourparttwo

import android.app.Application
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
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
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import kotlin.jvm.java
import kotlin.jvm.javaClass

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class SplashActivityTest {

    private lateinit var activity: SplashActivity
    private lateinit var parent: LinearLayout
    private lateinit var imgLogo: ImageView
    private lateinit var appName: TextView

    @Before
    fun setup() {
        // Set the theme before creating the activity
        val activityController = Robolectric.buildActivity(SplashActivity::class.java)
        activityController.create().start().resume().visible()
        activity = activityController.get()
        
        // Use setTheme to override with AppCompat theme
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)

        parent = activity.findViewById(R.id.splash_layout)
        imgLogo = activity.findViewById(R.id.ivLogo)
        appName = activity.findViewById(R.id.tvAppName)

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
        assertNotNull(imgLogo)
        assertNotNull(appName)
    }

    // Layout Tests
    @Test
    fun `parent layout should be LinearLayout`() {
        val parent = appName.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Logo ImageView Test
    @Test
    fun `logo should be visible`() {
        assertEquals(View.VISIBLE, imgLogo.visibility)
    }

    @Test
    fun `logo should use correct drawable`() {
        val resId = Shadows.shadowOf(imgLogo.drawable).createdFromResId
        assertEquals(R.drawable.logo, resId)
    }

    // Title TextView Tests
    @Test
    fun `title should have correct text`() {
        val expectedText = activity.getString(R.string.app_name)
        assertEquals(expectedText, appName.text.toString())
    }

    @Test
    fun `title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
        assertEquals(expectedColor, appName.currentTextColor)
    }

    @Test
    fun `title should have correct text size`() {
        assertEquals(spToPx(32), appName.textSize, 0.1f)
    }

    @Test
    fun `title should have correct text style`() {
        assertEquals(Typeface.BOLD, appName.typeface.style)
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