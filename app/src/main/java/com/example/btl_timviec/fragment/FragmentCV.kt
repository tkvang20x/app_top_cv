package com.example.btl_timviec.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.btl_timviec.R
import com.example.btl_timviec.databinding.FragmentCvBinding
import com.example.btl_timviec.viewmodel.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentCV : Fragment() {
    private lateinit var binding:  FragmentCvBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCvBinding.inflate(inflater, container, false)
        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bnv_view)
        view.visibility = View.VISIBLE

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.getDataUser()
        userViewModel.data1.observe(viewLifecycleOwner, {
            if (it !== null) {
                binding.user = it.data
            }
        })


        return binding.root
    }
}