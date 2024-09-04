package com.dodas.firstapi.entity;

import java.util.UUID;

import com.dodas.firstapi.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    /*
     * Entidade modelo das respostas do usuário
     */
    String uuid;
    String name;
    int age;
    String gender;
    String profession;

    public Response(RequestDTO req) {
        UUID newUuid = UUID.randomUUID();
        this.uuid = newUuid.toString();
        this.name = req.name();
        this.age = req.age();
        this.gender = req.gender();
        this.profession = req.profession();
    }
}
