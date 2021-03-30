package saba.qazi.meetingrooms.meetingroomdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MrDetailViewModelFactory @Inject constructor(
    private val service: MrDetailService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MrDetailViewModel(service) as T
    }

}

