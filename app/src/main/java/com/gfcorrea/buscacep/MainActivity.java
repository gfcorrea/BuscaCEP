package com.gfcorrea.buscacep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import android.view.View;
import android.widget.Toast;

import com.gfcorrea.buscacep.api.ViaCepAPI;
import com.gfcorrea.buscacep.databinding.ActivityMainBinding;
import com.gfcorrea.buscacep.model.CepReturnModel;
import com.gfcorrea.buscacep.viewmodel.CepViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CepViewModel cepViewModel = new ViewModelProvider(this).get(CepViewModel.class);


        final Observer<String> logradouroObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.lblLogradouro.setText("Logradouro: " + s);
            }
        };

        final Observer<String> cidadeObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.lblCidade.setText("Cidade: " + s);
            }
        };

        final Observer<String> ufObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.lblUF.setText("UF: " + s);
            }
        };

        final Observer<String> bairroObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.lblBairro.setText("Bairro: " + s);
            }
        };

        final Observer<String> ibgeObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.lblIBGE.setText("Nº IBGE: " + s);
            }
        };

        cepViewModel.getLogradouro().observe(this, logradouroObserver);
        cepViewModel.getLocalidade().observe(this, cidadeObserver);
        cepViewModel.getBairro().observe(this, bairroObserver);
        cepViewModel.getNibge().observe(this, ibgeObserver);
        cepViewModel.getUf().observe(this, ufObserver);


        binding.btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                executor.execute(() -> {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://viacep.com.br/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ViaCepAPI apiService = retrofit.create(ViaCepAPI.class);

                    Call<CepReturnModel> call = apiService.getCepInformation(binding.txtCEP.getText().toString());

                    call.enqueue(new Callback<CepReturnModel>() {
                        @Override
                        public void onResponse(Call<CepReturnModel> call, Response<CepReturnModel> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "ERROR onResponde: " + response.code(), Toast.LENGTH_LONG).show();
                            } else {
                                CepReturnModel cep = response.body();
                                cepViewModel.getLocalidade().setValue(cep.getLocalidade());
                                cepViewModel.getBairro().setValue(cep.getBairro());
                                cepViewModel.getNibge().setValue(cep.getIbge());
                                cepViewModel.getLogradouro().setValue(cep.getLogradouro());
                                cepViewModel.getUf().setValue(cep.getUf());
                            }
                        }

                        @Override
                        public void onFailure(Call<CepReturnModel> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    });

                });

            }
        });


    }
}