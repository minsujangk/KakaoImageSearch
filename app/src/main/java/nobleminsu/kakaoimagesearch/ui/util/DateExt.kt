package nobleminsu.kakaoimagesearch.ui.util

import android.annotation.SuppressLint
import nobleminsu.kakaoimagesearch.KakaoImageSearchApplication
import nobleminsu.kakaoimagesearch.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("StaticFieldLeak")
//object DateExt {

    fun formatDate(date: Date): String {
        val today = Date()
        val days = TimeUnit.DAYS.toDays(today.time - date.time)
        return if (days < 7) KakaoImageSearchApplication.appCtx.getString(R.string.days_ago, days)
        else SimpleDateFormat.getDateInstance().format(date)
    }
//}