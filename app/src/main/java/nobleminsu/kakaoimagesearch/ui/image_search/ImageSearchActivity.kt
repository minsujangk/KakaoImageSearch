package nobleminsu.kakaoimagesearch.ui.image_search

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nobleminsu.kakaoimagesearch.R
import nobleminsu.kakaoimagesearch.databinding.ActivityImageSearchBinding
import nobleminsu.kakaoimagesearch.ui.common.observeOnLifecycle
import nobleminsu.kakaoimagesearch.ui.image_search.view.ImageViewActivity

@AndroidEntryPoint
class ImageSearchActivity : AppCompatActivity() {
    private val binding: ActivityImageSearchBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_image_search)
    }

    private val imageSearchViewModel: ImageSearchViewModel by viewModels()
    private val imageSearchEventViewModel: ImageSearchEventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarImageSearch)

        binding.viewModel = imageSearchViewModel

        binding.recyclerViewImageSearch.adapter = ImageSearchPagedListAdapter(
            imageSearchEventViewModel::clickImage
        ).apply {
            imageSearchViewModel.searchedDocuments.observe(this@ImageSearchActivity) {
                submitList(it)
            }
        }

        binding.recyclerViewImageSearchTag.adapter =
            ImageSearchCollectionTagRecyclerAdapter(
                imageSearchViewModel::toggleCollection
            ).apply {
                imageSearchViewModel.tags.observe(this@ImageSearchActivity) {
                    collectionList = it
                    notifyDataSetChanged()
                }
                imageSearchViewModel.selectedCollection.observe(this@ImageSearchActivity) {
                    selectedCollection = it
                    notifyDataSetChanged()
                }
            }

        imageSearchEventViewModel.eventsFlow.observeOnLifecycle(this) {
            when (it) {
                is ImageSearchEventViewModel.Event.ShowImage -> {
                    startActivity(Intent(this, ImageViewActivity::class.java).apply {
                        putExtra(ImageViewActivity.KEY_DOCUMENT_DTO, it.documentDto)
                    })
                }
            }
        }

        binding.searchViewImageSearch.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                imageSearchViewModel.query.value = if (p0.isNullOrBlank()) "kakao" else p0
                return true
            }
        })
    }
}