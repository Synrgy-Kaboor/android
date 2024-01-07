package com.synrgy.common.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutAddonsCardBinding
import com.synrgy.common.utils.enums.AddonsType
import com.synrgy.common.utils.ext.goneIf
import com.synrgy.common.utils.ext.visibleIf
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/6/2024.
 * Github github.com/wahidabd.
 */


class KaboorAddonsCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var binding: LayoutAddonsCardBinding

    private var label: String = emptyString()
    private var header: String = emptyString()
    private var icon: Int = 0
    private var addonLabel1 = emptyString()
    private var addonLabel2 = emptyString()
    private var description: String = emptyString()
    private var footer: String = emptyString()
    private var price: Float = 0F
    private var type: AddonsType = AddonsType.NORMAL
    var isSelectedItem: Boolean = false
        private set

    private var onLoadMoreClickListener: () -> Unit = {}
    private var onAddonClickList: () -> Unit = {}

    init {
        binding = LayoutAddonsCardBinding.inflate(LayoutInflater.from(context), this)
        setAttributes(attrs)
        setupView()
    }

    private fun setAttributes(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.KaboorAddons, 0, 0)
        label = attributes.getString(R.styleable.KaboorAddons_kaboorAddons_label).orEmpty()
        description =
            attributes.getString(R.styleable.KaboorAddons_kaboorAddons_description).orEmpty()
        footer = attributes.getString(R.styleable.KaboorAddons_kaboorAddons_footer).orEmpty()
        header = attributes.getString(R.styleable.KaboorAddons_kaboorAddons_header).orEmpty()
        icon = attributes.getResourceId(R.styleable.KaboorAddons_kaboorAddons_icon, 0)
        addonLabel1 =
            attributes.getString(R.styleable.KaboorAddons_kaboorAddons_assurance_label_1).orEmpty()
        addonLabel2 =
            attributes.getString(R.styleable.KaboorAddons_kaboorAddons_assurance_label_2).orEmpty()
        type = attributes.getInt(R.styleable.KaboorAddons_kaboorAddons_type, 0).let {
            AddonsType.entries[it]
        }
        price = attributes.getFloat(R.styleable.KaboorAddons_kaboorAddons_price, 0F)
        attributes.recycle()
    }

    private fun setupView() = with(binding) {
        normalContainer.goneIf { type != AddonsType.NORMAL }
        assuranceContainer.visibleIf { type == AddonsType.ASSURANCE }
        tvAssuranceLabel2.visibleIf { addonLabel2.isNotBlank() }

        tvLabel.text = label
        tvDescription.text = description
        tvFooter.text = if (type == AddonsType.NORMAL) footer else price.toString()
        tvHeader.text = header
        imgIcon.setImageResource(icon)
        tvAssuranceLabel1.text = addonLabel1
        tvAssuranceLabel2.text = addonLabel2

        imgAddon.onClick { onAddonClickList.invoke() }
        tvLoadMore.onClick { onLoadMoreClickListener.invoke() }
    }

    fun setAddonClickListener(listener: () -> Unit) {
        onAddonClickList = listener
        isSelectedItem = !isSelectedItem
        binding.imgAddon.isSelected = isSelectedItem
    }

    fun setOnLoadMoreClickListener(listener: () -> Unit) {
        onLoadMoreClickListener = listener
    }
}