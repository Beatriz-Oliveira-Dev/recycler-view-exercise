package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerview.models.Live
import kotlinx.android.synthetic.main.res_item_live.view.*

class LiveAdapter(private val onItemClicked: (Live) -> Unit)  : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items : List<Live> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LiveViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.res_item_live, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is LiveViewHolder ->{
                holder.bind(items[position],onItemClicked)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataSet(lives : List<Live>){
        this.items = lives
    }

    class LiveViewHolder constructor(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView) {

        private var liveTitle = itemView.title
        private var liveAuthor = itemView.author
        private var liveThumbnail = itemView.thumbnail

        fun bind(live: Live, onItemClicked:(Live) -> Unit){
            liveTitle.text = live.title
            liveAuthor.text = live.author
//            liveThumbnail

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(live.thumbnailUrl)
                .into(liveThumbnail)

            itemView.setOnClickListener{
                onItemClicked(live)
            }
        }

    }

}