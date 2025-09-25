package edu.ccrm.config;
import java.nio.file.Path;
import java.nio.file.Paths;
public class AppConfig {
private static AppConfig instance;
private final Path dataDirectory;
private AppConfig() {
this.dataDirectory = Paths.get("test-data");
}
public static AppConfig getInstance() {
if (instance == null) {
instance = new AppConfig();
}
return instance;
}
public Path getDataDirectory() {
return dataDirectory;
}
}