package com.h5190007.bauhaus.util

import android.app.ProgressDialog
import android.content.Context
import com.h5190007.bauhaus.R

object ProgressDialogUtil {

    var progressDialog: ProgressDialog? = null

    fun showProgressDialog(context: Context, loadingTitle: String){
        progressDialog = ProgressDialog(context)

        progressDialog!!.apply {
            setTitle(loadingTitle)
            setMessage(context.getString(R.string.progress_wait_msg))
            setCancelable(false)
            show()
        }
    }

    fun dismissProgressDialog(){
        progressDialog!!.let {
            it.dismiss()
        }
    }
}