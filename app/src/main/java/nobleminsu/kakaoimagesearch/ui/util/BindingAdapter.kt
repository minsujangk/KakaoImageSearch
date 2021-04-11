package nobleminsu.kakaoimagesearch.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import nobleminsu.kakaoimagesearch.R
import timber.log.Timber

@BindingAdapter("app:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String) {
    Timber.e("loading $imageUrl")
    Glide.with(imageView)
        .asBitmap()
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(imageView)
}