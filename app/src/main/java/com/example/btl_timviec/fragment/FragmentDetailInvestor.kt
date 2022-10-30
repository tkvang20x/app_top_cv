package com.example.btl_timviec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.btl_timviec.databinding.FragmentDetailInvestorBinding
import com.example.btl_timviec.model.job.Job

class FragmentDetailInvestor: Fragment() {
    private lateinit var binding: FragmentDetailInvestorBinding
    private lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInvestorBinding.inflate(layoutInflater, container, false)

        job = arguments?.getSerializable("job") as Job
        binding.job = job

        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}