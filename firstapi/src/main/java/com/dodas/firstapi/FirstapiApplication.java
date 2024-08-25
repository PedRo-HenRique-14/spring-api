 package com.dodas.firstapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dodas.firstapi.util.Config;
import com.dodas.firstapi.util.DirectoryManager;

@SpringBootApplication
public class FirstapiApplication {
	static Config config;
	static DirectoryManager dirMan;
	public static void main(String[] args) {
		SpringApplication.run(FirstapiApplication.class, args);
		config = Config.getConfigManager();
		dirMan = DirectoryManager.getManager();
		config.loadConfig();
		dirMan.createDirectory(config.getConfigProp("response.directory"));
	}

}
