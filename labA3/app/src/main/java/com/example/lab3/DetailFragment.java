package com.example.lab3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.lab3.databinding.FragmentDetailBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private OkHttpClient client;
    private Gson gson;
    private View view;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new OkHttpClient();
        gson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        Bundle args = getArguments();
        Log.d("args", args.toString());
        Bundle itemTextBundle = args.getBundle("itemtext");
        String groupName = itemTextBundle.getString("title");

        fetchDetails(groupName);

        binding.button.setOnClickListener(view -> returnAction());

        return view;
    }

    private void fetchDetails(String groupName) {
        String url = "https://brave-mud-8154b800471f41b1bbae6eea8237e22e.azurewebsites.net/medlemmar/" + groupName;
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() ->
                        binding.textView2.setText("Failed to load details")
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                    List<Objects> details = null;
                    if (jsonObject.has("medlemmar")) {
                        details = gson.fromJson(jsonObject.get("medlemmar"), new TypeToken<List<Objects>>() {}.getType());
                    }

                    List<Objects> finalDetails = details;
                    getActivity().runOnUiThread(() -> {
                        if (finalDetails != null) {
                            StringBuilder detailText = new StringBuilder();
                            for (Objects detail : finalDetails) {
                                detailText.append("namn: ").append(detail.getNamn()).append("\n")
                                        .append("epost: ").append(detail.getEpost()).append("\n")
                                        .append("svarade: ").append(detail.getSvarade()).append("\n\n");
                            }
                            binding.textView2.setText(detailText.toString());
                        } else {
                            binding.textView2.setText("No details found");
                        }
                    });
                } else {
                    Log.d("whoops", response.toString());
                    getActivity().runOnUiThread(() ->
                            binding.textView2.setText("Failed to load details")
                    );
                }
            }
        });
    }

    private void returnAction() {
        NavDirections action = DetailFragmentDirections.actionDetailFragmentToPrimaryFragment();
        Navigation.findNavController(view).navigate(action);
    }
}
