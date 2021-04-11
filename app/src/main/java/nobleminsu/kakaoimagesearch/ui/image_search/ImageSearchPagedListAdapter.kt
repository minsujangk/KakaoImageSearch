package nobleminsu.kakaoimagesearch.ui.image_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.databinding.ItemImageSearchDocumentBinding

// TODO: Image gallery view로 대체
class ImageSearchPagedListAdapter(
    private val onClickImageItem: (document: ImageSearchResponseDocumentDto) -> Unit
) : PagingDataAdapter<ImageSearchResponseDocumentDto, ImageSearchPagedListAdapter.ViewHolder>(DIFF) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClickImageItem) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageSearchDocumentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class ViewHolder(private val binding: ItemImageSearchDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            documentDto: ImageSearchResponseDocumentDto,
            onClick: (ImageSearchResponseDocumentDto) -> Unit
        ) {
            binding.document = documentDto
            binding.onClick = onClick
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<ImageSearchResponseDocumentDto>() {
            override fun areItemsTheSame(
                oldItem: ImageSearchResponseDocumentDto,
                newItem: ImageSearchResponseDocumentDto
            ): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(
                oldItem: ImageSearchResponseDocumentDto,
                newItem: ImageSearchResponseDocumentDto
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}