package com.example.btl_timviec.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.btl_timviec.Const
import com.example.btl_timviec.MainActivity
import com.example.btl_timviec.R
import com.example.btl_timviec.databinding.FragmentLoginBinding
import com.example.btl_timviec.model.login.Login
import com.example.btl_timviec.sharepreference.MySharedPreference
import com.example.btl_timviec.viewmodel.LoginViewModel

class FragmentLogin : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var model: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        model = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.tvRegister.setOnClickListener {
            val action = FragmentLoginDirections.actionFragmentLoginToFragmentRegister()
           findNavController().navigate(action)
        }


        binding.btnLogin.setOnClickListener {
            val login = Login(binding.txtUserName.text.toString(), binding.txtPassword.text.toString())
            if (binding.txtUserName.text.toString() != "" && binding.txtPassword.text.toString() != "") {

                model.postLogin(login)
                model.dataLogin.observe(viewLifecycleOwner, {
                    if (it?.message == "AUTH.LOGIN.FAILED") {
                        Toast.makeText(this.context,"Nhập sai thông tin", Toast.LENGTH_LONG).show()
                    } else {
                        MySharedPreference(requireActivity()).putToken(it?.data!!.access_token)

                        Const.TOKEN = MySharedPreference(requireActivity()).getToken().toString()

                        val intent = Intent()
                        intent.setClass(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                    }

                })
            } else {
                Toast.makeText(this.context, "Điền thiếu thông tin", Toast.LENGTH_LONG)
                    .show()
                Log.d("zz","xx${login}")
            }
        }
        return binding.root
    }
}