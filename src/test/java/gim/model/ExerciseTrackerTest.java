package gim.model;

import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import gim.model.exercise.Exercise;
import gim.model.exercise.exceptions.DuplicateExerciseException;
import gim.testutil.ExerciseBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExerciseTrackerTest {

    private final ExerciseTracker exerciseTracker = new ExerciseTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), exerciseTracker.getExerciseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExerciseTracker_replacesData() {
        ExerciseTracker newData = getTypicalExerciseTracker();
        exerciseTracker.resetData(newData);
        assertEquals(newData, exerciseTracker);
    }

    @Test
    public void resetData_withDuplicateExercises_throwsDuplicateExerciseException() {
        // Two exercises with the same identity fields
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Exercise> newExercises = Arrays.asList(ALICE, editedAlice);
        ExerciseTrackerStub newData = new ExerciseTrackerStub(newExercises);

        assertThrows(DuplicateExerciseException.class, () -> exerciseTracker.resetData(newData));
    }

    @Test
    public void hasExercise_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseTracker.hasExercise(null));
    }

    @Test
    public void hasExercise_exerciseNotInExerciseTracker_returnsFalse() {
        assertFalse(exerciseTracker.hasExercise(ALICE));
    }

    @Test
    public void hasExercise_exerciseInExerciseTracker_returnsTrue() {
        exerciseTracker.addExercise(ALICE);
        assertTrue(exerciseTracker.hasExercise(ALICE));
    }

    @Test
<<<<<<< HEAD:src/test/java/gim/model/AddressBookTest.java
    public void hasExercise_exerciseWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addExercise(ALICE);
<<<<<<< HEAD:src/test/java/gim/model/ExerciseTrackerTest.java
        Exercise editedAlice = new ExerciseBuilder(ALICE).withRep(VALID_REPS_BENCH_PRESS).withTags(VALID_TAG_HUSBAND)
=======
    public void hasExercise_exerciseWithSameIdentityFieldsInExerciseTracker_returnsTrue() {
        exerciseTracker.addExercise(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withRep(VALID_REP_BENCH_PRESS).withTags(VALID_TAG_HUSBAND)
>>>>>>> c0f7df5d3b3500cd7399b0b4c63352ac4d96c054:src/test/java/gim/model/ExerciseTrackerTest.java
=======
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withTags(VALID_TAG_HUSBAND)
>>>>>>> reptoreps:src/test/java/gim/model/AddressBookTest.java
                .build();
        assertTrue(exerciseTracker.hasExercise(editedAlice));
    }

    @Test
    public void getExerciseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> exerciseTracker.getExerciseList().remove(0));
    }

    /**
     * A stub ReadOnlyExerciseTracker whose exercises list can violate interface constraints.
     */
    private static class ExerciseTrackerStub implements ReadOnlyExerciseTracker {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();

        ExerciseTrackerStub(Collection<Exercise> exercises) {
            this.exercises.setAll(exercises);
        }

        @Override
        public ObservableList<Exercise> getExerciseList() {
            return exercises;
        }
    }

}
