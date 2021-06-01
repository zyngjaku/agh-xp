import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializerTests {

    @Test
    void givenValidJson_settingsContainExpectedValues() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var givenJson = "{ \"currency\": \"aaaaa\", \"language\": \"EN\" }";
        var settings = objectMapper.readValue(givenJson, SettingsProvider.class);

        assertEquals("aaaaa", settings.getCurrency());
        assertEquals("EN", settings.getLanguage());
    }

}
