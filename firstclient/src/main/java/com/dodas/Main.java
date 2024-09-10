package com.dodas;

import java.util.Arrays;
import java.util.List;
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
            System.out.print("Menu----------\n"+
                            "[1] Registrar usuário\n"+
                            "[2] Atualizar Usuário\n"+
                            "[3] Deletar usuário\n"+
                            "[4] Lista de usuários\n"+
                            "[5] Sair\n"+
                            ">> "
            );
            int r = scanner.nextInt();
            switch (r) {
                case 1:
                    registerUser();
                    break;
                
                case 2:
                    updateUser();
                    break;
                
                case 3:
                    deleteUser();
                    break;
                
                case 4:
                    userList(client.getRequest());
                    break;

                case 5:
                    System.out.println("Saindo...");
                    run = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
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

    private static void updateUser() {
        User[] userList = client.getRequest();
        userList(userList);
        System.out.print("Digite o index do usuário que deseja atualizar:\n>> ");
        
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int indexToUpdate = scanner.nextInt();
        scanner.reset();

        User userToUpdate = userList[--indexToUpdate];
        System.out.print("Digite o novo nome\n>> ");
        userToUpdate.setName(scanner.next());
        System.out.print("Digite a nova idade\n>> ");
        userToUpdate.setAge(scanner.nextInt());
        System.out.print("Digite o novo gênero\n>> ");
        userToUpdate.setGender(scanner.next());
        System.out.print("Digite a nova profissão\n>> ");
        userToUpdate.setProfession(scanner.next());
        client.updateRequest(userToUpdate);
    }
    
    private static void deleteUser() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String name;
        List<User> users = Arrays.asList(client.getRequest());
        users.forEach((user) -> {
            System.out.println("--------------------------------------------");
            System.out.println(String.format("Nome: %s\nIdade: %s\nGênero: %s\nProfissão: %s", 
                user.getName(), 
                user.getAge(), 
                user.getGender(), 
                user.getProfession()
            ));
            System.out.println("--------------------------------------------");
        });
        System.out.println("Digite o nome do usuário que deseja deletar\n>> ");
        name = scanner.nextLine();
        if(!client.deleteRequest(name)) {
            System.out.printf("Ocorreu um erro ao tentar remover o usuário %s.\n", name);
            return;
        }
        System.out.println("Usuário removidos com sucesso!");
    }

    private static void userList(User[] users) {
        int index = 0;
        for(User user : users) {
            System.out.printf("--------------------------------------------\nIndex - %s\n", ++index);
            System.out.println(String.format("Nome: %s\nIdade: %s\nGênero: %s\nProfissão: %s", user.getName(), user.getAge(), user.getGender(), user.getProfession()));
            System.out.println("--------------------------------------------");
        }
    }
}