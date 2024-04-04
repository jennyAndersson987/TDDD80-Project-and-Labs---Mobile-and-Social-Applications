package com.example.laba1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.laba1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        EditText textEditable = binding.editTextText;
        Button button = binding.buttonid;
        TextView text = binding.textView;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.append(textEditable.getText()+"\n");
                textEditable.setText("");
            }
        });

    }
}

