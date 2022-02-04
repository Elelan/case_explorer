package com.ezio.caseexplorer.ui.feature_scenarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.ui.BaseViewHolder
import com.ezio.caseexplorer.databinding.ListItemScenarioBinding

class ScenarioAdapter(
    private val delegate: ItemClickListener
) :
    ListAdapter<ScenarioItem, ScenarioAdapter.ScenarioViewHolder>(DiffCallback()) {

    inner class ScenarioViewHolder(private val binding: ListItemScenarioBinding) :
        BaseViewHolder<ScenarioItem>(binding.root) {
        override fun bind(item: ScenarioItem) = with(binding) {
            scenarioItem = item
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ScenarioItem>() {
        override fun areItemsTheSame(oldItem: ScenarioItem, newItem: ScenarioItem): Boolean =
            oldItem.caseId == newItem.caseId

        override fun areContentsTheSame(oldItem: ScenarioItem, newItem: ScenarioItem): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val itemBinding =
            ListItemScenarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScenarioViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    interface ItemClickListener {
        fun onScenarioClicked(item: ScenarioItem)
    }
}