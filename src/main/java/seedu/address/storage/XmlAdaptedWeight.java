package seedu.address.storage;

import javax.xml.bind.annotation.XmlValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Weight;

public class XmlAdaptedWeight {

    @XmlValue
    private String weightName;

    /**
     * Constructs an XmlAdaptedWeight.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedWeight() {}

    /**
     * Constructs a {@code XmlAdaptedWeight} with the given {@code weightName}.
     */
    public XmlAdaptedWeight(String weightName) {
        this.weightName = weightName;
    }

    /**
     * Converts a given Weight into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedWeight(Weight source) {
        weightName = source.value;
    }

    /**
     * Converts this jaxb-friendly adapted Weight object into the model's Weight object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Weight toModelType() throws IllegalValueException {
        if (!Weight.isValidWeight(weightName)) {
            throw new IllegalValueException(Weight.MESSAGE_WEIGHT_CONSTRAINTS);
        }
        return new Weight(weightName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedWeight)) {
            return false;
        }

        return weightName.equals(((XmlAdaptedWeight) other).weightName);
    }
}
