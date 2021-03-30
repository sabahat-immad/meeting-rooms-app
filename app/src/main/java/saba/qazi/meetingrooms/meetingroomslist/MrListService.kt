package saba.qazi.meetingrooms.meetingroomslist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom
import java.lang.RuntimeException
import javax.inject.Inject

class MrListService @Inject constructor(
        private val meetingRoomsListAPI: MeetingRoomsListAPI
)  {

    suspend fun fetchMeetingRoomsList() : Flow<Result<List<MeetingRoom>>> {

         return flow{
             val newsList : List<MeetingRoom> = meetingRoomsListAPI.fetchMeetingRooms()
             emit(Result.success(newsList))
         }.catch {
             emit(Result.failure(RuntimeException("Something went wrong")))
         }
}

}
