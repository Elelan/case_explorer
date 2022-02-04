package com.ezio.caseexplorer.ui.feature_cases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezio.caseexplorer.core.domain.models.Answer
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.ui.BaseViewHolder
import com.ezio.caseexplorer.databinding.ListItemAnswerBinding
import com.ezio.caseexplorer.databinding.ListItemScenarioBinding

class AnswerAdapter(
    private val delegate: ItemClickListener
) :
    ListAdapter<Answer, AnswerAdapter.AnswerViewHolder>(DiffCallback()) {

    var previousCheckedPosition = -1
    var checkedPosition = -1

    inner class AnswerViewHolder(private val binding: ListItemAnswerBinding) :
        BaseViewHolder<Answer>(binding.root) {

        init {
            binding.apply {
//                checkbox.setOnClickListener {
//                    val position = adapterPosition
//                    if(position != RecyclerView.NO_POSITION) {
//                        val item = getItem(position)
//                        delegate.onAnswerClicked(item)
//                    }
//                }
            }
        }

        override fun bind(item: Answer) = with(binding) {
            var isChecked = adapterPosition == checkedPosition

            if(isChecked) {
                previousCheckedPosition = adapterPosition
                checkbox.isChecked = true
            } else {
                checkbox.isChecked = false
            }


            checkbox.setOnClickListener {
                checkedPosition = if(isChecked) -1 else adapterPosition
                notifyItemChanged(previousCheckedPosition)
                notifyItemChanged(checkedPosition)
                delegate.onAnswerClicked(item)
            }

            answer = item
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Answer>() {
        override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean =
            oldItem.caseid == newItem.caseid

        override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val itemBinding =
            ListItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.bind(currentItem)
    }

    interface ItemClickListener {
        fun onAnswerClicked(item: Answer)
    }
}