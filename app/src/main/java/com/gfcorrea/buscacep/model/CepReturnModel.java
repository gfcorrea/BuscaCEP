package com.gfcorrea.buscacep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CepReturnModel {
    @SerializedName("cep")
    @Expose
    private String cep;

    @SerializedName("logradouro")
    @Expose
    private String logradouro;

    @SerializedName("bairro")
    @Expose
    private String bairro;

    @SerializedName("localidade")
    @Expose
    private String localidade;

    @SerializedName("uf")
    @Expose
    private String uf;

    @SerializedName("ibge")
    @Expose
    private String ibge;

    @SerializedName("gia")
    @Expose
    private String gia;

    @SerializedName("ddd")
    @Expose
    private String ddd;

    @SerializedName("siafi")
    @Expose
    private String siafi;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }
}


