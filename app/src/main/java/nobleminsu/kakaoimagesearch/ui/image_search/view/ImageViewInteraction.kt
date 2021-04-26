package nobleminsu.kakaoimagesearch.ui.image_search.view

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible

fun animateImageInfoVisibilityChange(toolbar: Toolbar, bottomView: View, isVisible: Boolean) {
    fun View.startEnterAnim(fromTop: Boolean) {
        animate().alpha(1f)
            .withStartAction {
                this.isVisible = true
                alpha = 0f
                translationY =
                    (height * (if (fromTop) -1 else 1)).toFloat()
            }
            .translationY(0f)
            .withEndAction { this.isVisible = true }
            .start()
    }

    fun View.startExitAnim(toTop: Boolean) {
        animate().alpha(0f)
            .withStartAction {
                alpha = 1f
                translationY = 0f
            }
            .translationYBy((height * (if (toTop) -1 else 1)).toFloat())
            .withEndAction { this.isVisible = false }
            .start()
    }
    if (isVisible) {
        toolbar.startEnterAnim(true)
        bottomView.startEnterAnim(false)
    } else {
        toolbar.startExitAnim(true)
        bottomView.startExitAnim(false)
    }
}

fun handleImageInfoAutoHide(
    imageViewViewModel: ImageViewViewModel,
    isVisible: Boolean
) {
    if (isVisible) {
        imageViewViewModel.hideViewAfter(5000)
    } else {
        imageViewViewModel.cancelHideViewJob()
    }
}