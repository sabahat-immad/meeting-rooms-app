package saba.qazi.meetingrooms.meetingroomdetail

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import saba.qazi.meetingrooms.meetingroomdetail.data.MeetingRoomDetail
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom
import saba.qazi.meetingrooms.utils.BaseUntTest
import saba.qazi.meetingrooms.utils.getValueForTest
import java.lang.RuntimeException

class MeetingRoomDetailViewModelShould : BaseUntTest() {

    private lateinit var viewModel: MrDetailViewModel
    private val service : MrDetailService = mock()
    private val meetingRoomDetail : MeetingRoomDetail = mock()
    val id : String = "1"
    private val exception = RuntimeException("Something went wrong.")
    private val expected = Result.success(meetingRoomDetail)

    @Test
    fun getMeetingRoomDetailFromService() = runBlockingTest{

        mockSuccessfulCase()
        viewModel.mrDetails.getValueForTest()
        verify(service, times(1)).fetchMeetingRoomDetail(id)
    }

    @Test
    fun emitMeetingRoomDetailFromService()= runBlockingTest{
        mockSuccessfulCase()
        assertEquals(expected, viewModel.mrDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
    mockErrorCase()
        Assert.assertEquals(exception, viewModel.mrDetails.getValueForTest()!!.exceptionOrNull())
    }


    private fun mockSuccessfulCase()  {
        runBlocking {
            whenever(service.fetchMeetingRoomDetail(id)).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        viewModel = MrDetailViewModel(service)
        viewModel.getMeetingRoomDetails(id)
    }

    private fun mockErrorCase(){
        runBlocking {
            whenever(service.fetchMeetingRoomDetail(id)).thenReturn(
                flow {
                    emit(Result.failure<MeetingRoomDetail>(exception))
                }
            )
        }
        viewModel = MrDetailViewModel(service)
        viewModel.getMeetingRoomDetails(id)
    }
}