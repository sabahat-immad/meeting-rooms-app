package saba.qazi.meetingrooms.meetingroomdetail

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import saba.qazi.meetingrooms.meetingroomdetail.data.MeetingRoomDetail
import javax.inject.Inject

class MrDetailService @Inject constructor(
    private val mrDetailAPI: MrDetailAPI
){

    suspend fun fetchMeetingRoomDetail(id: String) : Flow<Result<MeetingRoomDetail>> {
        return flow{

            val mrDetail : MeetingRoomDetail = mrDetailAPI.fetchMeetingRoomDetail(id)
            emit(Result.success(mrDetail))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }


}
