package com.ezio.caseexplorer.ui.feature_cases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezio.caseexplorer.R
import com.ezio.caseexplorer.core.domain.models.Answer
import com.ezio.caseexplorer.core.utils.exhaustive
import com.ezio.caseexplorer.databinding.FragmentCaseBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_case.*
import kotlinx.android.synthetic.main.fragment_scenario_list.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CaseFragment: Fragment(R.layout.fragment_case), AnswerAdapter.ItemClickListener {

    lateinit var binding: FragmentCaseBinding
    val viewModel: CaseViewModel by viewModels()
    lateinit var dataAdapter : AnswerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCaseBinding.bind(view)

        dataAdapter = AnswerAdapter(this)

        binding.apply {
            binding.apply {
                rvList.apply {
                    adapter = dataAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                    addItemDecoration(
                        DividerItemDecoration(
                            this.context,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
            }
        }

        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.event.collect { event ->
                when(event) {
                    is CaseViewModel.UiEvents.FetchNextCase ->{

                    }
                    is CaseViewModel.UiEvents.LoadCaseItem -> {
                        // display Case Details
                        val data = event.caseItem
                        binding.apply {
                            text.text = data.text
                            Picasso.with(requireContext()).load(data.image).into(image)
                        }
                        loadAnswers(data.answers)

                    }
                    is CaseViewModel.UiEvents.Loading -> {
                        showHideLoading(event.isLoading)
                    }
                }.exhaustive
            }
        }
    }

    private fun loadAnswers(list: List<Answer>) {
        dataAdapter.submitList(list)
    }

    private fun showHideLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onAnswerClicked(item: Answer) {
        //
    }
}