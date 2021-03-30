package saba.qazi.meetingrooms.meetingroomdetail

import retrofit2.http.GET
import retrofit2.http.Path
import saba.qazi.meetingrooms.meetingroomdetail.data.MeetingRoomDetail

interface MrDetailAPI {

    @GET("detail/{id}.json")
    suspend fun fetchMeetingRoomDetail(@Path("id")id: String) : MeetingRoomDetail

}
