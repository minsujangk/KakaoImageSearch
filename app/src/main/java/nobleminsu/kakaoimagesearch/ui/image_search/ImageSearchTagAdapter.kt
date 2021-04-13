package nobleminsu.kakaoimagesearch.ui.image_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nobleminsu.kakaoimagesearch.databinding.ItemImageSearchTagBinding

class ImageSearchTagAdapter : RecyclerView.Adapter<ImageSearchTagAdapter.ViewHolder>() {
    var tagList: List<String>? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        tagList?.get(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageSearchTagBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return tagList?.size ?: 0
    }

    class ViewHolder(private val binding: ItemImageSearchTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(collection: String) {
            binding.tagName = collection
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}