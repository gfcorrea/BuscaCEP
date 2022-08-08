package com.gfcorrea.buscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gfcorrea.buscacep.api.ViaCepAPI;
import com.gfcorrea.buscacep.databinding.ActivityMainBinding;
import com.gfcorrea.buscacep.model.CepReturnModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                            binding.lblCidade.setText("Cidade: " + cep.getLocalidade());
                            binding.lblUF.setText("UF: " + cep.getUf());
                            binding.lblLogradouro.setText("Logradouro: " + cep.getLogradouro());
                            binding.lblBairro.setText("Bairro: " + cep.getBairro());
                            binding.lblIBGE.setText("Nº IBGE: " + cep.getIbge());

                        }
                    }

                    @Override
                    public void onFailure(Call<CepReturnModel> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                });

            }
        });


    }
}