package com.example.taskdemo

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class CommonAdapter<T:ViewDataBinding>(private val bindingInterface: RecyclerViewInterface<T>) :
    RecyclerView.Adapter<CommonAdapter<T>.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        return viewHolder(bindingInterface.getViewBinding(parent,viewType))
    }

    override fun getItemCount(): Int {
        return bindingInterface.getListCount()
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        bindingInterface.bindView(holder.binding,position)

    }

    inner class viewHolder(val binding: T) : RecyclerView.ViewHolder(binding.root)

    interface RecyclerViewInterface<T:ViewDataBinding> {

        fun getViewBinding(viewGroup: ViewGroup,viewType:Int): T
        fun bindView(viewBind: T, position:Int)
        fun getListCount():Int
    }


}