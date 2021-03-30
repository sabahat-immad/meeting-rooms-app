package saba.qazi.meetingrooms.meetingroomslist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom
import saba.qazi.meetingrooms.utils.BaseUntTest
import java.lang.RuntimeException

class MeetingRoomsListServiceShould : BaseUntTest() {

    private lateinit var service : MrListService
    private val meetingRoomsListApi : MeetingRoomsListAPI = mock()
    private val mrList : List<MeetingRoom> = mock()


    @Test
    fun fetchNewsListFromApi() = runBlockingTest{
        service = MrListService(meetingRoomsListApi)
        service.fetchMeetingRoomsList().first()
        verify(meetingRoomsListApi,times(1)).fetchMeetingRooms()
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
    mockSuccessfulCase()
        assertEquals(Result.success(mrList), service.fetchMeetingRoomsList().first())
    }


    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailureCase()
        assertEquals("Something went wrong",
            service.fetchMeetingRoomsList().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(meetingRoomsListApi.fetchMeetingRooms())
            .thenThrow(RuntimeException("Damn backend developers"))

        service = MrListService(meetingRoomsListApi)
    }


    private suspend fun mockSuccessfulCase() {
        whenever(meetingRoomsListApi.fetchMeetingRooms()).thenReturn(mrList)
        service = MrListService(meetingRoomsListApi)
    }
}