package com.h5190007.bauhaus.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.h5190007.bauhaus.data.model.Product
import com.h5190007.bauhaus.databinding.ActivityDetailBinding
import com.h5190007.bauhaus.downloadAndShowImage
import com.h5190007.bauhaus.util.Constants
import com.h5190007.bauhaus.util.ObjectUtil

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val strDetail = intent.getStringExtra(Constants.MOVED_PRODUCT_TITLE)
        product = ObjectUtil.jsonStringToObject(strDetail!!)

        binding.apply {
            product?.let {
                txtBrand.text = it.BrandName
                txtName.text = it.Name
                txtPrice.text = it.Price
                txtProductAbout.text = it.Detail
                imgProductDetail.downloadAndShowImage(it.ImageUrl!!)
            }
        }
    }
}