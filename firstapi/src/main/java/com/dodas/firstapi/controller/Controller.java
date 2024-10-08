package com.dodas.firstapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodas.firstapi.dto.RequestDTO;
import com.dodas.firstapi.dto.ResponseDTO;
import com.dodas.firstapi.entity.Response;
import com.dodas.firstapi.util.Config;
import com.dodas.firstapi.util.FileManager;

@RestController
@RequestMapping("response")
public class Controller {
    
    @PostMapping("create")
    public void newResponse(@RequestBody RequestDTO req) {
        FileManager.getFileManager().createFile(Config.getConfigManager().getConfigProp("response.directory"), new Response(req));
        System.out.println("New request done!");
        return;
    }

    @PostMapping("update/{uuid}")
    public void updateResponse(@PathVariable("uuid") String uuid, @RequestBody RequestDTO req) {
        FileManager.getFileManager().updateFile(uuid, new Response(req));
        System.out.println("New request done!");
    }

    @GetMapping("users")
    public List<ResponseDTO> getAll() {
        System.out.println("New POST done!");
        
        String responseDirectory = Config.getConfigManager().getConfigProp("response.directory");
        List<ResponseDTO> resDTO = FileManager.getFileManager().getAllFilesContent(responseDirectory).stream().map(ResponseDTO::new).toList();

        return resDTO;
    }

    @DeleteMapping("delete/{uuid}")
    public void deleteResponse(@PathVariable("uuid") String uuid) {
        String responseDirectory = Config.getConfigManager().getConfigProp("response.directory");
        FileManager.getFileManager().deleteFiles(responseDirectory, uuid);
        System.out.println("Arquivos removidos com sucesso!");
    }

}
