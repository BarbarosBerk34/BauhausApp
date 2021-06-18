package com.h5190007.bauhaus.ui.products

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190007.bauhaus.R
import com.h5190007.bauhaus.data.model.CategoryResponseItem
import com.h5190007.bauhaus.data.model.Product
import com.h5190007.bauhaus.databinding.ActivityProductsBinding
import com.h5190007.bauhaus.ui.detail.DetailActivity
import com.h5190007.bauhaus.util.*

class ProductsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductsBinding
    var selectedCategory: CategoryResponseItem? = null
    var gridLayoutManager: GridLayoutManager? = null
    var linearLayoutManager: LinearLayoutManager? = null

    companion object {
        var products: List<Product>? = null
        var productsAdapter: ProductsAdapter? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ProgressDialogUtil.showProgressDialog(this, getString(R.string.progress_wait_tit))

        gridLayoutManager = GridLayoutManager(applicationContext, Constants.GRID_COLUMN_SIZE)
        linearLayoutManager = LinearLayoutManager(applicationContext)

        val strMovedCategory = intent.getStringExtra(Constants.MOVED_CATEGORY_TITLE)
        selectedCategory = ObjectUtil.jsonStringToObject(strMovedCategory!!)

        products = selectedCategory!!.Products

        binding.apply {
            txtSelectedCategoryName.text = selectedCategory!!.Name

            btnToggleRcvLayout.setOnClickListener {
                toggleLayout()
            }

            btnSort.setOnClickListener {
                AlertDialogUtil.showAlertDialog(
                    this@ProductsActivity,
                    ALERT_TYPE.ALERT_SORT,
                    products as ArrayList<Product>
                )
            }
        }

        initRecyclerView(products!!)

    }

    private fun toggleLayout() {
        binding.apply {
            if (rcvProducts.layoutManager == gridLayoutManager) {
                rcvProducts.layoutManager = linearLayoutManager
                btnToggleRcvLayout.text = getString(R.string.products_grid_type)
            } else {
                rcvProducts.layoutManager = gridLayoutManager
                btnToggleRcvLayout.text = getString(R.string.products_list_type)
            }
        }
    }

    private fun initRecyclerView(products: List<Product>) {

        binding.apply {
            productsAdapter = ProductsAdapter(products, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@ProductsActivity, DetailActivity::class.java)

                    var strDetail: String = ObjectUtil.objectToJsonString(products[position])
                    intent.putExtra(Constants.MOVED_PRODUCT_TITLE, strDetail)
                    startActivity(intent)
                }
            })

            ProgressDialogUtil.dismissProgressDialog()

            rcvProducts.adapter = productsAdapter
            rcvProducts.layoutManager = gridLayoutManager
        }
    }
}