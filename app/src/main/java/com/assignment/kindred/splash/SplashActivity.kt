package com.assignment.kindred.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.assignment.kindred.R
import com.assignment.kindred.base.BaseActivity
import com.assignment.kindred.databinding.ActivitySplashBinding
import com.assignment.kindred.home.view.HomeActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callNextScreenAfterDelay(600)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    /**
     * @delay: delay in milliseconds
     */
    private fun callNextScreenAfterDelay(delay: Long) {
        Handler().postDelayed(Runnable {
            val homeIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, delay)
    }
}
