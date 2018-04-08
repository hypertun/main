package seedu.address.logic.parser;

//@@author hypertun
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.address.logic.commands.CaloriesCommand;

public class CaloriesCommandParserTest {
    private CaloriesCommandParser parser = new CaloriesCommandParser();

    @Test
    public void parse_validArgs_returnsCaloriesCommand() throws Exception {
        assertParseSuccess(parser, "1", new CaloriesCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() throws Exception {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CaloriesCommand.MESSAGE_USAGE));
    }
}
