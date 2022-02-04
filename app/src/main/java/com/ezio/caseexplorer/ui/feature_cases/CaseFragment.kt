package com.ezio.caseexplorer.ui.feature_cases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezio.caseexplorer.R
import com.ezio.caseexplorer.core.domain.models.Answer
import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.utils.exhaustive
import com.ezio.caseexplorer.databinding.FragmentCaseBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_case.*
import kotlinx.android.synthetic.main.fragment_scenario_list.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CaseFragment: Fragment(R.layout.fragment_case), AnswerAdapter.ItemClickListener {

    var nextCaseId = 0
    lateinit var binding: FragmentCaseBinding
    val viewModel: CaseViewModel by viewModels()
    lateinit var dataAdapter : AnswerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCaseBinding.bind(view)
        setHasOptionsMenu(true)
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

                btnNext.setOnClickListener {
                    if(nextCaseId != 0) {
                        viewModel.loadNextCase(nextCaseId)
                    } else {
                        findNavController().navigateUp()
                    }
                }
            }
        }

        dataAdapter.submitList(listOf())
        loadAnswers(listOf(
            Answer(0, "")
        ))
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
                        displayData(data)

                    }
                    is CaseViewModel.UiEvents.Loading -> {
                        showHideLoading(event.isLoading)
                    }
                }.exhaustive
            }
        }
    }

    private fun displayData(data: CaseItem) {
        binding.apply {
            noDataView.visibility = View.GONE
            dataView.visibility = View.VISIBLE
            text.text = data.text
            print(data.image)
            Picasso.get().load(data.image)
                .into(image)
        }
        loadAnswers(data.answers)
    }

    private fun loadAnswers(list: List<Answer>) {
        dataAdapter.submitList(list)
    }

    private fun showHideLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                noDataView.visibility = View.VISIBLE
                dataView.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onAnswerClicked(item: Answer) {
        this.nextCaseId = item.caseid
    }
}