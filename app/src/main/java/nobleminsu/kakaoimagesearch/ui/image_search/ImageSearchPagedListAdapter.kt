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

class ImageSearchPagedListAdapter(
    private val onClickImageItem: (document: ImageSearchResponseDocumentDto) -> Unit
) : PagedListAdapter<ImageSearchResponseDocumentDto, ImageSearchPagedListAdapter.ViewHolder>(DIFF) {
    private var filteredList: List<Pair<Int, ImageSearchResponseDocumentDto>>? = null
    var collectionFilters: List<String>? = null
        set(value) {
            field = value
            currentList?.let {
                updateFilteredList(it)
                notifyDataSetChanged()
            }
        }

    // TODO: 테스트 작성
    // TODO: 탭 전환시 더 로드되는 문제 살펴보기
    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                currentList?.let { updateFilteredList(it) }
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
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
        val oldFilteredSize = filteredList?.size
        filteredList = list
            .mapIndexed { index, documentDto -> index to documentDto }
            .filter { isFilteredDocument(it.second) }
            .also {
                if (oldFilteredSize == it.size) currentList?.apply {
                    if (loadedCount - 1 >= 0) loadAround(loadedCount - 1)
                }
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        filteredList?.also {
            // TODO: prettify codes, variables
            val curItemIndex = it[position].first
            if (curItemIndex >= currentList?.size ?: 0) return
            getItem(curItemIndex)?.let {
                holder.bind(
                    it,
                    isFilteredDocument(it),
                    onClickImageItem
                )
            }
            if (position == it.lastIndex) {
                currentList?.apply {
                    loadAround(loadedCount - 1)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageSearchDocumentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return filteredList?.size ?: 0
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