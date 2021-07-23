package com.yohanes.oxyadapter.core

import android.view.View
import com.yohanes.oxyadapter.core.BaseViewHolderModel.Companion.MERGE_BINDING_PRIORITY_EXTERNAL

abstract class ViewHolderModel<VH : OxyViewHolder> : BaseViewHolderModel<VH> {

    override var externalBinder: ExternalBindViewHolder<VH>? = null

    override var bindingStrategy = MERGE_BINDING_PRIORITY_EXTERNAL

    override fun createViewHolder(itemView: View): VH {
        return OxyViewHolder(itemView) as VH
    }

    override fun onInternalBind(holder: VH) {
        /*no-op*/
    }

    fun bind(b: (holder: VH) -> Unit) {
        val s: ExternalBindViewHolder<VH> = ExternalBindViewHolder { holder -> b.invoke(holder) }
        this.externalBinder = s
    }
}
