package com.ezio.caseexplorer.ui.feature_scenarios;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ezio.caseexplorer.R
import com.ezio.caseexplorer.databinding.FragmentScenarioListBinding
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
class ScenarioList: Fragment(R.layout.fragment_scenario_list) {

    lateinit var binding: FragmentScenarioListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScenarioListBinding.bind(view)

        binding.apply {

        }
    }
}
