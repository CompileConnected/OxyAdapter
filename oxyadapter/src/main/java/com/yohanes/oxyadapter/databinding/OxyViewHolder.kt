package com.yohanes.oxyadapter.databinding

import androidx.viewbinding.ViewBinding

class OxyViewHolder<VH : ViewBinding>(val binding: VH) :
    com.yohanes.oxyadapter.core.OxyViewHolder(binding.root)