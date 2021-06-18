package com.h5190007.bauhaus.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190007.bauhaus.data.model.CategoryResponseItem
import com.h5190007.bauhaus.databinding.CardviewCategoryBinding
import com.h5190007.bauhaus.downloadAndShowImage
import com.h5190007.bauhaus.util.ItemClickListener

class CategoryAdapter(
    var categories: List<CategoryResponseItem>,
    var onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardviewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardviewCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            binding.apply {

                txtCategoryName.text = categories[position].Name
                imgCategory.downloadAndShowImage(categories[position].BannerUrl!!)

                cardView.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun updateData(newCategories: List<CategoryResponseItem>){
        categories = newCategories
    }

}