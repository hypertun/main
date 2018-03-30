package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String weight;
    @XmlElement(required = true)
    private String height;
    @XmlElement(required = true)
    private String gender;
    @XmlElement(required = true)
    private String age;

    @XmlElement
    private List<XmlAdaptedWeight> weighted = new ArrayList<>();
    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedPerson() {}

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given person details.
     */
    public XmlAdaptedPerson(String name, String phone, String email, String address,
                            String height, String weight, String gender, String age, List<XmlAdaptedWeight> weighted,
                            List<XmlAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        if (weighted != null) {
            this.weighted = new ArrayList<>(weighted);
        }
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
    }

    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        height = source.getHeight().value;
        weight = source.getWeight().value;
        gender = source.getGender().value;
        age = source.getAge().value;
        weighted = new ArrayList<>();
        for (Weight weight : source.getWeights()) {
            weighted.add(new XmlAdaptedWeight(weight));
        }
        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Weight> personWeights = new ArrayList<>();
        for (XmlAdaptedWeight weight : weighted) {
            personWeights.add(weight.toModelType());
        }

        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name name = new Name(this.name);

        if (this.phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(this.phone)) {
            throw new IllegalValueException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        final Phone phone = new Phone(this.phone);

        if (this.email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(this.email)) {
            throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        final Email email = new Email(this.email);

        if (this.address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(this.address)) {
            throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        final Address address = new Address(this.address);

        if (this.height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(this.height)) {
            throw new IllegalValueException(Height.MESSAGE_HEIGHT_CONSTRAINTS);
        }
        final Height height = new Height(this.height);

        if (this.weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(this.weight)) {
            throw new IllegalValueException(Weight.MESSAGE_WEIGHT_CONSTRAINTS);
        }
        final Weight weight = new Weight(this.weight);

        if (this.gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(this.gender)) {
            throw new IllegalValueException(Gender.MESSAGE_GENDER_CONSTRAINTS);
        }
        final Gender gender = new Gender(this.gender);

        if (this.age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(this.age)) {
            throw new IllegalValueException(Age.MESSAGE_AGE_CONSTRAINTS);
        }
        final Age age = new Age(this.age);

        final Set<Weight> weights = new HashSet<>(personWeights);

        final Set<Tag> tags = new HashSet<>(personTags);

        return new Person(name, phone, email, address, height, weight, gender, age, weights, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedPerson)) {
            return false;
        }

        XmlAdaptedPerson otherPerson = (XmlAdaptedPerson) other;
        return Objects.equals(name, otherPerson.name)
                && Objects.equals(phone, otherPerson.phone)
                && Objects.equals(email, otherPerson.email)
                && Objects.equals(address, otherPerson.address)
                && Objects.equals(height, otherPerson.height)
                && Objects.equals(weight, otherPerson.weight)
                && Objects.equals(gender, otherPerson.gender)
                && Objects.equals(age, otherPerson.age)
                && weighted.equals(otherPerson.weighted)
                && tagged.equals(otherPerson.tagged);
    }
}
