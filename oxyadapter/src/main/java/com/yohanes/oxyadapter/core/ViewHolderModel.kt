package com.yohanes.oxyadapter.core

import android.view.View

abstract class ViewHolderModel<VH : OxyViewHolder> {

    abstract val layoutId: Int

    var externalBinder: ExternalBindViewHolder<VH, ViewHolderModel<VH>>? = null

    open fun createViewHolder(itemView: View): VH {
        return OxyViewHolder(itemView) as VH
    }

    fun bind(b: (holder: VH, viewHolderModel: ViewHolderModel<VH>) -> Unit) {
        val s: ExternalBindViewHolder<VH, ViewHolderModel<VH>> =
            object : ExternalBindViewHolder<VH, ViewHolderModel<VH>> {
                override fun onBind(holder: VH, viewHolderModel: ViewHolderModel<VH>) {
                    b.invoke(holder, viewHolderModel)
                }
            }
        this.externalBinder = s
    }
}
