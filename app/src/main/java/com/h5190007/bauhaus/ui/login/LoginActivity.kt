package com.h5190007.bauhaus.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.h5190007.bauhaus.R
import com.h5190007.bauhaus.data.model.UserResponseItem
import com.h5190007.bauhaus.databinding.ActivityLoginBinding
import com.h5190007.bauhaus.isNotEmptyText
import com.h5190007.bauhaus.isValidEmail
import com.h5190007.bauhaus.ui.categories.CategoriesActivity
import com.h5190007.bauhaus.util.ALERT_TYPE
import com.h5190007.bauhaus.util.AlertDialogUtil
import com.h5190007.bauhaus.util.Constants
import com.h5190007.bauhaus.util.PrefUtil
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var usersViewModel = UsersViewModel()
    var users: List<UserResponseItem>? = null
    var foundUser: List<UserResponseItem>? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onBackPressed() {
        AlertDialogUtil.showAlertDialog(this@LoginActivity, ALERT_TYPE.ALERT_EXIT)
    }

    private fun init() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        login()
    }

    private fun findUserByCredentials(email: String){
        users?.let {
            foundUser = it.filter {
                it.Email!!.contains(email)
            }
        }
    }

    private fun initViewModel() {
        usersViewModel.apply {
            usersLiveData.observe(this@LoginActivity, Observer {
                it.run {
                    Log.e("BBG", "observe: $it")
                    users = it
                }
            })


            error.observe(this@LoginActivity, Observer {
                Log.e("BBG", "error: ")
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            })

            loading?.observe(this@LoginActivity, Observer {
                Log.e("BBG", "loading: ")
                //Handle loading
            })

        }
    }

    private fun login() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (edtEmail.text.isValidEmail() && edtPassword.text.isNotEmptyText()) {
                    findUserByCredentials(edtEmail.text.toString())
                    foundUser?.let {
                        if (it.isNotEmpty() && (edtPassword.text.toString() == it.first().Parola)) {
                            val userName = it.first().Adi + " " + it.first().Soyadi
                            PrefUtil.setStringPref(applicationContext, Constants.MOVED_USERNAME, userName)
                            startActivity(Intent(this@LoginActivity, CategoriesActivity::class.java))
                            finish()
                        } else {
                            txtError.text = getString(R.string.login_error_wrong_credentials)
                        }
                    }
                } else {
                    txtError.text = getString(R.string.login_error_invalid_fields)
                }
            }
        }
    }
}