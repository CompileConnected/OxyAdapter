package com.yohanes.oxyadapter

typealias OxyAdapter = SingleOxyAdapter<*>

fun oxyadapter(init: SingleOxyAdapter.Build.() -> Unit) =
    SingleOxyAdapter.Build(init).build()