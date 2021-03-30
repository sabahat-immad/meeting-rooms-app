package saba.qazi.meetingrooms.meetingroomslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MeetingRoomsListViewModelFactory @Inject constructor(
        private val repository: MrListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MeetingRoomsListViewModel(repository) as T
    }

}
