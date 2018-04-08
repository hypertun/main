package seedu.address.model.person;

//@@author hypertun
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidGender = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        Assert.assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("gender")); // non-numeric
        assertFalse(Gender.isValidGender("9p.2")); // alphabets within digits
        assertFalse(Gender.isValidGender("9 5")); // spaces within digits
        assertFalse(Gender.isValidGender("y ")); // other alphabets

        // valid gender
        assertTrue(Gender.isValidGender("m")); // exactly m
        assertTrue(Gender.isValidGender("f")); // exactly f
        assertTrue(Gender.isValidGender("M")); // exactly M
        assertTrue(Gender.isValidGender("F")); // exactly F
    }
}
