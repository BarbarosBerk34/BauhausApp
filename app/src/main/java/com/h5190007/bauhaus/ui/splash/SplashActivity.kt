package com.h5190007.bauhaus.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.h5190007.bauhaus.R
import com.h5190007.bauhaus.ui.categories.CategoriesActivity
import com.h5190007.bauhaus.ui.login.LoginActivity
import com.h5190007.bauhaus.util.*

class SplashActivity : AppCompatActivity() {

    var countDownTimer: CountDownTimer? = null
    var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash()
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }

    private fun splash() {
        countDownTimer = object : CountDownTimer(Constants.COUNTDOWN_MILISECONDS, Constants.COUNTDOWN_INTERVAL){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                switchActivity()
            }
        }.start()
    }

    private fun switchActivity() {
        if (NetworkUtil.checkNetworkConn(applicationContext)){
            if (checkLoggedUser()) {
                startActivity(Intent(this@SplashActivity, CategoriesActivity::class.java))
            }
            else
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        } else {
            AlertDialogUtil.showAlertDialog(this, ALERT_TYPE.ALERT_NO_INTERNET)
        }
    }

    private fun checkLoggedUser(): Boolean {
        userName = PrefUtil.getStringPref(applicationContext, Constants.MOVED_USERNAME)
        userName?.let {
            return true
        }

        return false
    }
}