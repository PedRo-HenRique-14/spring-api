package com.dodas.firstapi.entity;

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
    String name;
    int age;
    String gender;
    String profession;
}
