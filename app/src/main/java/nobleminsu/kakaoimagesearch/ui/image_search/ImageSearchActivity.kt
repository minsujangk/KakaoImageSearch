package nobleminsu.kakaoimagesearch.ui.image_search

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import nobleminsu.kakaoimagesearch.R
import nobleminsu.kakaoimagesearch.databinding.ActivityImageSearchBinding

class ImageSearchActivity : AppCompatActivity() {
    private val binding: ActivityImageSearchBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_image_search)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarImageSearch)
    }
}