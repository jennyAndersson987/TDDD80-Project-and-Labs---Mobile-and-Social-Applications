package com.example.lab3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab3.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {


    View view;
    private FragmentDetailBinding binding;
    private ArrayAdapter<String> adapter;


    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        String key = args.getItemtext().getString("key");
        String detailText = args.getItemtext().getString(key);

        binding = FragmentDetailBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        binding.textView2.setText(detailText);
        binding.button.setOnClickListener(view -> returnAction());

        return view;
    }
    private void returnAction(){
        NavDirections action = DetailFragmentDirections.actionDetailFragmentToPrimaryFragment();
        Navigation.findNavController(view).navigate(action);

    }
}
