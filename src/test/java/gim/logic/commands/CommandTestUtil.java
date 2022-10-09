package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REP;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gim.commons.core.index.Index;
import gim.logic.commands.exceptions.CommandException;
import gim.model.AddressBook;
import gim.model.Model;
import gim.model.exercise.Exercise;
import gim.model.exercise.NameContainsKeywordsPredicate;
import gim.testutil.EditExerciseDescriptorBuilder;



/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_WEIGHT_AMY = "11111111";
    public static final String VALID_WEIGHT_BOB = "22222222";
    public static final String VALID_SETS_AMY = "3";
    public static final String VALID_SETS_BOB = "5";
    public static final String VALID_REP_AMY = "1";
    public static final String VALID_REP_BOB = "2";
    public static final String VALID_DATE = "11/01/2007";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String WEIGHT_DESC_AMY = " " + PREFIX_WEIGHT + VALID_WEIGHT_AMY;
    public static final String WEIGHT_DESC_BOB = " " + PREFIX_WEIGHT + VALID_WEIGHT_BOB;
    public static final String SETS_DESC_AMY = " " + PREFIX_SETS + VALID_SETS_AMY;
    public static final String SETS_DESC_BOB = " " + PREFIX_SETS + VALID_SETS_BOB;
    public static final String REP_DESC_AMY = " " + PREFIX_REP + VALID_REP_AMY;
    public static final String REP_DESC_BOB = " " + PREFIX_REP + VALID_REP_BOB;
    public static final String DATE_DESC = " " + PREFIX_DATE + VALID_DATE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_WEIGHT_DESC = " " + PREFIX_WEIGHT + "911a"; // 'a' not allowed in weights
    public static final String INVALID_SETS_DESC = " " + PREFIX_SETS; // empty string not allowed for sets
    public static final String INVALID_REP_DESC = " " + PREFIX_REP; // empty string not allowed for reps
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "2022/05/02"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditExerciseDescriptor DESC_AMY;
    public static final EditCommand.EditExerciseDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditExerciseDescriptorBuilder().withName(VALID_NAME_AMY)
                .withWeight(VALID_WEIGHT_AMY).withSets(VALID_SETS_AMY).withRep(VALID_REP_AMY)
                .withDates(VALID_DATE).build();
        DESC_BOB = new EditExerciseDescriptorBuilder().withName(VALID_NAME_BOB)
                .withWeight(VALID_WEIGHT_BOB).withSets(VALID_SETS_BOB).withRep(VALID_REP_BOB)
                .withDates(VALID_DATE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered exercise list and selected exercise in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Exercise> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExerciseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredExerciseList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the exercise at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showExerciseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExerciseList().size());

        Exercise exercise = model.getFilteredExerciseList().get(targetIndex.getZeroBased());
        final String[] splitName = exercise.getName().fullName.split("\\s+");
        model.updateFilteredExerciseList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredExerciseList().size());
    }

}
