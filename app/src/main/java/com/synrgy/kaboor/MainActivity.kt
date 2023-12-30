package com.synrgy.kaboor

import android.content.Context
import android.content.Intent
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.databinding.ActivityMainBinding
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible

class MainActivity : KaboorActivity<ActivityMainBinding>() {

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)


    // TODO: For handle intent (Data, etc)
    override fun initIntent() {}

    // TODO: For UI
    override fun initUI() {
        with(binding) {
            val navHost =
                supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
            val navController = navHost.navController

            bottomNav.setOnItemSelectedListener { item ->
                if (item.itemId != bottomNav.selectedItemId) {
                    NavigationUI.onNavDestinationSelected(item, navController)
                }
                true
            }

            navController.addOnDestinationChangedListener { _, dest, _ ->
                when (dest.id) {
                    R.id.homeFragment,
                    R.id.orderFragment,
                    R.id.notificationFragment,
                    R.id.accountFragment -> bottomNav.visible()
                    else -> bottomNav.gone()
                }
            }
        }
    }

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {}

    // TODO: For Observer (LiveData, etc)
    override fun initObservers() {}
}