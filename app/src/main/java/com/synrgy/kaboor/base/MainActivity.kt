package com.synrgy.kaboor.base

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.PermissionExt
import com.synrgy.common.utils.ext.requestMultiplePermission
import com.synrgy.kaboor.R
import com.synrgy.kaboor.databinding.ActivityMainBinding
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible

class MainActivity : KaboorActivity<ActivityMainBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)


    override fun initIntent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) requestPermission()
    }

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

    override fun initAction() {}

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermission() {
        requestMultiplePermission(
            permissions = PermissionExt.notificationPermission13,
            requestCode = PermissionExt.NOTIFICATION_REQUEST_CODE
        )
    }
}