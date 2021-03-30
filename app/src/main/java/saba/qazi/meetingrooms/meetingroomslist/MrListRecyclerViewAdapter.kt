package saba.qazi.meetingrooms.meetingroomslist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import saba.qazi.meetingrooms.R
import saba.qazi.meetingrooms.meetingroomslist.data.MeetingRoom


class MrListRecyclerViewAdapter(
    private val values: List<MeetingRoom>,
    private val listener : (String) -> Unit
) : RecyclerView.Adapter<MrListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meetingrooms_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

            holder.name.text = item.name
            holder.capacity.text = "Seating capacity: ${item.capacity}"

            holder.root.setOnClickListener { listener(item.key!!) }

            Glide
                .with(holder.thumbnail.context)
                .load(item.thumbnailUrl)
                .placeholder(null)
                .into(holder.thumbnail);

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.f_name)
        val capacity: TextView = view.findViewById(R.id.capacity)
        val thumbnail : ImageView = view.findViewById(R.id.mr_image)
        val root : View = view.findViewById(R.id.item_root)

    }
}