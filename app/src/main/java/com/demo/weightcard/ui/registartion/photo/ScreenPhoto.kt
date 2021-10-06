package com.demo.weightcard.ui.registartion.photo

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demo.weightcard.BuildConfig
import com.demo.weightcard.R
import com.demo.weightcard.databinding.ScreenPhotoBinding
import com.demo.weightcard.ui.registartion.RegistrationNavigator
import com.demo.weightcard.ui.registartion.birthday.ScreenBirthdayViewModel
import com.demo.weightcard.ui.registartion.registration.RegistrationViewModel
import com.demo.weightcard.ui.registartion.weight.ScreenWeightViewModel
import com.demo.weightcard.utils.registrationViewModelDelegate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File

class ScreenPhoto : Fragment(R.layout.screen_photo) {
    private val binding by viewBinding(ScreenPhotoBinding::bind)
    private val viewModel by registrationViewModelDelegate<ScreenPhotoViewModel>()
    private val birthdayViewModel by registrationViewModelDelegate<ScreenBirthdayViewModel>()
    private val weightViewModel by registrationViewModelDelegate<ScreenWeightViewModel>()
    private val registrationViewModel: RegistrationViewModel by sharedViewModel()
    private val registrationNavigator = RegistrationNavigator()

    private val takeImageResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                latestTmpUri?.let {
                    viewModel.face.value = "content://com.demo.weightcard.provider" + it.path
                }
            }
        }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                takeImage()
            }
        }
    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it?.let { viewModel.face.value = it.path }
        }

    private fun requestCamera() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) -> takeImage()
            else -> requestPermissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    private fun takeImage() {
        lifecycleScope.launchWhenStarted {
            getTmpFileUri().let { uri ->
                latestTmpUri = uri
                takeImageResult.launch(uri)
            }
        }
    }

    private var latestTmpUri: Uri? = null
    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")

    private fun getTmpFileUri(): Uri {
        val tmpFile =
            File.createTempFile("tmp_image_file", ".png", requireContext().cacheDir).apply {
                createNewFile()
                deleteOnExit()
            }

        return FileProvider.getUriForFile(
            requireContext(),
            "${BuildConfig.APPLICATION_ID}.provider",
            tmpFile
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        viewModel.face.observe(
            viewLifecycleOwner,
            {
                if (!it.isNullOrEmpty()) binding.photoImageView.setImageURI(Uri.parse(it))
            })
        bindListeners()
    }

    private fun bindListeners() {
        binding.gallery.setOnClickListener { selectImageFromGallery() }
        binding.camera.setOnClickListener { requestCamera() }
        binding.nextScreen.setOnClickListener {
            if (viewModel.isAllFieldsValid())
                registrationViewModel.saveProfileAndClose(
                    registrationNavigator,
                    parentFragment,
                    weightViewModel.weight.value ?: "",
                    birthdayViewModel.birthday.value ?: "",
                    weightViewModel.units.value ?: "",
                    viewModel.face.value ?: ""
                )
        }
        binding.prevScreen.setOnClickListener {
            registrationNavigator.moveToPreviousScreen(parentFragment)
        }
    }
}