package newstart.core.services.impl;

import java.util.Map;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import newstart.core.services.YamlService;

public class YamlServiceImpl implements YamlService {

    // Le chemin par défaut du fichier YAML
    private final String defaultPath = "application.yaml";

    @Override
    public Map<String, Object> loadYaml() {
        // Charger le fichier YAML avec le chemin par défaut
        return loadYaml(defaultPath);
    }

    @Override
    public Map<String, Object> loadYaml(String path) {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        
        if (inputStream == null) {
            throw new IllegalArgumentException("Le fichier YAML n'a pas été trouvé : " + path);
        }
        
        return yaml.load(inputStream);
    }

}
