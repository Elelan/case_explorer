package com.ezio.caseexplorer.ui.feature_scenarios;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezio.caseexplorer.R
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.utils.exhaustive
import com.ezio.caseexplorer.databinding.FragmentScenarioListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ScenarioList : Fragment(R.layout.fragment_scenario_list), ScenarioAdapter.ItemClickListener {

    lateinit var binding: FragmentScenarioListBinding
    lateinit var dataAdapter: ScenarioAdapter
    private val viewModel: ScenarioViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScenarioListBinding.bind(view)

        dataAdapter = ScenarioAdapter(this)

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

        initObservers()

        val list = listOf(
            ScenarioItem(1, "scenario one"),
            ScenarioItem(2, "scenario two"),
            ScenarioItem(3, "scenario three"),
            ScenarioItem(4, "scenario four")
        )

        dataAdapter.submitList(listOf())
        loadScenarioList(list)
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { event ->
                when (event) {
                    is UiStateEvents.LoadScenarioList -> {
                        loadScenarioList(event.data)
                    }
                    is UiStateEvents.Loading -> {
                        showHideLoading(event.isLoading)
                    }
                    is UiStateEvents.NavigateToCase -> {
                        // Navigate to Case Details Screen
                        val action = ScenarioListDirections.actionScenarioListToCaseFragment(event.caseId)
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }

    private fun showHideLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        }
    }

    private fun loadScenarioList(data: List<ScenarioItem>) {
        dataAdapter.submitList(data)
    }

    override fun onScenarioClicked(item: ScenarioItem) {
        viewModel.navigateToCaseDetail(item)
    }
}
