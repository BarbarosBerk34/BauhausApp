package com.h5190007.bauhaus.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190007.bauhaus.data.model.Product
import com.h5190007.bauhaus.databinding.CardviewProductBinding
import com.h5190007.bauhaus.downloadAndShowImage
import com.h5190007.bauhaus.util.ItemClickListener

class ProductsAdapter(
    var products: List<Product>,
    var onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardviewProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardviewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            binding.apply {

                txtProductName.text = products[position].Name
                imgProduct.downloadAndShowImage(products[position].ImageUrl!!)

                cardView.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    fun updateData(newProducts: List<Product>){
        products = newProducts
    }

    override fun getItemCount(): Int {
        return products.size
    }

}