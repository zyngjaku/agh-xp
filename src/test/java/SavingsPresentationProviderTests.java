import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsPresentationProviderTests {

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "0, 0.00 PLN",
            "7, 0.07 PLN",
            "54, 0.54 PLN",
            "300, 3.00 PLN",
            "253, 2.53 PLN",
            "63684, 636.84 PLN",
            "-6473, -64.73 PLN",
            "-63, -0.63 PLN"
    })
    public void getRepresentationForGivenInput(long calculation, String expected) {
        var sut = new SavingsPresentationProvider("PLN");
        var result = sut.getRepresentation(calculation);

        assertEquals(expected, result);
    }
}
