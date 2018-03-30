package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_HEIGHT = "169.0";
    public static final String DEFAULT_WEIGHT = "55.5";
    public static final String DEFAULT_GENDER = "f";
    public static final String DEFAULT_AGE = "22";
    public static final String DEFAULT_WEIGHTS = "55.5";
    public static final String DEFAULT_TAGS = "friends";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Height height;
    private Weight weight;
    private Gender gender;
    private Age age;
    private Set<Weight> weights;
    private Set<Tag> tags;

    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        height = new Height(DEFAULT_HEIGHT);
        weight = new Weight(DEFAULT_WEIGHT);
        gender = new Gender(DEFAULT_GENDER);
        age = new Age(DEFAULT_AGE);
        weights = SampleDataUtil.getWeightSet(DEFAULT_WEIGHTS);
        tags = SampleDataUtil.getTagSet(DEFAULT_TAGS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        height = personToCopy.getHeight();
        weight = personToCopy.getWeight();
        gender = personToCopy.getGender();
        age = personToCopy.getAge();
        weights = new HashSet<>(personToCopy.getWeights());
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withWeights(String ... weights) {
        this.weights = SampleDataUtil.getWeightSet(weights);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Person} that we are building.
     */
    public PersonBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Person} that we are building.
     */
    public PersonBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, height, weight, gender, age, weights, tags);
    }

}
