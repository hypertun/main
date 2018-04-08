package seedu.address.model.person;

//@@author hypertun
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class AgeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null Age
        Assert.assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid age
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("Age")); // non-numeric
        assertFalse(Age.isValidAge("9p.2")); // alphabets within digits
        assertFalse(Age.isValidAge("9 3")); // spaces within digits

        // valid age numbers
        assertTrue(Age.isValidAge("1")); // exactly 1 numbers
        assertTrue(Age.isValidAge("95"));
        assertTrue(Age.isValidAge("100")); // heavy age
    }
}
