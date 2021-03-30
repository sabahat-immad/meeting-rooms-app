package saba.qazi.meetingrooms.meetingroomslist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom
import saba.qazi.meetingrooms.utils.BaseUntTest
import saba.qazi.meetingrooms.utils.captureValues
import saba.qazi.meetingrooms.utils.getValueForTest
import java.lang.RuntimeException


class MeetingRoomsListViewModelShould : BaseUntTest() {

    private val repository : MrListRepository = mock()
    private val mrList = mock<List<MeetingRoom>>()
    private val expected = Result.success(mrList)
    private val exception = RuntimeException("Something went wrong.")


    @Test
    fun getMeetingRoomsListFromRepository() = runBlockingTest{

        val viewModel = mockSuccessfulResult()
        viewModel.meetingRoomsList.getValueForTest()
        verify(repository, times(1)).getMeetingRoomsList()
    }

    @Test
    fun emitMeetingRoomsListsFromRepository() = runBlockingTest{
        val viewModel = mockSuccessfulResult()
        assertEquals(expected, viewModel.meetingRoomsList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(){
        val viewModel = mockErrorCase()

        assertEquals(exception, viewModel.meetingRoomsList.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun displayLoaderWhenLoading() = runBlockingTest{
       val viewModel = mockSuccessfulResult()

       viewModel.loader.captureValues{
           viewModel.meetingRoomsList.getValueForTest()
           assertEquals(true, values[0])
       }
    }

    @Test
    fun hideLoaderWhenLoadingIsFinished() = runBlockingTest{
        val viewModel = mockSuccessfulResult()

        viewModel.loader.captureValues{
            viewModel.meetingRoomsList.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun hideLoaderWhenError() = runBlockingTest{
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues{
            viewModel.meetingRoomsList.getValueForTest()
            assertEquals(false, values.last())
        }
    }
    private fun mockSuccessfulResult(): MeetingRoomsListViewModel {
        runBlocking {
            whenever(repository.getMeetingRoomsList()).thenReturn(
                    flow {
                        emit(expected)
                    }
            )
        }
        return MeetingRoomsListViewModel(repository)
    }

    private fun mockErrorCase(): MeetingRoomsListViewModel {
        runBlocking {
            whenever(repository.getMeetingRoomsList()).thenReturn(
                    flow {
                        emit(Result.failure<List<MeetingRoom>>(exception))
                    }
            )
        }

        return MeetingRoomsListViewModel(repository)
    }
}