package com.synrgy.kaboor.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.synrgy.domain.home.model.Product
import com.synrgy.common.R
import com.synrgy.common.utils.enums.ProductAdapterType
import com.synrgy.kaboor.databinding.ItemCardProductBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/1/2024.
 * Github github.com/wahidabd.
 */


class CardProductAdapter(
    private val context: Context,
    private val type: ProductAdapterType = ProductAdapterType.NORMAL,
    private val onClick: (Product) -> Unit
) : BaseAsyncRecyclerAdapter<Product, CardProductAdapter.CardProductViewHolder>() {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding =
        ItemCardProductBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardProductAdapter.CardProductViewHolder =
        CardProductViewHolder(getViewBinding(parent, viewType))

    override fun getItemCount(): Int =
        if (type == ProductAdapterType.HOME && super.getItemCount() >= 3) 3 else super.getItemCount()

    inner class CardProductViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Product>(binding) {
        override fun bind(data: Product) = with(binding as ItemCardProductBinding) {
            tvTitle.text = data.title
            tvPrice.text = data.price.toString()
            tvRating.text = context.getString(R.string.format_rating, data.rating)
            tvType.text = data.type
            ratingBar.rating = data.rating
            imgPicture.setImageResource(data.image ?: R.drawable.sample_img_car2)

            root.onClick {
                onClick.invoke(data)
            }
        }
    }
}