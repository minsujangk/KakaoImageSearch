package nobleminsu.kakaoimagesearch.ui.image_search.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nobleminsu.kakaoimagesearch.R
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.databinding.ActivityImageViewBinding
import javax.inject.Inject

@AndroidEntryPoint
class ImageViewActivity : AppCompatActivity() {
    companion object {
        const val KEY_DOCUMENT_DTO = "document-dto"
    }

    private val documentDto by lazy {
        intent?.getParcelableExtra<ImageSearchResponseDocumentDto>(KEY_DOCUMENT_DTO)
    }

    private val binding: ActivityImageViewBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_image_view)
    }

    @Inject
    lateinit var imageViewViewModelFactory: ImageViewViewModel.Factory

    private val imageViewViewModel: ImageViewViewModel by viewModels {
        ImageViewViewModel.provideFactory(imageViewViewModelFactory, documentDto)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = imageViewViewModel
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbarImageView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        imageViewViewModel.documentDtoLiveData.observe(this) {
            supportActionBar?.title = it.collection
        }

        binding.imageViewImageView.setOnClickListener {
            imageViewViewModel.isImageInfoShown.value =
                imageViewViewModel.isImageInfoShown.value?.not()
        }

        imageViewViewModel.isImageInfoShown.observe(this) {
            animateImageInfoVisibilityChange(
                binding.toolbarImageView,
                binding.constraintLayoutImageViewInfo,
                it
            )
            handleImageInfoAutoHide(imageViewViewModel, it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}