package com.gfcorrea.buscacep.api;

import com.gfcorrea.buscacep.model.CepReturnModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepAPI {

    @GET("ws/{CEP}/json/")
    Call<CepReturnModel> getCepInformation(@Path("CEP") String cep);

}
