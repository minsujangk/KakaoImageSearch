package nobleminsu.kakaoimagesearch.ui.image_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nobleminsu.kakaoimagesearch.databinding.ItemImageSearchTagBinding

class ImageSearchCollectionTagRecyclerAdapter(
    private val onClickCollection: (String) -> Unit
) : RecyclerView.Adapter<ImageSearchCollectionTagRecyclerAdapter.ViewHolder>() {
    var collectionList: List<String>? = null
    var selectedCollection: List<String>? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        collectionList?.get(position)?.let { collection ->
            holder.bind(
                collection = collection,
                selected = when (collection) {
                    ImageSearchViewModel.COLLECTION_ALL -> {
                        selectedCollection.isNullOrEmpty()
                    }
                    else -> {
                        selectedCollection?.contains(collection) ?: false
                    }
                },
                onClick = { onClickCollection(collection) }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageSearchTagBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return collectionList?.size ?: 0
    }

    class ViewHolder(private val binding: ItemImageSearchTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(collection: String, selected: Boolean, onClick: () -> Unit) {
            binding.tagName = collection
            binding.selected = selected
            binding.onClick = onClick
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