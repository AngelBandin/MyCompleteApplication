package com.example.mycompleteaplication.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mycompleteaplication.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private val settingsViewModel: SettingsViewModel by viewModels<SettingsViewModel>()
    private var _binding : FragmentSettingsBinding? = null
    private var firstime = true
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
    }

    private fun initUI() {
        lifecycleScope.launch {
            if (firstime) {
                val dark = settingsViewModel.getDarkThemeValue()
                requireActivity().runOnUiThread {
                    binding.switchDark.isChecked = dark
                }
                firstime = false
            }
        }
    }



    private fun initListeners() {
        binding.switchDark.setOnCheckedChangeListener{ _, value ->
            if (value) enableDarkMode()
            else disableDarkMode()
            CoroutineScope(Dispatchers.IO).launch { settingsViewModel.putDarkThemeValue(binding.switchDark.isChecked) }

        }

    }
    private fun enableDarkMode() {
        val activity = requireActivity() as AppCompatActivity
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        activity.delegate.applyDayNight()
    }
    private fun disableDarkMode() {
        val activity = requireActivity() as AppCompatActivity
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        activity.delegate.applyDayNight()
    }

}