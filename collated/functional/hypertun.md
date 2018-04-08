# hypertun
###### \java\seedu\address\commons\events\ui\ShowCaloriesEvent.java
``` java
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
```
###### \java\seedu\address\model\person\Age.java
``` java
/**
 * Represents a Person's age in the Personal Trainer Pro app.
 * Guarantees: immutable; is valid as declared in {@link #isValidAge(String)}
 */
public class Age {

    public static final String MESSAGE_AGE_CONSTRAINTS =
            "Age can only be up to three numbers long";
    public static final String AGE_VALIDATION_REGEX = "\\d{1,3}+";
    public final String value;

    /**
     * Constructs a {@code Age}.
     *
     * @param age A valid age.
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_AGE_CONSTRAINTS);
        this.value = age;
    }

    /**
     * Returns true if a given string is a valid age.
     */
    public static boolean isValidAge(String test) {
        return test.matches(AGE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Age // instanceof handles nulls
                && this.value.equals(((Age) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
```
###### \java\seedu\address\model\person\Gender.java
``` java
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
```
###### \java\seedu\address\ui\BrowserPanel.java
``` java
    public static final String CALCULATOR_PREFIX_URL = "http://www.calculator.net/calorie-calculator.html?ctype=metric";

    public static final String CALCULATOR_AGE_PREFIX = "&cage=";

    public static final String CALCULATOR_GENDER_PREFIX = "&csex=";

    public static final String CALCULATOR_HEIGHT_PREFIX = "&cheightfeet=5&cheightinch=10&cpound=160&cheightmeter=";

    public static final String CALCULATOR_WEIGHT_PREFIX = "&ckg=";

    public static final String CALCULATOR_ACTIVITY_LEVEL_PREFIX = "&cactivity=";

    public static final String CALCULATOR_SUFFIX_URL = "&printit=0";

```
###### \java\seedu\address\ui\BrowserPanel.java
``` java
    /**
     * Creates calories url from given person
     */
    public void loadPersonCalories(Person person) {
        loadPage(CALCULATOR_PREFIX_URL
                + CALCULATOR_AGE_PREFIX + person.getAge().value
                + CALCULATOR_GENDER_PREFIX + person.getGender().value.toLowerCase()
                + CALCULATOR_HEIGHT_PREFIX + person.getHeight().value
                + CALCULATOR_WEIGHT_PREFIX + person.getWeight().value
                + CALCULATOR_ACTIVITY_LEVEL_PREFIX + "1.375"
                + CALCULATOR_SUFFIX_URL);
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    public void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection().person);
    }

```
###### \java\seedu\address\ui\BrowserPanel.java
``` java
    @Subscribe
    private void handleShowCaloriesEvent(ShowCaloriesEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event,
                "Processing Calories of " + event.person.getName().fullName));
        loadPersonCalories(event.person);
    }
```
###### \resources\view\PersonListCard.fxml
``` fxml
         <Label text="Name(Gender):">
            <font>
               <Font name="System Bold" size="12.0" />
            </font></Label>
      <HBox alignment="CENTER_LEFT" spacing="5">
        <Label fx:id="id" styleClass="cell_big_label">
          <minWidth>
            <!-- Ensures that the label text is never truncated -->
            <Region fx:constant="USE_PREF_SIZE" />
          </minWidth>
        </Label>
        <Label fx:id="name" styleClass="cell_big_label" text="\$first" />
            <Label text="(" />
            <Label fx:id="gender" alignment="CENTER" style="-fx-text-fill: red;" styleClass="cell_small_label" text="\$gender" />
            <Label text=")" />
```
