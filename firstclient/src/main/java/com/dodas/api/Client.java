package com.dodas.api;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.dodas.Config;
import com.dodas.dto.User;
import com.google.gson.Gson;

public class Client {
    Gson gson = new Gson();
    HttpClient client = HttpClient.newHttpClient();

    public void postRequest(User user) {
        try {
            
            HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("http://%s/response", Config.getConfigManager().getConfigProp("server.ip"))))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                        .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    System.out.println("Resposta enviada!");
                } else {
                    System.out.println("Falha ao enviar resposta...");
                }
            } catch (ConnectException e) {
                System.out.println("Erro ao enviar resposta...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User[] getRequest() {
        try {
            
            HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("http://%s/response", Config.getConfigManager().getConfigProp("server.ip"))))
                        .GET()
                        .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            User[] userList;
            userList = gson.fromJson(response.body(), User[].class);
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
