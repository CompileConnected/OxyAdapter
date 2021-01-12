package com.yohanes.oxyadapter.core

interface ExternalBindViewHolder<VH : OxyViewHolder> {
    fun onBind(holder: VH)
}