package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.VALID_LEVEL_EASY;
import static gim.logic.commands.CommandTestUtil.assertCommandFailure;
import static gim.logic.commands.GenerateCommand.MESSAGE_NOT_IMPLEMENTED_YET;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static gim.testutil.TypicalIndexes.INDEX_LIST_FIRST_SECOND;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for GenerateCommand.
 */
class GenerateCommandTest {

    private Model model = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());

    @Test
    public void execute() {
        assertCommandFailure(new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_EASY),
                model, MESSAGE_NOT_IMPLEMENTED_YET);
        // work in progress
    }

    @Test
    public void equals() {
        final GenerateCommand standardCommand = new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_EASY);

        GenerateCommand commandWithSameValues = new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_EASY);
        assertTrue(standardCommand.equals(commandWithSameValues));
    }
}
