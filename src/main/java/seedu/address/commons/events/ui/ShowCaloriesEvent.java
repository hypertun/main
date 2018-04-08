package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.Person;
//@@author hypertun
/**
 * Event raised on 'goal' command's successful execution
 */
public class ShowCaloriesEvent extends BaseEvent {

    public final Person person;

    public ShowCaloriesEvent(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
