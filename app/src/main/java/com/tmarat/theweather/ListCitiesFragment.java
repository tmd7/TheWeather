package com.tmarat.theweather;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListCitiesFragment extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);
        setRecyclerView(view);
        return view;
    }

    private void setRecyclerView(View view) {
        view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getDataList());
        recyclerView.setAdapter(adapter);
    }

    public static ListCitiesFragment init(ArrayList<Data> dataList) {
        ListCitiesFragment fragment = new ListCitiesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("data", dataList);
        fragment.setArguments(args);
        return fragment;
    }

    public ArrayList<Data> getDataList() {
        return getArguments().getParcelableArrayList("data");
    }

}
