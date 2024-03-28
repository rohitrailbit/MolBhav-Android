package com.example.molbhav.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.molbhav.R;
import com.example.molbhav.entity.ProductCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final List<ProductCategory> categoryList;

    public CategoryAdapter(List<ProductCategory> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        ProductCategory category = categoryList.get(position);
        // Set the category name
        holder.categoryNameTextView.setText(category.getName());

        // Load and set the category image (you should use an image loading library like Picasso or Glide)
        // Example: Picasso.get().load(category.getImageUrl()).into(holder.categoryIconImageView);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public ImageView categoryIconImageView;
        public TextView categoryNameTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryIconImageView = itemView.findViewById(R.id.categoryIcon);
            categoryNameTextView = itemView.findViewById(R.id.categoryName);
        }
    }
}
