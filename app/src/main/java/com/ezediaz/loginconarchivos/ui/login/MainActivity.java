package com.ezediaz.loginconarchivos.ui.login;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ezediaz.loginconarchivos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding.btnLogearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                vm.logearse(email, password);
                binding.etEmail.setText("");
                binding.etPassword.setText("");
            }
        });
        binding.btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.registrarse();
            }
        });
    }
}