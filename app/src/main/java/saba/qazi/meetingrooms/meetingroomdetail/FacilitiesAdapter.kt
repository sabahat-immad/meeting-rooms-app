package saba.qazi.meetingrooms.meetingroomdetail

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import saba.qazi.meetingrooms.R

class FacilitiesAdapter(private val context: Activity, private val imageList : List<String>)
        : ArrayAdapter<String>(context, R.layout.custom_list, imageList) {

        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.custom_list, null, true)

            val imageView = rowView.findViewById(R.id.icon) as ImageView
            Glide
                .with(context)
                .load(imageList[position])
                .placeholder(null)
                .into(imageView)

            return rowView
        }
    }
