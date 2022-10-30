package com.example.btl_timviec.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btl_timviec.adapter.ItemAdapter
import com.example.btl_timviec.adapter.ItemHistoryAdapter
import com.example.btl_timviec.callback.CallBack
import com.example.btl_timviec.databinding.FragmentHistoryJobBinding
import com.example.btl_timviec.model.history.History
import com.example.btl_timviec.model.history.JobHistory
import com.example.btl_timviec.model.job.Job
import com.example.btl_timviec.viewmodel.HistoryViewModel

class FragmentHistory: Fragment(), CallBack {

    private lateinit var binding: FragmentHistoryJobBinding
    private val listJobSaved = arrayListOf<History>()
    private val listJobApplied = arrayListOf<History>()
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapterSaved: ItemHistoryAdapter
    private lateinit var adapterApplied: ItemHistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryJobBinding.inflate(layoutInflater, container, false)


        historyViewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel:: class.java)
        historyViewModel.getHistory(0)
        historyViewModel.dataHistory.observe(viewLifecycleOwner,{
            if(it != null && it.data.history.isNotEmpty()){
                listJobApplied.clear()
                listJobSaved.clear()
                for(item in it.data.history){
                    if (item.type == "saved"){
                        listJobSaved.add(item)
                    }else{
                        listJobApplied.add(item)
                    }
                }
            }
        })



        adapterSaved = ItemHistoryAdapter(listJobSaved, this)
        binding.rvListSaved.adapter = adapterSaved
        binding.rvListSaved.layoutManager =
            LinearLayoutManager(FragmentHome().context, LinearLayoutManager.HORIZONTAL, false)

        adapterApplied = ItemHistoryAdapter(listJobApplied, this)
        binding.rvListApplied.adapter = adapterApplied
        binding.rvListApplied.layoutManager =
            LinearLayoutManager(FragmentHome().context, LinearLayoutManager.HORIZONTAL, false)
        return binding.root
    }

    override fun onClick(job: Job) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(job: Job) {
        TODO("Not yet implemented")
    }
}