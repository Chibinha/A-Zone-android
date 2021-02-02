package com.example.azone_android.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.azone_android.R;
import com.example.azone_android.models.CartItem;
import com.example.azone_android.models.Product;
import com.example.azone_android.models.SingletonStore;

import java.util.ArrayList;

public class CheckoutListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<CartItem> mCartArrayList;

    public CheckoutListAdapter(Context Context, ArrayList<CartItem> CartArrayList) {
        mContext = Context;
        mCartArrayList = CartArrayList;
    }

    @Override
    public int getCount() {
        return mCartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCartArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mLayoutInflater == null) {
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            try {
                convertView = mLayoutInflater.inflate(R.layout.checkout_item, null);

            } catch (Exception ex) {
                Log.e("A+ Zone", "getView: ", ex.fillInStackTrace());
            }
        }

        ViewHolderList viewHolder = (ViewHolderList) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolderList(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.update(position);

        return convertView;
    }


    private class ViewHolderList {
        private TextView mTextViewProductName;
        private TextView mTextViewProductPrice;
        private TextView mTextViewProductQuantity;
        private ImageView mImageViewProductImage;

        public ViewHolderList(View view) {
            mTextViewProductName = view.findViewById(R.id.textView_name);
            mTextViewProductPrice = view.findViewById(R.id.textView_price);
            mTextViewProductQuantity = view.findViewById(R.id.textView_quantity);
            mImageViewProductImage = view.findViewById(R.id.imageView_Product);
        }

        public void update(int position) {
            CartItem item = SingletonStore.getInstance(mContext).getCartItem(position);
            Product product = SingletonStore.getInstance(mContext).getProduct(item.getId());
            mTextViewProductName.setText(product.getName());
            mTextViewProductPrice.setText(product.getPrice() + "€");
            mTextViewProductQuantity.setText(String.valueOf(item.getQuantity()));

            Glide.with(mContext)
                    .load(product.getImage())
                    .placeholder(R.drawable.no_image)
                    .thumbnail(0f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mImageViewProductImage);
        }
    }
}
