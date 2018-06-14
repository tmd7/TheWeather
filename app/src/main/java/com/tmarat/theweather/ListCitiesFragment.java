package com.tmarat.theweather;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListCitiesFragment extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cities,container, false);
        view.findViewById(R.id.recycler_view);
        return view;
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
