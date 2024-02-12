package com.synrgy.common.utils.enums

import com.synrgy.common.R


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


enum class OrderType(
    val label: String,
    val background: Int,
    private val proof: Boolean,
    private val completed: Boolean
) {

    PROCESS("Sedang di Proses", R.drawable.bg_round_rectangle_warning, true, false),
    NOT_COMPLETED("Belum Selesai", R.drawable.bg_round_rectangle_danger, false, false),
    E_TICKET("E-Tiket Terbit", R.drawable.bg_round_rectangle_secondary, true, true),
    COMPLETED("Selesai",R.drawable.bg_round_rectangle_success, true, true);

    companion object {
        fun getBackground(
            checked: Pair<Boolean, Boolean>,
            isCompleted: Boolean = false
        ): OrderType {
            return if (isCompleted) COMPLETED
            else entries.first { type ->
                type.proof == checked.first && type.completed == checked.second
            }
        }
    }
}