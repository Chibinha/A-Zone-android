package com.example.azone_android;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.azone_android.adapters.ProductListAdapter;
import com.example.azone_android.listeners.ProductListener;
import com.example.azone_android.models.Product;
import com.example.azone_android.models.SingletonStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment implements ProductListener {

    private ListView mListViewProducts;
    private ProductListAdapter mProductListAdapter;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        setHasOptionsMenu(true);

        mListViewProducts = view.findViewById(R.id.listView_products);
        mListViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) parent.getItemAtPosition(position);
                Intent product_intent = new Intent(getActivity(), ProductDetailsActivity.class);
                product_intent.putExtra("ID", product.getId());
                startActivity(product_intent);
            }
        });

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SingletonStore.getInstance(getContext()).getAllProductsAPI(getContext(), SingletonStore.isConnectedInternet(getContext()));
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CartActivity.class);
                startActivity(intent);
            }
        });

        SingletonStore.getInstance(getContext()).setProductListener(this);

        return view;
    }

    // TODO: Finish search
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // Add menu_search as options menu
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem itemSearch = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) itemSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onRefreshProductList(ArrayList<Product> productArrayList) {
        mProductListAdapter = new ProductListAdapter(getContext(), productArrayList);
        mListViewProducts.setAdapter(mProductListAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        SingletonStore.getInstance(getContext()).getAllProductsAPI(getContext(), SingletonStore.isConnectedInternet(getContext()));
    }
}