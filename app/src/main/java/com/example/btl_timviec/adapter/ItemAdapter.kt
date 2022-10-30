package com.example.btl_timviec.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_timviec.Utils
import com.example.btl_timviec.callback.CallBack
import com.example.btl_timviec.databinding.ItemCardBinding
import com.example.btl_timviec.model.job.Job

class ItemAdapter(private val list: List<Job>, private val callback: CallBack) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCardBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Job){
            Utils.setAvt(binding.imgAvt,item.investor.avatar)
//            binding.tvNamePosition.text= item.work
//            binding.tvName.text= item.company_name
//            binding.tvWage.text= item.salary
//            binding.tvLocation.text= item.address
            Utils.setText(binding.tvNamePosition,item.work)
            Utils.setText(binding.tvName,item.company_name)
            Utils.setText(binding.tvWage,item.salary)
            Utils.setText(binding.tvLocation,item.address)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(list[position])

        holder.binding.csItemJob.setOnClickListener {
            callback.onClick(list[position])
        }

        holder.binding.csItemJob.setOnLongClickListener {
            callback.onLongClick(list[position])

            return@setOnLongClickListener true

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}