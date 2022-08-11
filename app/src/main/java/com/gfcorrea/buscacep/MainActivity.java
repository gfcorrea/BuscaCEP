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

        //Iniciando o ViewModel.
        CepViewModel cepViewModel = new ViewModelProvider(this).get(CepViewModel.class);

        //Criando o observer para atualizar a tela quando a thread terminar.
        final Observer<CepReturnModel> cepObserver = new Observer<CepReturnModel>() {
            @Override
            public void onChanged(CepReturnModel cepReturnModel) {
                binding.lblLogradouro.setText("Logradouro: " + cepReturnModel.getLogradouro());
                binding.lblCidade.setText("Cidade: " + cepReturnModel.getLocalidade());
                binding.lblUF.setText("UF: " + cepReturnModel.getUf());
                binding.lblBairro.setText("Bairro: " + cepReturnModel.getBairro());
                binding.lblIBGE.setText("Nº IBGE: " + cepReturnModel.getIbge());
            }
        };
        //Setando o observer no atributo do viewmodel.
        cepViewModel.getCep().observe(this, cepObserver);

        //Click do botão.
        binding.btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inicia a Thread
                executor.execute(() -> {

                    //Constrói o retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://viacep.com.br/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ViaCepAPI apiService = retrofit.create(ViaCepAPI.class);

                    Call<CepReturnModel> call = apiService.getCepInformation(binding.txtCEP.getText().toString());

                    //Executa a chamada
                    call.enqueue(new Callback<CepReturnModel>() {
                        @Override
                        public void onResponse(Call<CepReturnModel> call, Response<CepReturnModel> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "ERROR onResponde: " + response.code(), Toast.LENGTH_LONG).show();
                            } else {
                                CepReturnModel cep = response.body();
                                //Por causa do observer a tela será atualizada sempre que o atributo do viewModel for alterado.
                                cepViewModel.getCep().setValue(cep);
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