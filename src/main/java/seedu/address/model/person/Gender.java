package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
//@@author hypertun
/**
 * Represents a Person's Gender (whether male or female) in the Personal Trainer Pro app.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_GENDER_CONSTRAINTS =
            "Gender can only be the alphabets M or F or m or f";
    public static final String GENDER_VALIDATION_REGEX = "[mfMF]{1}+";
    public final String value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param sex A valid gender.
     */
    public Gender(String sex) {
        requireNonNull(sex);
        checkArgument(isValidGender(sex), MESSAGE_GENDER_CONSTRAINTS);
        this.value = sex;
    }

    /**
     * Returns true if a given string is a valid Gender.
     */
    public static boolean isValidGender(String test) {
        return test.matches(GENDER_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && this.value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
