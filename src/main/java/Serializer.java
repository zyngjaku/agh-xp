import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serializer {
    private final String path;

    public Serializer(String path) {
        this.path = path;
    }

    public SettingsProvider loadFromFile() throws SerializationException {
        byte[] data = new byte[0];
        try {
            var filePath = Paths.get(path);
            data = Files.readAllBytes(filePath);
        } catch (IOException exc) {
            throw new SerializationException("Failed to read data from a file '" + path + "': " + exc.getMessage());
        }

        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, SettingsProvider.class);
        } catch (IOException exc) {
            throw new SerializationException("Failed to serialize configuration from a file '" + path + "': " + exc.getMessage());
        }
    }

    public void writeToFile(SettingsProvider provider) throws SerializationException {
        var mapper = new ObjectMapper();
        try {
            var file = Paths.get(path).toFile();
            mapper.writeValue(file, provider);
        } catch (IOException exc) {
            throw new SerializationException("Failed to write settings to a file '" + path + "': " + exc.getMessage());
        }
    }


}
