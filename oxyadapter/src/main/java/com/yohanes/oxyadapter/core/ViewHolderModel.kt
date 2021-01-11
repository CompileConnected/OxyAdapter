package com.yohanes.oxyadapter.core

import android.view.View

abstract class ViewHolderModel<VH : OxyViewHolder> {

    abstract val layoutId: Int

    var externalBinder: ExternalBindViewHolder<VH, ViewHolderModel<VH>>? = null
    var bindingStrategy: Int = MERGE_BINDING_PRIORITY_EXTERNAL

    companion object {
        const val USE_DEFAULT_BINDING = 0
        const val USE_EXTERNAL_BINDING_ONLY = 1
        const val MERGE_BINDING = 2
        const val MERGE_BINDING_PRIORITY_EXTERNAL = 3
    }

    open fun createViewHolder(itemView: View): VH {
        return OxyViewHolder(itemView) as VH
    }

    open fun onBind(holder: VH, viewHolderModel: ViewHolderModel<VH>) {}

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
