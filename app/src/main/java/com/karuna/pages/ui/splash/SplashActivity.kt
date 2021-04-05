package com.karuna.pages.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.karuna.pages.R
import com.karuna.pages.ui.home.HomeActivity
import com.karuna.pages.ui.login.ui.login.LoginActivity
import com.karuna.pages.utils.PreferenceManager

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 1000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
//            var intent = Intent(applicationContext, LoginActivity::class.java)
//            if (PreferenceManager(this).getLoginStatus() != 0) {
//                intent = Intent(applicationContext, HomeActivity::class.java)
//            }
//            startActivity(intent)
//            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }
}