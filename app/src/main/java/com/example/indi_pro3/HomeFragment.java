package com.example.indi_pro3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    public Toolbar toolbar;
    HomeAdapter searchAdapter;
    public RecyclerView rv_regesterList;
    private final ArrayList<ItemMed> arrayList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        rv_regesterList = view.findViewById(R.id.rv_regesterList);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        searchAdapter = new HomeAdapter(getActivity(), false);
        LinearLayoutManager layoutManagerfilter = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_regesterList.setLayoutManager(layoutManagerfilter);
        rv_regesterList.setAdapter(searchAdapter);

        arrayList.add(new ItemMed("0", "Paracetamol", "10", "1", "100", "325", "above 18", "325 Pow", "Tramadol Hydrochloride 36.5 mg", R.drawable.medi_1));
        arrayList.add(new ItemMed("0", "Acyclovir", "50", "1", "132", "500", "above 22", "500 Pow", "weight 25.04 mg", R.drawable.medi_3));
        arrayList.add(new ItemMed("0", "Cefixime", "20", "1", "100", "325", "above 18", "325 Pow", "Tramadol Hydrochloride 36.5 mg", R.drawable.med_2));
        arrayList.add(new ItemMed("0", "Azithromycin", "30", "1", "132", "500", "above 22", "500 Pow", "weight 25.04 mg", R.drawable.medi_4));
        arrayList.add(new ItemMed("0", "Dolo 650+", "30", "1", "132", "500", "above 22", "500 Pow", "weight 25.04 mg", R.drawable.medi_5));


        searchAdapter.updateArrayList(arrayList);
        return view;
    }


}