package com.yohanes.oxyadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class OxyAdapter private constructor(
    private val data: List<ViewHolderModel<Any>>,
    private val _onBindViewHolder: OnBindViewHolder?
) : RecyclerView.Adapter<RecyclerViewHolder>() {
    companion object {
        class Build constructor() {
            constructor(init: Build.() -> Unit) : this() {
                init()
            }

            private var dataHolder: List<ViewHolderModel<Any>> = emptyList()
            private var register: OnBindViewHolder? = null
            private var pool: RecyclerView.RecycledViewPool? = null


            fun data(init: () -> List<ViewHolderModel<Any>>): Build {
                dataHolder = init()
                return this
            }

            @JvmName("register1")
            fun <T : ViewHolderModel<Any>> register(init: (view: RecyclerViewHolder, data: T) -> Unit): Build {
                this.register = object : OnBindViewHolder {
                    override fun execute(view: RecyclerViewHolder, data: ViewHolderModel<Any>) {
                        init(view, data as T)
                    }
                }
                return this
            }

            fun register(init: (view: RecyclerViewHolder, data: ViewHolderModel<Any>) -> Unit): Build {
                this.register = object : OnBindViewHolder {
                    override fun execute(view: RecyclerViewHolder, data: ViewHolderModel<Any>) =
                        init(view, data)
                }
                return this
            }

            fun getPool(): RecyclerView.RecycledViewPool {
                if (pool == null) pool = RecyclerView.RecycledViewPool()
                return pool!!
            }

            fun build() = OxyAdapter(dataHolder, register)
        }

        @JvmStatic
        fun oxyadapter(init: Build.() -> Unit) = Build(init).build()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            0 -> throw Exception("OxyAdapter onCreateViewHolder, viewType of $viewType not found")
            else -> RecyclerViewHolder(
                LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
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


    private interface OnBindViewHolder {
        fun execute(view: RecyclerViewHolder, data: ViewHolderModel<Any>)
    }
}
