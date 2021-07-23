package com.yohanes.oxyadapter.core

fun interface ExternalBindViewHolder<VH : OxyViewHolder> {
    fun onBind(holder: VH)
}