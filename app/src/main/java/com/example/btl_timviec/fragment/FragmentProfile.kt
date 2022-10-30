package com.example.btl_timviec.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.btl_timviec.Const
import com.example.btl_timviec.R
import com.example.btl_timviec.databinding.FragmentProfileBinding
import com.example.btl_timviec.viewmodel.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import gun0912.tedbottompicker.TedBottomPicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException

class FragmentProfile: Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var userViewModel: UserViewModel
    var id = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bnv_view)
        view.visibility = View.VISIBLE

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.getDataUser()
        userViewModel.data1.observe(viewLifecycleOwner, {
            if (it !== null) {
                binding.user = it.data
                id = it.data.id.toString()
            }
        })


        binding.imgChangeAvt.setOnClickListener {
            checkPermission()
        }

        binding.tvUpdate.setOnClickListener {
            val action = FragmentProfileDirections.actionFragmentProfileToFragmentUpdateUser()
            findNavController().navigate(action)
        }

        return binding.root
    }



    private fun checkPermission() {
        val permissionlistener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                TedBottomPicker.with(activity).show { uri ->
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Thay Avatar")
                    builder.setMessage("Bạn Có Muốn Xác Nhận Thay Avatar?")
                    builder.setNegativeButton(
                        "No"
                    ) { dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss() }
                    builder.setPositiveButton(
                        "Yes"
                    ) { dialogInterface: DialogInterface?, i: Int ->
                        var bitmap: Bitmap? = null
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(
                                activity!!.contentResolver,
                                uri
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        binding.imgAvtUser.setImageBitmap(bitmap)
                        val multipartBody: MultipartBody.Part = getPath(uri)
                        userViewModel.uploadAvatar(id, multipartBody)
                        val builderSuccess =
                            AlertDialog.Builder(requireContext())
                        builderSuccess.setTitle("Thay Avatar")
                        builderSuccess.setMessage("Thay Avatar Thành Công")
                        builderSuccess.show()
                    }
                    builder.show()
                }
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(context, "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .check()
    }
    fun getPath(uri: Uri): MultipartBody.Part {
        val file = File(uri.path)
        val reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        return MultipartBody.Part.createFormData("avatar", file.name, reqFile)
    }
}

