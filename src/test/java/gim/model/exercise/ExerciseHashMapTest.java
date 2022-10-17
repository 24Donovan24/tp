package gim.model.exercise;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.model.exercise.exceptions.ExerciseNotFoundException;
import gim.testutil.ExerciseBuilder;

public class ExerciseHashMapTest {

    private final ExerciseHashMap exerciseHashMap = new ExerciseHashMap();

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseHashMap.contains(null));
    }

    @Test
    public void contains_exerciseNotInHashMap_returnsFalse() {
        assertFalse(exerciseHashMap.contains(ALICE));
    }

    @Test
    public void contains_exerciseInHashMap_returnsTrue() {
        exerciseHashMap.add(ALICE);
        assertTrue(exerciseHashMap.contains(ALICE));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInHashMap_returnsTrue() {
        exerciseHashMap.add(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withReps(VALID_REPS_BENCH_PRESS).withDate(VALID_DATE)
                .build();
        assertTrue(exerciseHashMap.contains(editedAlice));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseHashMap.add(null));
    }

    @Test
    public void remove_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseHashMap.remove(null));
    }

    @Test
    public void remove_exerciseDoesNotExist_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> exerciseHashMap.remove(ALICE));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        exerciseHashMap.add(ALICE);
        exerciseHashMap.remove(ALICE);
        ExerciseHashMap expectedExerciseHashMap = new ExerciseHashMap();
        assertEquals(expectedExerciseHashMap, exerciseHashMap);
    }

}
