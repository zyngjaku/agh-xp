import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SettingsCommandTest {
    @Mock
    SettingsProvider settingsProvider;

    @Test
    public void whenEmptyStringIsInput_noSerializationIsMade() {
        var sut = new SettingsCommand(settingsProvider);
        sut.handleInput("");
        verify(settingsProvider, times(0)).deserialize(any());
        verify(settingsProvider, times(0)).serialize(any());
    }

    @Test
    public void whenLoadCommandIsInput_DeserializationIsMade() {
        var sut = new SettingsCommand(settingsProvider);
        sut.handleInput("load file");
        verify(settingsProvider, times(1)).deserialize(any());
        verify(settingsProvider, times(0)).serialize(any());
    }

    @Test
    public void whenSaveCommandIsInput_SerializationIsMade() {
        var sut = new SettingsCommand(settingsProvider);
        sut.handleInput("save file");
        verify(settingsProvider, times(0)).deserialize(any());
        verify(settingsProvider, times(1)).serialize(any());
    }
}
