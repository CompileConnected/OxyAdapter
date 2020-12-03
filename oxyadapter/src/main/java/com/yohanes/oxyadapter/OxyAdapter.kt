package com.yohanes.oxyadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class OxyAdapter private constructor(
    private val data: List<ViewHolderModel<Any>>,
    private val _onBindViewHolder: OnBindViewHolder?
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    @Suppress("UNCHECKED_CAST")
    class Build constructor() {
        constructor(init: Build.() -> Unit) : this() {
            init()
        }

        private var dataHolder: List<ViewHolderModel<Any>> = emptyList()
        private var register: OnBindViewHolder? = null
        private var pool: RecyclerView.RecycledViewPool? = null


        fun data(init: () -> List<ViewHolderModel<Any>>) = apply {
            dataHolder = init()
        }

        @JvmName("register1")
        fun <T : ViewHolderModel<Any>> register(init: (view: RecyclerViewHolder, data: T) -> Unit) =
            apply {
                this.register = object : OnBindViewHolder {
                    override fun execute(view: RecyclerViewHolder, data: ViewHolderModel<Any>) {
                        init(view, data as T)
                    }
                }
            }

        fun register(init: (view: RecyclerViewHolder, data: ViewHolderModel<Any>) -> Unit) =
            apply {
                this.register = object : OnBindViewHolder {
                    override fun execute(view: RecyclerViewHolder, data: ViewHolderModel<Any>) {
                        init(view, data)
                    }
                }
            }

        fun getPool(): RecyclerView.RecycledViewPool {
            if (pool == null) {
                pool = RecyclerView.RecycledViewPool()
            }
            return pool!!
        }

        fun build() = OxyAdapter(dataHolder, register)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            0 -> throw Exception("OxyAdapter onCreateViewHolder, viewType of $viewType not found")
            else -> RecyclerViewHolder(parent.inflate(viewType))
        }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
//        Log.d(
//            "OxyAdapter",
//            "onBindViewHolder ${holder.itemViewType} position: ${holder.adapterPosition} ${holder.isRecyclable}"
//        )
        _onBindViewHolder?.execute(holder, data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        val x = data[position]
        return when {
            x is LoadingViewHolderModel<Any> && x.isLoading -> x.loadingResId
            else -> x.layoutResId
        }
    }


    interface OnBindViewHolder {
        fun execute(view: RecyclerViewHolder, data: ViewHolderModel<Any>)
    }
}
