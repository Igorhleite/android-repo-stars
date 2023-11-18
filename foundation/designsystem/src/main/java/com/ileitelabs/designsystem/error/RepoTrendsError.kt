package com.ileitelabs.designsystem.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ileitelabs.repotrends.foundation.designsystem.databinding.RepoTrendsErrorScreenBinding

class RepoTrendsError @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: RepoTrendsErrorScreenBinding =
        RepoTrendsErrorScreenBinding.inflate(LayoutInflater.from(context), this, true)

    fun serErrorText(text: String) {
        binding.tvError.text = text
    }

    fun onClickTryAgainButton(action: () -> Unit) {
        binding.btErrorRefresh.setOnClickListener {
            action()
        }
    }
}