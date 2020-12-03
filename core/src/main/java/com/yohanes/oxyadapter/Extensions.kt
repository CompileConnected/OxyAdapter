package com.yohanes.oxyadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.*

fun RecyclerView.gridVertical(
    spanCount: Int = GridLayoutManager.DEFAULT_SPAN_COUNT,
    reverse: Boolean = false
) {
    this.layoutManager =
        GridLayoutManager(this.context, spanCount, GridLayoutManager.VERTICAL, reverse)
}

fun RecyclerView.gridHorizontal(
    spanCount: Int = GridLayoutManager.DEFAULT_SPAN_COUNT,
    reverse: Boolean = false
) {
    this.layoutManager =
        GridLayoutManager(this.context, spanCount, GridLayoutManager.HORIZONTAL, reverse)
}

fun RecyclerView.vertical(reverse: Boolean = false) {
    this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, reverse)
}

fun RecyclerView.horizontal(reverse: Boolean = false) {
    this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, reverse)
}

fun RecyclerView.linearSnap() {
    if (this.onFlingListener == null) {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(this)
    }

}

fun RecyclerView.pagerSnap() {
    if (this.onFlingListener == null) {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this)
    }
}

fun ViewGroup.inflate(@LayoutRes resource: Int): View =
    LayoutInflater.from(this.context).inflate(resource, this, false)