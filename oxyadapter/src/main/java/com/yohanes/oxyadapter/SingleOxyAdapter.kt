package com.yohanes.oxyadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yohanes.oxyadapter.core.OxyViewHolder
import com.yohanes.oxyadapter.core.ViewHolderModel
import com.yohanes.oxyadapter.core.ViewHolderModel.Companion.MERGE_BINDING
import com.yohanes.oxyadapter.core.ViewHolderModel.Companion.MERGE_BINDING_PRIORITY_EXTERNAL
import com.yohanes.oxyadapter.core.ViewHolderModel.Companion.USE_DEFAULT_BINDING
import com.yohanes.oxyadapter.core.ViewHolderModel.Companion.USE_EXTERNAL_BINDING_ONLY

class SingleOxyAdapter<VH : OxyViewHolder>(
    private val viewHolderModelList: ArrayList<ViewHolderModel<VH>>,
) : RecyclerView.Adapter<VH>() {

    class Build constructor() {
        private val h = arrayListOf<ViewHolderModel<*>>()

        constructor(init: Build.() -> Unit) : this() {
            init()
        }

        fun data(data: List<ViewHolderModel<*>>) {
            this.h.addAll(data)
        }

        fun build() = OxyAdapter(h)
    }

    private var lastViewType: ViewHolderModel<VH>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val feed = getItemViewTypeFor(viewType)
        val view = inflater.inflate(feed.layoutId, parent, false)
        return feed.createViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val vhm = viewHolderModelList[position]
        when (vhm.bindingStrategy) {
            MERGE_BINDING_PRIORITY_EXTERNAL -> {
                vhm.externalBinder?.onBind(holder)
                vhm.onInternalBind(holder)
            }
            MERGE_BINDING -> {
                vhm.onInternalBind(holder)
                vhm.externalBinder?.onBind(holder)
            }
            USE_EXTERNAL_BINDING_ONLY -> vhm.externalBinder?.onBind(holder)
            USE_DEFAULT_BINDING -> vhm.onInternalBind(holder)
        }
    }

    override fun getItemCount() = viewHolderModelList.size


    override fun getItemViewType(position: Int): Int {
        lastViewType = viewHolderModelList[position]
        return lastViewType!!.layoutId
    }

    private fun getItemViewTypeFor(viewType: Int): ViewHolderModel<VH> {
        var res: ViewHolderModel<VH>? = null

        if (lastViewType != null && lastViewType?.layoutId == viewType) {
            res = lastViewType
        } else {
            for (it in viewHolderModelList) {
                if (it.layoutId == viewType) {
                    res = it
                    break
                }
            }
        }
        return res ?: throw Error("Could Not find model for view type: $viewType")
    }
}