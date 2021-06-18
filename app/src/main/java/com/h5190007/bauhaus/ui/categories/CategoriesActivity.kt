package com.h5190007.bauhaus.ui.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.h5190007.bauhaus.R
import com.h5190007.bauhaus.data.model.CategoryResponseItem
import com.h5190007.bauhaus.databinding.ActivityCategoriesBinding
import com.h5190007.bauhaus.ui.products.ProductsActivity
import com.h5190007.bauhaus.util.*
import java.util.*

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    var categoriesViewModel = CategoriesViewModel()
    private lateinit var categoriesAdapter: CategoryAdapter
    var categories: List<CategoryResponseItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName: String = PrefUtil.getStringPref(applicationContext, Constants.MOVED_USERNAME)!!

        binding.apply {
            txtUsername.text = userName
        }

        initSearchView()

        ProgressDialogUtil.showProgressDialog(this, getString(R.string.categories_progress_wait_tit))

        initViewModel()
    }

    private fun initSearchView() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterCategories(newText)
                    return false
                }
            })
        }
    }

    private fun filterCategories(searchText: String?) {
        searchText?.let {
            categories?.let {
                var filteredList = it.filter {
                    it.Name!!.contains(searchText.toUpperCase(Locale(Constants.LOCALE_TURKISH)))
                }
                categoriesAdapter.apply {
                    updateData(filteredList)
                    notifyDataSetChanged()
                }
            }
        }
    }

    private fun initViewModel() {

        categoriesViewModel.apply {
            categoriesLiveData.observe(this@CategoriesActivity, Observer {
                it.run {
                    Log.e("BBG", "observe: $it")
                    categories = it
                    initRecyclerView(it)
                    ProgressDialogUtil.dismissProgressDialog()
                }
            })


            error?.observe(this@CategoriesActivity, Observer {
                Log.e("BBG", "error: ")
                it.run {
                    Toast.makeText(applicationContext, this.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                    ProgressDialogUtil.dismissProgressDialog()
                }
            })

            loading?.observe(this@CategoriesActivity, Observer {
                Log.e("BBG", "loading: ")
                //Handle loading etc. progressDialog
                ProgressDialogUtil.dismissProgressDialog()
            })

        }
    }

    private fun initRecyclerView(categories: List<CategoryResponseItem>) {

        binding.apply {
            categoriesAdapter = CategoryAdapter(categories, object : ItemClickListener {
                override fun onItemClick(position: Int) {

                    val strProduct: String? = ObjectUtil.objectToJsonString(categories[position])

                    val intent = Intent(this@CategoriesActivity, ProductsActivity::class.java)
                    intent.putExtra(Constants.MOVED_CATEGORY_TITLE, strProduct)

                    startActivity(intent)
                }
            })

            rcvCategories.adapter = categoriesAdapter
            rcvCategories.layoutManager =
                GridLayoutManager(applicationContext, Constants.GRID_COLUMN_SIZE)
        }
    }

    override fun onBackPressed() {
        AlertDialogUtil.showAlertDialog(this, ALERT_TYPE.ALERT_EXIT)
    }
}