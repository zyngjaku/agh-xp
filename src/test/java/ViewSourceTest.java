import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ViewSourceTest {
    @Mock
    InputSource inputSourceMock;
    @Mock
    RepositoryProvider repositoryProvider;
    @Mock
    BalanceCalculator balanceCalculator;

    @Test
    public void viewSource_ReturnsInvalidViewForNullLine() {
        when(inputSourceMock.read()).thenReturn(null);
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertTrue(sut.processNextCommand() instanceof InvalidInputView);
    }

    @Test
    public void viewSource_ReturnsInvalidViewForNotExistentCommand() {
        when(inputSourceMock.read()).thenReturn("nonexistent command");
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertTrue(sut.processNextCommand() instanceof InvalidInputView);
    }

    @Test
    public void viewSource_ReturnsCorrectObjectForHelpCommand() {
        when(inputSourceMock.read()).thenReturn("help");
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertTrue(sut.processNextCommand() instanceof HelpView);
    }

    @Test
    public void viewSource_ReturnsCorrectObjectForAddGoalCommand() {
        when(inputSourceMock.read()).thenReturn("addgoal");
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertTrue(sut.processNextCommand() instanceof AddGoalView);
    }

    @Test
    public void viewSource_ReturnsCorrectObjectForAddCyclicalCommand() {
        when(inputSourceMock.read()).thenReturn("addcyclical");
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertTrue(sut.processNextCommand() instanceof AddCyclicalItemView);
    }

    @Test
    public void viewSource_ReturnsCorrectObjectForAddGoalSummary() {
        when(inputSourceMock.read()).thenReturn("goalsummary");
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertTrue(sut.processNextCommand() instanceof GoalSummaryView);
    }

    @Test
    public void viewSource_ReturnsNullObjectForQuitCommand() {
        when(inputSourceMock.read()).thenReturn("quit");
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertNull(sut.processNextCommand());
    }

    @Test
    public void viewSource_ReturnsCorrectObjectForSettings() {
        when(inputSourceMock.read()).thenReturn("settings");
        var sut = new ViewSource(inputSourceMock, repositoryProvider, new SettingsProvider(), balanceCalculator);
        assertTrue(sut.processNextCommand() instanceof SettingsView);
    }
}


