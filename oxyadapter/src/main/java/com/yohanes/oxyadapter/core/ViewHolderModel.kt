package com.yohanes.oxyadapter.core

import android.view.View

abstract class ViewHolderModel<VH : OxyViewHolder> {

    abstract val layoutId: Int

    var externalBinder: ExternalBindViewHolder<VH>? = null
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

    open fun onInternalBind(holder: VH) {}

    fun bind(b: (holder: VH) -> Unit) {
        val s: ExternalBindViewHolder<VH> = ExternalBindViewHolder { holder -> b.invoke(holder) }
        this.externalBinder = s
    }
}
