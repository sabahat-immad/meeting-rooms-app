package saba.qazi.meetingrooms

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.core.AllOf
import org.junit.Test

class MeetingRoomDetailFeatureUI : BaseUITest() {

    @Test
    fun displayViewsInScreen(){
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.mr_image),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.meetingrooms_list), 1))
            )
        )
            .perform(ViewActions.click())

        assertDisplayed(R.id.meetingroom_detail_fragment)

        assertDisplayed(R.id.hero_image)
        assertDisplayed(R.id.name)
        assertDisplayed(R.id.capacity)
    }
}