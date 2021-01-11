package com.yohanes.oxyadapter.databinding

import android.view.View
import androidx.viewbinding.ViewBinding
import com.yohanes.oxyadapter.core.ViewHolderModel

abstract class ViewBindingHolderModel<T : ViewBinding> : ViewHolderModel<OxyViewHolder<T>>() {

    protected abstract fun initViewBinding(itemView: View): T

    override fun createViewHolder(itemView: View): OxyViewHolder<T> {
        val s = initViewBinding(itemView)
        return OxyViewHolder(s)
    }
}