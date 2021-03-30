package saba.qazi.meetingrooms.meetingroomslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_meetingroomslist.*
import kotlinx.android.synthetic.main.fragment_meetingroomslist.view.*
import saba.qazi.meetingrooms.R
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom
import javax.inject.Inject

@AndroidEntryPoint
class MeetingRoomsListFragment : Fragment() {
    lateinit var viewModel : MeetingRoomsListViewModel

    @Inject
    lateinit var viewModelFactory : MeetingRoomsListViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meetingroomslist, container, false)

        setupViewModel()

        observeLoader()

        observeNewsList(view)

        return view
    }

    private fun observeLoader() {
        viewModel.loader.observe(this as LifecycleOwner, { loading ->
            when (loading) {
                true -> loader.visibility = View.VISIBLE
                else -> loader.visibility = View.GONE
            }
        })
    }

    private fun observeNewsList(view: View) {
        viewModel.meetingRoomsList.observe(this as LifecycleOwner, { mrList ->
            if (mrList.getOrNull() != null)
                setupList(view.meetingrooms_list, mrList.getOrNull()!!)
            else {
                showErrorToast()
            }
        })
    }

    private fun setupList(
        view: View?,
        mrList : List<MeetingRoom>
    ) {
        with (view as RecyclerView) {

                layoutManager = LinearLayoutManager(context)
                adapter = MrListRecyclerViewAdapter(mrList) { key ->
                    val action =
                        MeetingRoomsListFragmentDirections.
                        actionMeetingRoomsListFragmentToMeetingRoomDetailFragment(key)
                    findNavController().navigate(action)
                }

        }
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(this, viewModelFactory).get(MeetingRoomsListViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MeetingRoomsListFragment().apply {

            }
    }

    private fun showErrorToast() {
        Toast.makeText(
            activity, "Oops, Something wrong...please check your internet" +
                    " connection and try again", Toast.LENGTH_SHORT
        ).show()
    }
}