package com.synrgy.kaboor.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.kaboor.databinding.FragmentHomeBinding

class HomeFragment : KaboorFragment<FragmentHomeBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, attachRoot)

    // TODO: For UI
    override fun initUI() {}

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {}

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}

}