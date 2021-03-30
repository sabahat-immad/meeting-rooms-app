package saba.qazi.meetingrooms.meetingroomdetail

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import saba.qazi.meetingrooms.meetingroomdetail.data.MeetingRoomDetail


class MeetingRoomDetailServiceShould {

    private lateinit var service : MrDetailService
    private val mrDetailApi : MrDetailAPI = mock()
    private val meetingRoomDetail : MeetingRoomDetail = mock()
    private val id = "1"
    private val exception = RuntimeException("Damn backend developers again 500!!!")

    @Test
    fun fetchMeetingRoomDetailFromApi()= runBlockingTest{
       mockSuccessfulCase()
       service.fetchMeetingRoomDetail(id).single()
        verify(mrDetailApi, times(1)).fetchMeetingRoomDetail(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(Result.success(meetingRoomDetail), service.fetchMeetingRoomDetail(id).single())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockErrorCase()

        TestCase.assertEquals(
            "Something went wrong",
            service.fetchMeetingRoomDetail(id).single().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(mrDetailApi.fetchMeetingRoomDetail(id)).thenThrow(exception)

        service = MrDetailService(mrDetailApi)
    }


    private suspend fun mockSuccessfulCase() {
        whenever(mrDetailApi.fetchMeetingRoomDetail(id)).thenReturn(meetingRoomDetail)

        service = MrDetailService(mrDetailApi)
    }
}