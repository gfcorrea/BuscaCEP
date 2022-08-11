package com.gfcorrea.buscacep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.buscacep.model.CepReturnModel;

public class CepViewModel extends ViewModel {

    private MutableLiveData<CepReturnModel> cep;

    public CepViewModel() {
    }

    public MutableLiveData<CepReturnModel> getCep() {
        if(cep == null){ cep = new MutableLiveData<CepReturnModel>(); }
        return cep;
    }
}
