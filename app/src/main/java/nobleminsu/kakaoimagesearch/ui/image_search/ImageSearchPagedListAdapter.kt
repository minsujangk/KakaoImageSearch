package nobleminsu.kakaoimagesearch.ui.image_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.databinding.ItemImageSearchDocumentBinding
import timber.log.Timber

// TODO: Image gallery view로 대체
class ImageSearchPagedListAdapter(
    private val onClickImageItem: (document: ImageSearchResponseDocumentDto) -> Unit
) : PagedListAdapter<ImageSearchResponseDocumentDto, ImageSearchPagedListAdapter.ViewHolder>(DIFF) {
    private var filteredList: List<Pair<Int, ImageSearchResponseDocumentDto>>? = null
    var filterCalculatedSize = 0
    var collectionFilters: List<String>? = null
        set(value) {
            field = value
            currentList?.let {
                updateFilteredList(it)
                notifyDataSetChanged()
            }
        }

    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                Timber.e("dapi item range changed start=$positionStart count=$itemCount")
                currentList?.let { updateFilteredList(it) }
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                Timber.e("dapi item range inserted start=$positionStart count=$itemCount")
                currentList?.let { updateFilteredList(it) }
            }
        })
    }

    override fun submitList(pagedList: PagedList<ImageSearchResponseDocumentDto>?) {
        if (pagedList != null) {
            updateFilteredList(pagedList)
        }
        super.submitList(pagedList)
    }

    override fun submitList(
        pagedList: PagedList<ImageSearchResponseDocumentDto>?,
        commitCallback: Runnable?
    ) {
        if (pagedList != null) {
            updateFilteredList(pagedList)
        }
        super.submitList(pagedList, commitCallback)
    }

    private fun updateFilteredList(list: PagedList<ImageSearchResponseDocumentDto>) {
        filteredList = list
            .mapIndexed { index, documentDto -> index to documentDto }
            .filter { isFilteredDocument(it.second) }
        filterCalculatedSize = list.size
        println("dapi filtered size=${filteredList?.size}, orig size=$filterCalculatedSize")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        filteredList?.let {
            val lastItemIndex = it.last().first
            if (position < it.size) {
                getItem(it[position].first)?.let {
                    holder.bind(
                        it,
                        isFilteredDocument(it),
                        onClickImageItem
                    )
                }
            } else {
                Timber.e("dapi lastItemIndex=$lastItemIndex getItem(${position - it.lastIndex + lastItemIndex})")
                getItem(position - it.lastIndex + lastItemIndex)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageSearchDocumentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        val origCount = super.getItemCount()
        val lastItemIndex = filteredList?.lastOrNull()?.first ?: 0
        Timber.e("dapi super item count=$origCount filtered count=${filteredList?.size} " +
                "returning count=${filteredList?.run { origCount + lastIndex - lastItemIndex } ?: 0} ")
        return (filteredList?.run { origCount + lastIndex - lastItemIndex } ?: 0)
    }

    private fun isFilteredDocument(documentDto: ImageSearchResponseDocumentDto) =
        collectionFilters?.run {
            if (isEmpty()) true else contains(documentDto.collection)
        } ?: true


    class ViewHolder(private val binding: ItemImageSearchDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            documentDto: ImageSearchResponseDocumentDto,
            isFiltered: Boolean,
            onClick: (ImageSearchResponseDocumentDto) -> Unit
        ) {
            binding.document = documentDto
            binding.onClick = onClick
            binding.root.isVisible = isFiltered
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