package com.yohanes.oxyadapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class RecyclerViewHolder(itemView: View, val binding: ViewDataBinding? = null) :
    RecyclerView.ViewHolder(binding?.root ?: itemView)
