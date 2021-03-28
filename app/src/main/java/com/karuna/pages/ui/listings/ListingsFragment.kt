package com.karuna.pages.ui.listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.BuildConfig
import com.karuna.pages.R
import com.karuna.pages.utils.Resource

class ListingsFragment : Fragment(R.layout.fragment_listings) {

    private val viewModel: ListingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        print("****************Ananda*********")
        return inflater.inflate(R.layout.fragment_listings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        print("****************AnandaCreated*********")
        setupObservers()
    }

    private fun setupObservers() {
        print("*************** $viewModel")
//        viewModel.uiState.observe(viewLifecycleOwner, Observer {
//            when (it.status) {
//                Resource.Status.SUCCESS -> {
//                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
////                    binding.progressBar.visibility = View.GONE
//                    //  if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
//                }
//                Resource.Status.ERROR ->
//                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//
//                Resource.Status.LOADING ->
//                    Toast.makeText(requireContext(), "Loading!!!!!!", Toast.LENGTH_SHORT).show()
////                    binding.progressBar.visibility = View.VISIBLE
//            }
//        })
    }

    companion object {
        const val FRAGMENT_TAG: String =
            BuildConfig.APPLICATION_ID.toString() + ".ListingsFragment_TAG"
    }
}

/**
 * Get a [ViewModel] in an [ComponentActivity].
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModelBuilder(
    noinline viewModelInitializer: () -> VM
): Lazy<VM> {
    return ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            return@ViewModelLazy object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")// Casting T as ViewModel
                    return viewModelInitializer.invoke() as T
                }
            }
        }
    )
}
