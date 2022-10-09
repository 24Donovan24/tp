package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_REP_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.testutil.ExerciseBuilder;


public class ExerciseTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Exercise exercise = new ExerciseBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> exercise.getDates().remove(0));
    }

    @Test
    public void isSameExercise() {
        // same object -> returns true
        assertTrue(ALICE.isSameExercise(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameExercise(null));

        // same name, all other attributes different -> returns true
        Exercise editedAlice =
                new ExerciseBuilder(ALICE).withWeight(VALID_WEIGHT_BOB).withSets(VALID_SETS_BOB).withRep(VALID_REP_BOB)
                        .withDates(VALID_DATE).build();
        assertTrue(ALICE.isSameExercise(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameExercise(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Exercise editedBob = new ExerciseBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameExercise(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ExerciseBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameExercise(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise aliceCopy = new ExerciseBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different exercise -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Exercise editedAlice = new ExerciseBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different weight -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withWeight(VALID_WEIGHT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different sets -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withSets(VALID_SETS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withRep(VALID_REP_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ExerciseBuilder(ALICE).withDates(VALID_DATE).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
