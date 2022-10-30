package com.example.btl_timviec.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.btl_timviec.R
import com.example.btl_timviec.databinding.FragmentUpdateProfileBinding
import com.example.btl_timviec.model.user.User
import com.example.btl_timviec.viewmodel.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentUpdateProfile : Fragment() {
    lateinit var binding: FragmentUpdateProfileBinding
    private lateinit var userViewModel: UserViewModel
    var id = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateProfileBinding.inflate(layoutInflater, container, false)

        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bnv_view)
        view.visibility = View.GONE

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.getDataUser()
        userViewModel.data1.observe(viewLifecycleOwner, {
            if (it !== null) {
                binding.user = it.data
                id = it.data.id.toString()
            }
        })

        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnUpdate.setOnClickListener {
            val userUpdate = User(
                null,
                null,
                null,
                binding.txtCareer.text.toString(),
                null,
                binding.txtEmail.text.toString(),
                binding.txtFullName.text.toString(),
                binding.txtGender.text.toString(),
                null,
                null,
                binding.txtPhone.text.toString(),
                null,
                null,
                binding.txtDescriptionUser.text.toString(),
                null,
                binding.txtAddress.text.toString(),
                2,
                "kinh nghiem v2",
                binding.txtSkill.text.toString()
            )

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Sửa Thông Tin")
            builder.setMessage("Bạn Có Muốn Xác Nhận Sửa?")
            builder.setNegativeButton(("No"), { dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss() })
            builder.setPositiveButton(("Yes"), { dialogInterface: DialogInterface, i:Int ->
                userViewModel.updateUser(id ,userUpdate)

                dialogInterface.dismiss()
                findNavController().navigate(R.id.action_fragmentUpdateUser_to_fragmentProfile)
                val builder2 = AlertDialog.Builder(requireContext())
                builder2.setMessage("Sửa thành công!!!")
                builder2.show()
            })
            builder.show()
        }

        return binding.root
    }
}