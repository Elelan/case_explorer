package com.ezio.caseexplorer.ui.feature_cases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ezio.caseexplorer.R
import com.ezio.caseexplorer.databinding.FragmentCaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaseFragment: Fragment(R.layout.fragment_case) {

    lateinit var binding: FragmentCaseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCaseBinding.bind(view)

        binding.apply {

        }
    }
}