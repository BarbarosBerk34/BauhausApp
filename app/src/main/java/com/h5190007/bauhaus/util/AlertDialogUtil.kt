package com.h5190007.bauhaus.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.h5190007.bauhaus.R
import com.h5190007.bauhaus.data.model.Product
import com.h5190007.bauhaus.ui.products.ProductsActivity.Companion.products
import com.h5190007.bauhaus.ui.products.ProductsActivity.Companion.productsAdapter

object AlertDialogUtil {
    fun showAlertDialog(
        activity: Activity,
        alertType: ALERT_TYPE,
        productList: ArrayList<Product>? = null
    ) {
        val builder = AlertDialog.Builder(activity)
        when (alertType) {
            ALERT_TYPE.ALERT_NO_INTERNET -> {
                builder.apply {
                    setTitle(R.string.alert_no_internet_tit)
                    setMessage(R.string.alert_no_internet_msg)
                    setCancelable(false)
                    setPositiveButton(R.string.alert_no_internet_posBtn) { dialog, which ->
                        activity.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
                    }
                    setNegativeButton(R.string.alert_no_internet_negBtn) { dialog, which ->
                        activity.finish()
                    }
                }
            }
            ALERT_TYPE.ALERT_EXIT -> {
                builder.apply {
                    setTitle(R.string.alert_exit_tit)
                    setMessage(R.string.alert_exit_msg)
                    setCancelable(false)
                    setPositiveButton(R.string.alert_exit_posBtn) { dialog, which ->
                        activity.finish()
                    }
                    setNegativeButton(R.string.alert_exit_negBtn) { dialog, which ->
                        dialog.dismiss()
                    }
                }

            }
            ALERT_TYPE.ALERT_SORT -> {
                builder.apply {
                    setTitle(R.string.alert_sort_tit)
                    val sortingTypes = arrayOf(
                        activity.getString(R.string.alert_sort_item_asc), activity.getString(
                            R.string.alert_sort_item_desc
                        )
                    )
                    setItems(sortingTypes) { dialog, position ->
                        productList?.let {
                            Log.e("BBG", it.toString())

                            when (position) {
                                0 -> {
                                    it.sortBy { it.Name }
                                    products = productList
                                }
                                1 -> {
                                    it.sortByDescending { it.Name }
                                }
                            }
                            products = productList
                            productsAdapter!!.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}