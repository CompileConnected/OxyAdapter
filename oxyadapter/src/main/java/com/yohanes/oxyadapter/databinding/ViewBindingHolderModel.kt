package com.yohanes.oxyadapter.databinding

import android.view.View
import androidx.viewbinding.ViewBinding
import com.yohanes.oxyadapter.core.ViewHolderModel

@JvmName("bindHolderFactory2")
inline fun <VB : ViewBinding, reified VHM : ViewHolderModel<OxyViewHolder<VB>>> bindHolderFactory(
    init: ViewHolderModel.Builder<OxyViewHolder<VB>, VHM>.() -> Unit
) = ViewHolderModel.Builder<OxyViewHolder<VB>, VHM>().init()

@JvmName("bindHolderFactory1")
inline fun <reified VHM : ViewHolderModel<OxyViewHolder<*>>> bindHolderFactory(
    init: ViewHolderModel.Builder<OxyViewHolder<*>, VHM>.() -> Unit
) = ViewHolderModel.Builder<OxyViewHolder<*>, VHM>().init()

abstract class ViewBindingHolderModel<T : ViewBinding> : ViewHolderModel<OxyViewHolder<T>>() {

    protected abstract fun initViewBinding(itemView: View): T

    override fun createViewHolder(itemView: View): OxyViewHolder<T> {
        val s = initViewBinding(itemView)
        return OxyViewHolder(s)
    }
}