package com.yohanes.oxyadapter.core

import android.view.View

abstract class ViewHolderModel<VH : OxyViewHolder> {

    abstract val layoutId: Int

    open fun createViewHolder(itemView: View): VH {
        return OxyViewHolder(itemView) as VH
    }

    abstract fun onBind(holder: VH, position: Int)
}