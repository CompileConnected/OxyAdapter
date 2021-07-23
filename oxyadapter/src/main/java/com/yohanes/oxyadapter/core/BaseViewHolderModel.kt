package com.yohanes.oxyadapter.core

import android.view.View

interface BaseViewHolderModel<VH : OxyViewHolder> {
    companion object {
        const val USE_DEFAULT_BINDING = 0
        const val USE_EXTERNAL_BINDING_ONLY = 1
        const val MERGE_BINDING = 2
        const val MERGE_BINDING_PRIORITY_EXTERNAL = 3
    }

    fun onInternalBind(holder: VH)
    fun createViewHolder(itemView: View): VH

    val layoutId: Int

    var externalBinder: ExternalBindViewHolder<VH>?

    var bindingStrategy: Int
}