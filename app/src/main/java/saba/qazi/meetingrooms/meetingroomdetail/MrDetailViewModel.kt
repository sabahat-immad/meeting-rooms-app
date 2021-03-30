package saba.qazi.meetingrooms.meetingroomdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import saba.qazi.meetingrooms.meetingroomdetail.data.MeetingRoomDetail

class MrDetailViewModel(
    private val service: MrDetailService
) : ViewModel(){

    val mrDetails : MutableLiveData<Result<MeetingRoomDetail>> = MutableLiveData()

    fun getMeetingRoomDetails(id: String) {
        viewModelScope.launch {
            service.fetchMeetingRoomDetail(id)
                .collect {
                    mrDetails.postValue(it)
                }
        }

    }

}
