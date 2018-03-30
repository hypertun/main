package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.util.CollectionUtil;


public class UniqueWeightList implements Iterable<Weight> {

    private final ObservableList<Weight> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty WeightList.
     */
    public UniqueWeightList() {}

    /**
     * Creates a UniqueWeightList using given weights.
     * Enforces no nulls.
     */
    public UniqueWeightList(Set<Weight> weights) {
        requireAllNonNull(weights);
        internalList.addAll(weights);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Returns all weights in this list as a Set.
     * This set is mutable and change-insulated against the internal list.
     */
    public Set<Weight> toSet() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return new HashSet<>(internalList);
    }

    /**
     * Replaces the Weights in this list with those in the argument weight list.
     */
    public void setWeights(Set<Weight> weights) {
        requireAllNonNull(weights);
        internalList.setAll(weights);
        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Ensures every weight in the argument list exists in this object.
     */
    public void mergeFrom(UniqueWeightList from) {
        final Set<Weight> alreadyInside = this.toSet();
        from.internalList.stream()
                .filter(weight -> !alreadyInside.contains(weight))
                .forEach(internalList::add);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Returns true if the list contains an equivalent Weight as the given argument.
     */
    public boolean contains(Weight toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a Weight to the list.
     *
     * @throws UniqueWeightList.DuplicateWeightException if the Weight to add is a duplicate of an existing Weight in the list.
     */
    public void add(Weight toAdd) throws UniqueWeightList.DuplicateWeightException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new UniqueWeightList.DuplicateWeightException();
        }
        internalList.add(toAdd);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    @Override
    public Iterator<Weight> iterator() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return internalList.iterator();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Weight> asObservableList() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public boolean equals(Object other) {
        assert CollectionUtil.elementsAreUnique(internalList);
        return other == this // short circuit if same object
                || (other instanceof UniqueWeightList // instanceof handles nulls
                && this.internalList.equals(((UniqueWeightList) other).internalList));
    }

    /**
     * Returns true if the element in this list is equal to the elements in {@code other}.
     * The elements do not have to be in the same order.
     */
    public boolean equalsOrderInsensitive(UniqueWeightList other) {
        assert CollectionUtil.elementsAreUnique(internalList);
        assert CollectionUtil.elementsAreUnique(other.internalList);
        return this == other || new HashSet<>(this.internalList).equals(new HashSet<>(other.internalList));
    }

    @Override
    public int hashCode() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateWeightException extends DuplicateDataException {
        protected DuplicateWeightException() {
            super("Operation would result in duplicate Weights");
        }
    }
}
