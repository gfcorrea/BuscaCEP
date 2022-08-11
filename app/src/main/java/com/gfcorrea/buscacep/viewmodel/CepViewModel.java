package com.gfcorrea.buscacep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.buscacep.model.CepReturnModel;

public class CepViewModel extends ViewModel {

    private MutableLiveData<CepReturnModel> cep;
    private MutableLiveData<String> localidade;
    private MutableLiveData<String> uf;
    private MutableLiveData<String> logradouro;
    private MutableLiveData<String> bairro;
    private MutableLiveData<String> nibge;

    public CepViewModel() {
    }

    public MutableLiveData<String> getLocalidade() {
        if(localidade == null){ localidade = new MutableLiveData<String>(); }
        return localidade;
    }

    public MutableLiveData<String> getUf() {
        if(uf == null){ uf = new MutableLiveData<String>(); }
        return uf;
    }

    public MutableLiveData<String> getLogradouro() {
        if(logradouro == null){ logradouro = new MutableLiveData<String>(); }
        return logradouro;
    }

    public MutableLiveData<String> getBairro() {
        if(bairro == null){ bairro = new MutableLiveData<String>(); }
        return bairro;
    }

    public MutableLiveData<String> getNibge() {
        if(nibge == null){ nibge = new MutableLiveData<String>(); }
        return nibge;
    }

    public MutableLiveData<CepReturnModel> getCep() {
        if(cep == null){ cep = new MutableLiveData<CepReturnModel>(); }
        return cep;
    }
}
