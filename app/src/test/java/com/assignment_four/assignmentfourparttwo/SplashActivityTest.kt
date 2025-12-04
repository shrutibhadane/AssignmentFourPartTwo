package com.assignment_four.assignmentfourparttwo

import android.content.Intent
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class SplashActivityTest {

    @Test
    fun testSplashNavigation() {
        val controller: ActivityController<SplashActivity> = Robolectric.buildActivity(SplashActivity::class.java)
        controller.create().start().resume()
        
        val activity = controller.get()
        
        // Fast forward time by 2 seconds
        shadowOf(activity.mainLooper).idleFor(java.time.Duration.ofSeconds(2))
        
        val expectedIntent = Intent(activity, MainActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity
        
        assertEquals(expectedIntent.component, actualIntent.component)
    }
}
