package com.dodas;

import java.util.Scanner;

import com.dodas.api.Client;
import com.dodas.dto.User;

public class Main {
    static Config config;
    static Client client = new Client();

    public static void main(String[] args) {
        config = Config.getConfigManager();
        config.loadConfig();
        
        boolean run = true;
        while (run == true) {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            System.out.print("Menu----------\n[1] Registrar usuário\n[2] Lista de usuários\n[3] Sair\n>> ");
            int r = scanner.nextInt();
            switch (r) {
                case 1:
                    registerUser();
                    break;
                
                case 2:
                    userList();
                    break;
            
                default:
                    System.out.println("Saindo...");
                    run = false;
                    break;
            }
        }
    }

    private static void registerUser() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um nome: ");
        String name = scanner.nextLine();
        System.out.println("Digite uma idade: ");
        int age = Integer.valueOf(scanner.nextLine());
        System.out.println("Digite um gênero: ");
        String gender = scanner.nextLine();
        System.out.println("Digite uma profissão: ");
        String profession = scanner.nextLine();

        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setGender(gender);
        user.setProfession(profession);

        client.postRequest(user);
    }

    private static void userList() {
        User[] users = client.getRequest();
        for(User user : users) {
            System.out.println("--------------------------------------------");
            System.out.println(String.format("Nome: %s\nIdade: %s\nGênero: %s\nProfissão: %s", user.getName(), user.getAge(), user.getGender(), user.getProfession()));
            System.out.println("--------------------------------------------");
        }
    }
}