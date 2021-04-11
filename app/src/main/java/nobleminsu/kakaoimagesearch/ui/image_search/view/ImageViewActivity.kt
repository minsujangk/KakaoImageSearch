package nobleminsu.kakaoimagesearch.ui.image_search.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

        binding.imageViewImageView.setOnClickListener {
            imageViewViewModel.isImageInfoShown.value =
                imageViewViewModel.isImageInfoShown.value?.not()
        }

        imageViewViewModel.isImageInfoShown.observe(this) {
            fun View.startEnterAnim(fromTop: Boolean) {
                animate().alpha(1f)
                    .withStartAction {
                        isVisible = true
                        alpha = 0f
                        translationY =
                            (height * (if (fromTop) -1 else 1)).toFloat()
                    }
                    .translationY(0f)
                    .withEndAction { isVisible = true }
                    .start()
            }

            fun View.startExitAnim(toTop: Boolean) {
                animate().alpha(0f)
                    .withStartAction {
                        alpha = 1f
                        translationY = 0f
                    }
                    .translationYBy((height * (if (toTop) -1 else 1)).toFloat())
                    .withEndAction { isVisible = false }
                    .start()
            }
            if (it) {
                binding.toolbarImageView.startEnterAnim(true)
                binding.constraintLayoutImageViewInfo.startEnterAnim(false)
            } else {
                binding.toolbarImageView.startExitAnim(true)
                binding.constraintLayoutImageViewInfo.startExitAnim(false)
            }
            if (it) {
                imageViewViewModel.hideViewAfter(5000)
            } else {
                imageViewViewModel.cancelHideViewJob()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}