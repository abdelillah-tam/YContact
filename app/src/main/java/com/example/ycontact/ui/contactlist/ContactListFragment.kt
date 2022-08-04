package com.example.ycontact.ui.contactlist

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ycontact.R
import com.example.ycontact.databinding.FragmentContactListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactListFragment : Fragment(R.layout.fragment_contact_list) {

    private lateinit var binding : FragmentContactListBinding
    private lateinit var recAdapter : ContactRecviewAdapter

    private val contactListViewModel : ContactListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactListBinding.bind(view)

        recAdapter = ContactRecviewAdapter()
        binding.contactListRecyclerview.apply {
            adapter = recAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }else{
            prepareContactList()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    prepareContactList()
                }
            }
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    fun prepareContactList(){

        contactListViewModel.getAllContacts(requireActivity().contentResolver)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                contactListViewModel.state.collect{
                    recAdapter.setContacts(it.list)
                }
            }
        }
    }
}