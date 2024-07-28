package com.dodas.firstapi.dto;

import com.dodas.firstapi.entity.Response;

public record ResponseDTO(String name, int age, String gender, String profession) {
    /*
     * Classe responsável por converter a entidade em um DTO
     */
    public ResponseDTO(Response response) {
        this(response.getName(), response.getAge(), response.getGender(), response.getProfession());
    }
}