package saba.qazi.meetingrooms.meetingroomslist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom
import saba.qazi.meetingrooms.utils.BaseUntTest
import java.lang.RuntimeException

class MeetingRoomListRepositoryShould : BaseUntTest() {

    private val exception: Throwable = RuntimeException("something went wrong")
    private val service : MrListService = mock()
    private val mrList : List<MeetingRoom> = mock<List<MeetingRoom>>()


    @Test
    fun getMeetingRoomsListsFromService() = runBlockingTest {
        val repository : MrListRepository = mockSuccessfulCase()
        repository.getMeetingRoomsList()
        verify(service, times(1)).fetchMeetingRoomsList()

    }

    @Test
    fun emitMeetingRoomsListsFromService()= runBlockingTest{
        val repository = mockSuccessfulCase()
        assertEquals(mrList, repository.getMeetingRoomsList().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getMeetingRoomsList().first().exceptionOrNull())
    }

    private suspend fun mockFailureCase() : MrListRepository{
        whenever(service.fetchMeetingRoomsList()).thenReturn(
                flow {
                    emit(Result.failure<List<MeetingRoom>>(exception))
                }
        )
        return MrListRepository(service)
    }

    private suspend fun mockSuccessfulCase() : MrListRepository{
        whenever(service.fetchMeetingRoomsList()).thenReturn(
                flow {
                    emit(Result.success(mrList))
                }
        )


        return MrListRepository(service)
    }
}