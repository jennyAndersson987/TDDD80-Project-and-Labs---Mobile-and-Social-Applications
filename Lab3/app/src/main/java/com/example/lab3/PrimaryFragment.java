package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lab2.PrimaryFragmentDirections;
import com.example.lab2.databinding.ActivityMainBinding;
import com.example.lab2.databinding.FragmentPrimaryBinding;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PrimaryFragment extends Fragment {

    View view;


    Bundle bundle = new Bundle();
    Intent intent;

    private ArrayAdapter<String> adapter;

    private FragmentPrimaryBinding binding;

    public PrimaryFragment() {
        // Required empty public constructor
    }

    public static PrimaryFragment newInstance(String param1, String param2) {
        PrimaryFragment fragment = new PrimaryFragment();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPrimaryBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1);
        binding.listview.setAdapter(adapter);

        Objects[] objects = new Objects[]{
                new Objects("hej", "hejsan"),
                new Objects("jenny", "snygg"),
                new Objects("lisa", "snygg"),
                new Objects("Cora", "snygg")
        };

        for(Objects object :objects){
            adapter.add(String.valueOf(object.text));
            bundle.putString(object.getText(), object.detailText);
        }

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Objects object = objects[position];

                bundle.putString("key", object.getText());

                NavDirections action = PrimaryFragmentDirections.actionPrimaryFragmentToDetailFragment(bundle);
                Navigation.findNavController(view).navigate(action);


            }
        });

        return view;
    }}
