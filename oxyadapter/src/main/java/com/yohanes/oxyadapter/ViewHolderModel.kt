package com.yohanes.oxyadapter


abstract class ViewHolderModel<out T : Any> {
    abstract val data: T
    abstract val layoutResId: Int
}

abstract class BindViewHolderModel<out T : Any> : ViewHolderModel<T>()

abstract class LoadingViewHolderModel<out T : Any> : ViewHolderModel<T>() {
    var loadingResId: Int = R.layout.default_loading_layout
    var isLoading = true
}
