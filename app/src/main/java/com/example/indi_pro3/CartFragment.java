package com.example.indi_pro3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapters;
    private DataBaseHelper dataBaseHelper;
    private ArrayList<ItemMed> productArrayList = new ArrayList<>();
    private TextView textNoData;
    public Toolbar toolbar;


    private void bindView() {
        recyclerView = rootView.findViewById(R.id.recyclerView);
        textNoData = rootView.findViewById(R.id.textNoData);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("Cart");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

    }


    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        dataBaseHelper = new DataBaseHelper(requireActivity());

        bindView();
        new InitArray().execute();

        return rootView;
    }

    private class InitArray extends AsyncTask<Void, Void, ArrayList<ItemMed>> {
        @Override
        protected ArrayList<ItemMed> doInBackground(Void... voids) {
            productArrayList = dataBaseHelper.retrieveData();
            return productArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemMed> itemProducts) {

            if (itemProducts != null) {
                setUpRecycler();
            }
            super.onPostExecute(itemProducts);
        }
    }

    @Override
    public void onResume() {
        if (textNoData != null && recyclerView != null) {
            new InitArray().execute();
        }
        super.onResume();
    }

    private void setUpRecycler() {

        if (0 < productArrayList.size()) {
            textNoData.setVisibility(View.GONE);
        }

        homeAdapters = new HomeAdapter(requireActivity(), true);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 1, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(homeAdapters);

        homeAdapters.updateArrayList(productArrayList);
    }

}