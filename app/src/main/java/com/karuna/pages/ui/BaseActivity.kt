package com.karuna.pages.ui

import androidx.appcompat.app.AppCompatActivity
import com.karuna.pages.utils.gone
import com.karuna.pages.utils.visible
import kotlinx.android.synthetic.main.activity_login.*

open class BaseActivity: AppCompatActivity() {

    fun showLoadingIndicator(show: Boolean) {
        runOnUiThread {
            if (show) loading.visible()
            else loading.gone()
        }
    }
}