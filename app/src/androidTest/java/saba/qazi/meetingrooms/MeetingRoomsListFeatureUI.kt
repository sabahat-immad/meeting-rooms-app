package saba.qazi.meetingrooms

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.core.AllOf.allOf

import org.junit.Test

import saba.qazi.meetingrooms.meetingroomslist.di.idlingResource


class MeetingRoomsListFeatureUI : BaseUITest(){

    @Test
    fun displayTitleText() {
        assertDisplayed("Meeting Rooms")
    }

    @Test
    fun displayListOfCatNews(){

        onView(allOf(withId(R.id.f_name), isDescendantOfA(nthChildOf(withId(R.id.meetingrooms_list),0))))
            .check(matches(withText("Blue Sapphire")))
            .check(matches(isDisplayed()))

}
    @Test
    fun displaysLoaderWhenFetchingTheNewsList(){
        /*we have explicitly unregistered idling resource because if we dont, soon after we did
        * the http call the thread sleeps and wont detect the UI loader */
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoaderWhenNewsAreFetched(){

        assertNotDisplayed(R.id.loader)
    }



    @Test
    fun navigateToMeetingRoomDetailScreen(){

        onView(allOf(withId(R.id.mr_image), isDescendantOfA(nthChildOf(withId(R.id.meetingrooms_list),1))))
                .perform(click())

        assertDisplayed(R.id.meetingroom_detail_fragment)
    }


}