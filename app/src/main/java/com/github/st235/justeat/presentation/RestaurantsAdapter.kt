package com.github.st235.justeat.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.st235.justeat.R
import com.github.st235.justeat.domain.Restaurant
import com.github.st235.justeat.presentation.utils.show
import com.squareup.picasso.Picasso

class RestaurantsAdapter: RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    private val listDiffer = AsyncListDiffer(this, ItemCallback())

    private val items: List<Restaurant>
    get() {
        return listDiffer.currentList
    }

    fun setItems(newItems: List<Restaurant>) {
        listDiffer.submitList(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }

        payloads.filterIsInstance<Payload>()
            .forEach { payload ->
                payload.title?.let {
                    holder.titleTextView.text = payload.title
                }

                payload.description?.let {
                    val description = payload.description
                    holder.descriptionTextView.text = description
                    holder.descriptionTextView.show(isShown = description.isNotEmpty())
                }

                payload.logo?.let {
                    holder.loadLogo(payload.logo)
                }

                payload.rating?.let {
                    holder.ratingTextView.text = payload.rating.toString()
                }
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = items[position]

        holder.titleTextView.text = restaurant.name
        holder.descriptionTextView.text = restaurant.description
        holder.descriptionTextView.show(isShown = restaurant.description.isNotEmpty())
        holder.ratingTextView.text = restaurant.rating.toString()
        holder.loadLogo(restaurant.logo)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val iconImageView: AppCompatImageView = itemView.findViewById(R.id.icon)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val ratingTextView: TextView = itemView.findViewById(R.id.rating_score)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)

        init {
            itemView.isClickable = true
            itemView.isFocusable = true
            itemView.setOnClickListener {  }
        }

        fun loadLogo(iconUrl: String) {
            Picasso.get()
                .load(iconUrl)
                .into(iconImageView)
        }
    }

    class ItemCallback: DiffUtil.ItemCallback<Restaurant>() {

        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Restaurant, newItem: Restaurant): Payload {
            return Payload(
                title = newItem.name.takeIf { oldItem.name != newItem.name },
                description = newItem.description.takeIf { oldItem.description != newItem.description },
                logo = newItem.logo.takeIf { oldItem.logo != newItem.logo },
                rating = newItem.rating.takeIf { oldItem.rating != newItem.rating }
            )
        }
    }

    data class Payload(
        val title: String?,
        val description: String?,
        val logo: String?,
        val rating: Double?
    )
}
