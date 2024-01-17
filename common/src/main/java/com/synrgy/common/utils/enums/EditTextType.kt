package com.synrgy.common.utils.enums

import android.text.InputType


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


enum class EditTextType(val type: Int) {
    TEXT(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL),
    NUMBER(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL),
    PHONE(InputType.TYPE_CLASS_PHONE),
    EMAIL(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS),
    PASSWORD(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD),
    MULTILINE_TEXT(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE),
}