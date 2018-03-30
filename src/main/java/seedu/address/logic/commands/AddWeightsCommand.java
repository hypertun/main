package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

public class AddWeightsCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "w/";
    public static final String COMMAND_ALIAS = "weight/";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add weight to the person(s) identified "
            + "by the index number used in the last person listing. "
            + "Input weight will append to the existing weights.\n"
            + "Parameters: [WEIGHT] "
            + "INDEX1 INDEX2... (must be a positive integer)"
            + "Example: " + COMMAND_WORD + " 55.5 "
            + "1 2 3 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final ArrayList<Index> index;
    private final WeightAddDescriptor weightAddDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param weightAddDescriptor details to edit the person with
     */
    public AddWeightsCommand(ArrayList<Index> index, WeightAddDescriptor weightAddDescriptor) {
        requireNonNull(index);
        requireNonNull(weightAddDescriptor);

        this.index = index;
        this.weightAddDescriptor = new WeightAddDescriptor(weightAddDescriptor);
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.get(0).getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.get(0).getZeroBased());
        Set<Weight> originalWeightList = personToEdit.getWeights();
        weightAddDescriptor.getWeights().addAll(originalWeightList);
        Person editedPerson = createEditedPerson(personToEdit, weightAddDescriptor);
        try {
            model.updatePerson(personToEdit, editedPerson);
        } catch (DuplicatePersonException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } catch (PersonNotFoundException pnfe) {
            throw new AssertionError("The target person cannot be missing");
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             WeightAddDescriptor weightAddDescriptor) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Height updatedHeight = personToEdit.getHeight();
        Weight updatedWeight = personToEdit.getWeight();
        Gender updatedGender = personToEdit.getGender();
        Age updatedAge = personToEdit.getAge();
        Set<Weight> updatedWeights = weightAddDescriptor.getWeights();
        Set<Tag> updatedTags = personToEdit.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedHeight, updatedWeight,
                updatedGender, updatedAge, updatedWeights, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        AddWeightsCommand e = (AddWeightsCommand) other;
        return index.equals(e.index)
                && weightAddDescriptor.equals(e.weightAddDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class WeightAddDescriptor {
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

        public WeightAddDescriptor() {}

        public WeightAddDescriptor(WeightAddDescriptor toCopy) {
            this.name = toCopy.name;
            this.phone = toCopy.phone;
            this.email = toCopy.email;
            this.address = toCopy.address;
            this.height = toCopy.height;
            this.weight = toCopy.weight;
            this.gender = toCopy.gender;
            this.age = toCopy.age;
            this.weights = toCopy.weights;
            this.tags = toCopy.tags;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(this.weights);
        }

        public void setWeights(Set<Weight> weights) {
            this.weights = weights;
        }

        public Set<Weight> getWeights() {
            return weights;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof WeightAddDescriptor)) {
                return false;
            }

            // state check
            WeightAddDescriptor e = (WeightAddDescriptor) other;

            return getWeights().equals(e.getWeights());
        }
    }
}
