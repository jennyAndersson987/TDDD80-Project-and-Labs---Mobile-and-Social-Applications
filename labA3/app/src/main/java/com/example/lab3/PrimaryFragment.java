package com.example.lab3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.lab3.databinding.FragmentPrimaryBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PrimaryFragment extends Fragment {

    private FragmentPrimaryBinding binding;
    private ArrayAdapter<String> adapter;
    private OkHttpClient client;
    private Gson gson;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new OkHttpClient();
        gson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPrimaryBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        binding.listview.setAdapter(adapter);

        getTitles();

        return view;
    }

    private void getTitles() {
        Request request = new Request.Builder()
                .url("https://brave-mud-8154b800471f41b1bbae6eea8237e22e.azurewebsites.net/grupper")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();


                    try {
                        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                        if (jsonObject.has("grupper")) {
                            List<String> groups = gson.fromJson(jsonObject.get("grupper"), new TypeToken<List<String>>(){}.getType());
                            getActivity().runOnUiThread(() -> {
                                adapter.clear();
                                adapter.addAll(groups);
                            });
                        } else {
                            getActivity().runOnUiThread(() ->
                                    Toast.makeText(getContext(), "No groups found", Toast.LENGTH_SHORT).show()
                            );
                        }
                    } catch (JsonSyntaxException e) {
                        Log.e("PrimaryFragment", "Failed to parse JSON", e);
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), "Failed to parse data", Toast.LENGTH_SHORT).show()
                        );
                    }
                } else {
                    getActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
                    );
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.listview.setOnItemClickListener((parent, view1, position, id) -> {
            String title = adapter.getItem(position);

            Bundle bundle = new Bundle();
            bundle.putString("title", title);

            NavDirections action = PrimaryFragmentDirections.actionPrimaryFragmentToDetailFragment(bundle);
            Navigation.findNavController(view1).navigate(action);
        });
    }
}
