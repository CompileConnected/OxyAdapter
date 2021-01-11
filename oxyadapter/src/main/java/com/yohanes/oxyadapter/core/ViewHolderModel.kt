package com.yohanes.oxyadapter.core

import android.view.View

@JvmName("holderFactory2")
inline fun <VH : OxyViewHolder, reified VHM : ViewHolderModel<VH>> holderFactory(
    init: ViewHolderModel.Builder<VH, VHM>.() -> Unit
) = ViewHolderModel.Builder<VH, VHM>().init()


@JvmName("holderFactory1")
inline fun <reified VHM : ViewHolderModel<OxyViewHolder>> holderFactory(
    init: ViewHolderModel.Builder<OxyViewHolder, VHM>.() -> Unit
) = ViewHolderModel.Builder<OxyViewHolder, VHM>().init()


abstract class ViewHolderModel<VH : OxyViewHolder> {

    abstract val layoutId: Int

    var externalBinder: ExternalBindViewHolder<VH, ViewHolderModel<VH>>? = null

    open fun createViewHolder(itemView: View): VH {
        return OxyViewHolder(itemView) as VH
    }

    class Builder<VH : OxyViewHolder, VHM : ViewHolderModel<VH>> {

        private var externalBinder: ExternalBindViewHolder<VH, ViewHolderModel<VH>>? = null
        private var layoutId: Int? = null

        fun bind(b: (holder: VH, viewHolderModel: VHM) -> Unit) {
            val s: ExternalBindViewHolder<VH, ViewHolderModel<VH>> =
                object : ExternalBindViewHolder<VH, ViewHolderModel<VH>> {
                    override fun onBind(holder: VH, viewHolderModel: ViewHolderModel<VH>) {
                        b.invoke(holder, viewHolderModel as VHM)
                    }
                }
            this.externalBinder = s
        }

        fun setLayoutId(layoutId: Int) {
            this.layoutId = layoutId
        }

        fun build() = object : ViewHolderModel<VH>() {
            override val layoutId: Int
                get() = this@Builder.layoutId!!

        }.apply {
            externalBinder = this@Builder.externalBinder
        }
    }

    fun setBind(b: (holder: VH, viewHolderModel: ViewHolderModel<VH>) -> Unit) {
        val s: ExternalBindViewHolder<VH, ViewHolderModel<VH>> =
            object : ExternalBindViewHolder<VH, ViewHolderModel<VH>> {
                override fun onBind(holder: VH, viewHolderModel: ViewHolderModel<VH>) {
                    b.invoke(holder, viewHolderModel)
                }
            }
        this.externalBinder = s
    }
}
