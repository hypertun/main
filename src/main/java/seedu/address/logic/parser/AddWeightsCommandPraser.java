package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddWeightsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Weight;

public class AddWeightsCommandPraser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddWeightsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argsArray = args.trim().split(" ");
        String newWeight = argsArray[0];
        HashSet<String> weightSet = new HashSet<>();
        weightSet.add(newWeight);
        ArrayList<Index> index = new ArrayList<>();

        try {
            for (int i = 1; i < argsArray.length; i++) {
                index.add(ParserUtil.parseIndex(argsArray[i]));
            }
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeightsCommand.MESSAGE_USAGE));
        }

        AddWeightsCommand.WeightAddDescriptor weightAddDescriptor = new AddWeightsCommand.WeightAddDescriptor();
        try {
            parseWeightsForEdit(weightSet).ifPresent(weightAddDescriptor::setWeights);
        } catch (IllegalValueException ive) {
            throw new ParseException(ive.getMessage(), ive);
        }

        if (!weightAddDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AddWeightsCommand.MESSAGE_NOT_EDITED);
        }

        return new AddWeightsCommand(index, weightAddDescriptor);
    }

    /**
     * Parses {@code Collection<String> weights} into a {@code Set<Weight>} if {@code weights} is non-empty.
     * If {@code weights} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Weight>} containing zero Weights.
     */
    private Optional<Set<Weight>> parseWeightsForEdit(Collection<String> weights) throws IllegalValueException {
        assert weights != null;

        if (weights.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> weightSet = weights.size() == 1 && weights.contains("") ? Collections.emptySet() : weights;
        return Optional.of(ParserUtil.parseWeights(weightSet));
    }
}
