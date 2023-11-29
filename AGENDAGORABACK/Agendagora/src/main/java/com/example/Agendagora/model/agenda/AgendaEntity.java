package com.example.Agendagora.model.agenda;

import com.example.Agendagora.model.prestador.PrestadorEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AgendaEntity {
    public int idagenda;

    public String data;
    public PrestadorEntity prestadorEntity;
    public int qtdVagas;

    public AgendaEntity(int idagenda, String data, PrestadorEntity prestadorEntity) {
        this.idagenda = idagenda;
        this.data = data;
        this.prestadorEntity = prestadorEntity;
    }

    public AgendaEntity() {
    }
}
