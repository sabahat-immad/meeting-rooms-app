package saba.qazi.meetingrooms.meetingroomslist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.onEach
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom

class MeetingRoomsListViewModel(
        private val repository: MrListRepository
) : ViewModel(){

    val loader = MutableLiveData<Boolean>()
    val meetingRoomsList = liveData<Result<List<MeetingRoom>>> {
        loader.postValue(true)
        emitSource(repository.getMeetingRoomsList()
                .onEach {
                    loader.postValue(false)
                }
                .asLiveData())
    }

}
