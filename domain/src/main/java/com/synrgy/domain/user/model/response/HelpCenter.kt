package com.synrgy.domain.user.model.response

import androidx.annotation.ArrayRes
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 1/28/2024.
 * Github github.com/wahidabd.
 */


data class HelpCenter(
    val title: String,
    val content: String,
    @ArrayRes val contentList: Int = 0,
    val contentBottom: String? = emptyString(),
)
