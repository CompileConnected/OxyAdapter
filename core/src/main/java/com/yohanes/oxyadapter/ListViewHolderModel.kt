package com.yohanes.oxyadapter

abstract class ListViewHolderModel<out T : ViewHolderModel<Any>> : ViewHolderModel<List<T>>() {
    override val layoutResId = R.layout.recycler_view_layout
}

abstract class LoadingListViewHolderModel<out T : ViewHolderModel<Any>> :
    LoadingViewHolderModel<List<T>>()