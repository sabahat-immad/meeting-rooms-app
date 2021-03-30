package saba.qazi.meetingrooms.meetingroomslist

import retrofit2.http.GET
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom

interface MeetingRoomsListAPI {

    @GET("rooms.json")
    suspend fun fetchMeetingRooms() : List<MeetingRoom>



}
