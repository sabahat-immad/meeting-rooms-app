package saba.qazi.meetingrooms.meetingroomslist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom
import javax.inject.Inject

class MrListRepository @Inject constructor(
    private val service: MrListService
) {
    suspend fun getMeetingRoomsList() : Flow<Result<List<MeetingRoom>>> =
            service.fetchMeetingRoomsList().map { response ->
                if(response.isSuccess)
                Result.success(response.getOrNull()!!.sortedBy { it.name })
                else
                    Result.failure(response.exceptionOrNull()!!)
            }


}
