package com.ileitelabs.home.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ileitelabs.home.domain.model.Repository
import com.ileitelabs.repotrends.feature.home.databinding.HomeRepositoryItemBinding

typealias OnRepositoryClick = (repository: Repository, position: Int) -> Unit

class RepositoriesAdapter(
    private val onClickListener: OnRepositoryClick
) : PagingDataAdapter<Repository, RepositoriesViewHolder>(RepositoriesAdapter) {

    private companion object : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        getItem(position)?.let { repository ->
            holder.initView(repository)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val biding = HomeRepositoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepositoriesViewHolder(biding, onClickListener)
    }
}