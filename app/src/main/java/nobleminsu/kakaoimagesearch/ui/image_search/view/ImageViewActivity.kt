package nobleminsu.kakaoimagesearch.ui.image_search.view

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.igreenwood.loupe.Loupe
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

        Loupe.create(binding.imageViewImageView, binding.constraintLayoutImageView) {
            onViewTranslateListener = object : Loupe.OnViewTranslateListener {
                override fun onDismiss(view: ImageView) {
                    finish()
                }

                override fun onRestore(view: ImageView) {
                }

                override fun onStart(view: ImageView) {
                }

                override fun onViewTranslate(view: ImageView, amount: Float) {
                }

            }
        }
    }
}