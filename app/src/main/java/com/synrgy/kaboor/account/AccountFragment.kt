package com.synrgy.kaboor.account

import android.view.LayoutInflater
import android.view.ViewGroup
import com.synrgy.common.presentation.KaboorFragment
import com.synrgy.kaboor.databinding.FragmentAccountBinding


class AccountFragment : KaboorFragment<FragmentAccountBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater, container, attachRoot)

    // TODO: For UI
    override fun initUI() {}

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {}

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}

}