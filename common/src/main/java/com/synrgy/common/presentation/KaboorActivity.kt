package com.synrgy.common.presentation

import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.activity.BaseActivity


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


abstract class KaboorActivity<VB: ViewBinding> : BaseActivity<VB>(){

    override fun initIntent() {}
    override fun initProcess() {}
    override fun initObservers() {}
}