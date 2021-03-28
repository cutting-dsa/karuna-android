package com.karuna.pages.utils

//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.lifecycle.*
//
//
//inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
//    val vm = ViewModelProviders.of(this, factory)[T::class.java]
//    vm.body()
//    return vm
//}
//
//inline fun <reified T : ViewModel> FragmentActivity.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
//    val vm = ViewModelProviders.of(this, factory)[T::class.java]
//    vm.body()
//    return vm
//}
//
//fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
//    liveData