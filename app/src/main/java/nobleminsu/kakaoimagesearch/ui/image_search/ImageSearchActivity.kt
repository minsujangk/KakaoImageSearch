package nobleminsu.kakaoimagesearch.ui.image_search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
            lifecycleScope.launchWhenCreated {
                imageSearchViewModel.searchDocuments.collectLatest {
                    submitData(lifecycle, it)
                }
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
    }
}