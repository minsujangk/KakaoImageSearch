package nobleminsu.kakaoimagesearch.ui.image_search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import nobleminsu.kakaoimagesearch.R
import nobleminsu.kakaoimagesearch.databinding.ActivityImageSearchBinding

@AndroidEntryPoint
class ImageSearchActivity : AppCompatActivity() {
    private val binding: ActivityImageSearchBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_image_search)
    }

    private val imageSearchViewModel: ImageSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarImageSearch)

        binding.recyclerViewImageSearch.adapter = ImageSearchPagedListAdapter().apply {
            lifecycleScope.launchWhenCreated {
                imageSearchViewModel.searchDocuments.collectLatest {
                    submitData(lifecycle, it)
                }
            }
        }
    }
}