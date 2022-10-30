package com.example.btl_timviec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btl_timviec.R
import com.example.btl_timviec.adapter.ItemV2Adapter
import com.example.btl_timviec.callback.CallBack
import com.example.btl_timviec.databinding.FragmentListJobBinding
import com.example.btl_timviec.model.SearchParam
import com.example.btl_timviec.model.job.Job
import com.example.btl_timviec.viewmodel.JobViewModel

class FragmentListJob : Fragment(), CallBack {

    private lateinit var binding: FragmentListJobBinding
    private lateinit var adapter: ItemV2Adapter
    private lateinit var jobViewModel: JobViewModel

    private val  list = arrayListOf<Job>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListJobBinding.inflate(layoutInflater, container, false)

        jobViewModel = ViewModelProvider(requireActivity()).get(JobViewModel:: class.java)
        jobViewModel.getListJob(SearchParam("",""),0)
        jobViewModel.dataJob.observe(viewLifecycleOwner,{
            if(it != null){
               list.clear()
                list.addAll(it.data.jobs)
            }
        })

        adapter = ItemV2Adapter(list, this)
        binding.rvListAllJob.adapter = adapter
        binding.rvListAllJob.layoutManager =
            LinearLayoutManager(FragmentHome().context, LinearLayoutManager.VERTICAL, false)


        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onClick(job: Job) {
        val bundle= Bundle()
        bundle.putSerializable("job",job)
        findNavController().navigate(R.id.action_fragmentListJob_to_fragmentDetailJob,bundle)
    }

    override fun onLongClick(job: Job) {
        TODO("Not yet implemented")
    }
}