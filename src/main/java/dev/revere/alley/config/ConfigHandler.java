package dev.revere.alley.config;

import lombok.Getter;
import dev.revere.alley.Alley;
import dev.revere.alley.util.logger.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emmy
 * @project Alley
 * @date 19/04/2024 - 17:39
 */
@Getter
public class ConfigHandler {
    private final Map<String, File> configFiles = new HashMap<>();
    private final Map<String, FileConfiguration> fileConfigurations = new HashMap<>();

    private final FileConfiguration settingsConfig;
    private final FileConfiguration messagesConfig;
    private final FileConfiguration databaseConfig;
    private final FileConfiguration ffaConfig;
    private final FileConfiguration kitsConfig;
    private final FileConfiguration arenasConfig;
    private final FileConfiguration scoreboardConfig;
    private final FileConfiguration tablistConfig;

    private final String[] configFileNames = {
            "settings.yml", "messages.yml",
            "database/database.yml",
            "storage/ffa.yml", "storage/kits.yml", "storage/arenas.yml",
            "providers/scoreboard.yml", "providers/tablist.yml"
    };

    /**
     * Constructor for the ConfigHandler class.
     */
    public ConfigHandler() {
        for (String fileName : configFileNames) {
            loadConfig(fileName);
        }

        settingsConfig = getConfig("settings.yml");
        messagesConfig = getConfig("messages.yml");
        databaseConfig = getConfig("database/database.yml");
        ffaConfig = getConfig("storage/ffa.yml");
        kitsConfig = getConfig("storage/kits.yml");
        arenasConfig = getConfig("storage/arenas.yml");
        scoreboardConfig = getConfig("providers/scoreboard.yml");
        tablistConfig = getConfig("providers/tablist.yml");
    }

    /**
     * Load a configuration file.
     *
     * @param fileName The name of the file.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void loadConfig(String fileName) {
        File configFile = new File(Alley.getInstance().getDataFolder(), fileName);
        configFiles.put(fileName, configFile);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            Alley.getInstance().saveResource(fileName, false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        fileConfigurations.put(fileName, config);
    }

    /**
     * Reload all configurations.
     */
    public void reloadConfigs() {
        for (String fileName : configFileNames) {
            loadConfig(fileName);
        }
    }

    /**
     * Save a configuration file.
     *
     * @param configFile The file to save.
     * @param fileConfiguration The configuration to save.
     */
    public void saveConfig(File configFile, FileConfiguration fileConfiguration) {
        try {
            fileConfiguration.save(configFile);
            fileConfiguration.load(configFile);
        } catch (Exception e) {
            Logger.logError("Error occurred while saving config: " + configFile.getName());
        }
    }

    /**
     * Get a file configuration by its name.
     *
     * @param configName The name of the config file.
     * @return The file configuration.
     */
    public FileConfiguration getConfig(String configName) {
        return fileConfigurations.get(configName);
    }

    /**
     * Get a file by its name.
     *
     * @param fileName The name of the file.
     * @return The file.
     */
    public File getConfigFile(String fileName) {
        return configFiles.get(fileName);
    }
}