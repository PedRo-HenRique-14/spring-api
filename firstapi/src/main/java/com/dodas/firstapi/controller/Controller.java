package com.dodas.firstapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodas.firstapi.dto.ResponseDTO;
import com.dodas.firstapi.entity.Response;
import com.dodas.firstapi.util.Config;
import com.dodas.firstapi.util.FileManager;

@RestController
@RequestMapping("response")
public class Controller {
    
    @PostMapping
    public void newResponse(String name, int age, String gender, String profession) {
        Response res = new Response();
        res.setName(name);
        res.setAge(age);
        res.setGender(gender);
        res.setProfession(profession);
        FileManager.getFileManager().createFile(Config.getConfigManager().getConfigProp("response.directory"), res);
        System.out.println("New request done!");
        return;
    }

    @GetMapping
    public List<ResponseDTO> getAll() {

        List<ResponseDTO> res = FileManager.getFileManager().getAllFilesContent(Config.getConfigManager().getConfigProp("response.directory")).stream().map(ResponseDTO::new).toList();

        return res;
    }

}
