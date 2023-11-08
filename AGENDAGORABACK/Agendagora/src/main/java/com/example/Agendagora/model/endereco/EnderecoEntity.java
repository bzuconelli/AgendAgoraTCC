package com.example.Agendagora.model.endereco;

public class EnderecoEntity {
    public int idendereco;
    public String rua;
    public String cidade;
    public String bairo;
    public int numero;
    public String lat;
    public String lng;

    public EnderecoEntity(int idendereco, String rua, String cidade, String bairo, int numero, String lat, String lng) {
        this.idendereco = idendereco;
        this.rua = rua;
        this.cidade = cidade;
        this.bairo = bairo;
        this.numero = numero;
        this.lat = lat;
        this.lng = lng;
    }

    public EnderecoEntity() {
    }

}
