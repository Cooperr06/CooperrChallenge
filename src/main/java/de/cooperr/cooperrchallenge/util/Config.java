package de.cooperr.cooperrchallenge.util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config extends YamlConfiguration {

    private static final File CONFIG_FILE = new File("./resources/config.yml");

    public Config() {
        try {
            load(CONFIG_FILE);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
