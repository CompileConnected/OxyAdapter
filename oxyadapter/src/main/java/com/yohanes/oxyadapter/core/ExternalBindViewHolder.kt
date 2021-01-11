package com.yohanes.oxyadapter.core

interface ExternalBindViewHolder<VH : OxyViewHolder, VHM : ViewHolderModel<VH>> {
    fun onBind(holder: VH, viewHolderModel: VHM)
}