package saba.qazi.meetingrooms.meetingroomdetail

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_meetingroom_detail.*
import saba.qazi.meetingrooms.R
import saba.qazi.meetingrooms.meetingroomdetail.data.MeetingRoomDetail
import javax.inject.Inject

@AndroidEntryPoint
class MeetingRoomDetailFragment : Fragment() {

   private val args : MeetingRoomDetailFragmentArgs by navArgs()

    lateinit var viewModel : MrDetailViewModel

    @Inject
    lateinit var viewModelFactory : MrDetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_meetingroom_detail, container, false)
        val id = args.key

        setupViewModel()

        viewModel.getMeetingRoomDetails(id)

        observeMeetingRoomDetails()

        return view
    }




    companion object {

        @JvmStatic
        fun newInstance() =
            MeetingRoomDetailFragment()
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(this, viewModelFactory).get(MrDetailViewModel::class.java)
    }

    private fun observeMeetingRoomDetails() {
        viewModel.mrDetails.observe(this as LifecycleOwner) { mrDetails ->
            if (mrDetails.getOrNull() != null) {
                setupUI(mrDetails.getOrNull()!!)
            } else {
                showErrorToast()
            }
        }
    }



    private fun setupUI(mrDetail: MeetingRoomDetail) {
        name.text = mrDetail.name
        Glide
            .with(this)
            .load(mrDetail.heroImageUrl)
            .placeholder(null)
            .into(hero_image)

        capacity.text = "Capacity: ${mrDetail.capacity}"
        location.text = "${mrDetail.location.floor.name}" + "\n" + "${mrDetail.location.building.name}" +
        "\n" + "${mrDetail.location.site.name}" + "\n" + "${mrDetail.location.region.name}"

        for(item in mrDetail.equipment){
            equipment.text = equipment.text.toString() + "\n" + "${item.name}"
        }
        for(item in mrDetail.features){
            features.text = features.text.toString() + "\n" + "${item.name}"
        }
        for(item in mrDetail.services){
            services.text = services.text.toString() + "\n" + "${item.name}"
        }

        val fac_icons: MutableList<String> = mutableListOf<String>()
        for(item in mrDetail.facilities) {
         fac_icons.add(item.iconUrl)
        }
        val facilityAdapter = activity?.let { FacilitiesAdapter(it, fac_icons) }
        facility_list.adapter = facilityAdapter


    }

    private fun showErrorToast() {
        Toast.makeText(
            activity, "Oops, Something wrong...please check your internet" +
                    " connection and try again", Toast.LENGTH_SHORT
        ).show()
    }


}