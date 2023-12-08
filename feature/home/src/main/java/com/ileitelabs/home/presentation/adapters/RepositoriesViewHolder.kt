package com.ileitelabs.home.presentation.adapters

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.repotrends.feature.home.R
import com.ileitelabs.repotrends.foundation.designsystem.R as DesignSystemR
import com.ileitelabs.repotrends.feature.home.databinding.HomeRepositoryItemBinding

class RepositoriesViewHolder(
    private val binding: HomeRepositoryItemBinding,
    private val onClickListener: (repository: Repository, position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun initView(repository: Repository) {
        binding.apply {
            tvOwnerName.setSafeText(repository.owner.name.orEmpty())
            tvRepositoryName.setSafeText(repository.name.orEmpty())
            tvStarsValue.setSafeText(repository.stars.toString())
            tvForksValue.setSafeText(repository.forks.toString())
            ivRepositoryImage.load(repository.owner.img) {
                crossfade(true)
                placeholder(DesignSystemR.drawable.ic_placeholder)
                error(DesignSystemR.drawable.ic_placeholder)
                transformations(CircleCropTransformation())
            }
            root.setOnClickListener {
                onClickListener(repository, layoutPosition)
            }
        }
    }

    private fun TextView.setSafeText(currentText: String) {
        text = if (currentText.trim().isEmpty()) {
            binding.root.context.getString(R.string.home_not_available)
        } else {
            currentText
        }
    }
}