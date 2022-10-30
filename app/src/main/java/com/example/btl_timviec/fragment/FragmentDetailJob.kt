package com.example.btl_timviec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.btl_timviec.R
import com.example.btl_timviec.databinding.FragmentDetailJobBinding
import com.example.btl_timviec.model.job.Job

class FragmentDetailJob: Fragment() {

    private lateinit var binding : FragmentDetailJobBinding
    private lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailJobBinding.inflate(inflater, container, false)
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        job = arguments?.getSerializable("job") as Job
        binding.job = job

        binding.tvName.setOnClickListener {
            val bundle= Bundle()
            bundle.putSerializable("job",job)
            findNavController().navigate(R.id.action_fragmentDetailJob_to_fragmentDetailInvestor,bundle)
        }



        return binding.root
    }
}