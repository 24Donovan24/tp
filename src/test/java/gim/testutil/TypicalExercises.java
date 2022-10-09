package gim.testutil;

import static gim.logic.commands.CommandTestUtil.VALID_NAME_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_REP_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_REP_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gim.model.AddressBook;
import gim.model.exercise.Exercise;


/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {

    public static final Exercise ALICE = new ExerciseBuilder().withName("Alice Pauline")
            .withRep("1").withSets("1")
            .withWeight("10")
            .withTags("friends").build();
    public static final Exercise BENSON = new ExerciseBuilder().withName("Benson Meier")
            .withRep("1")
            .withSets("1").withWeight("20")
            .withTags("owesMoney", "friends").build();
    public static final Exercise CARL = new ExerciseBuilder().withName("Carl Kurz").withWeight("30")
            .withSets("1").withRep("1").build();
    public static final Exercise DANIEL = new ExerciseBuilder().withName("Daniel Meier").withWeight("40")
            .withSets("1").withRep("1").withTags("friends").build();
    public static final Exercise ELLE = new ExerciseBuilder().withName("Elle Meyer").withWeight("50")
            .withSets("1").withRep("1").build();
    public static final Exercise FIONA = new ExerciseBuilder().withName("Fiona Kunz").withWeight("60")
            .withSets("1").withRep("1").build();
    public static final Exercise GEORGE = new ExerciseBuilder().withName("George Best").withWeight("70")
            .withSets("1").withRep("1").build();

    // Manually added
    public static final Exercise HOON = new ExerciseBuilder().withName("Hoon Meier").withWeight("80")
            .withSets("1").withRep("1").build();
    public static final Exercise IDA = new ExerciseBuilder().withName("Ida Mueller").withWeight("90")
            .withSets("1").withRep("1").build();

    // Manually added - Exercise's details found in {@code CommandTestUtil}
    public static final Exercise ARM_CURLS = new ExerciseBuilder()
            .withName(VALID_NAME_ARM_CURLS).withWeight(VALID_WEIGHT_ARM_CURLS).withSets(VALID_SETS_ARM_CURLS)
            .withRep(VALID_REP_ARM_CURLS).withTags(VALID_TAG_FRIEND).build();
    public static final Exercise BENCH_PRESS = new ExerciseBuilder()
            .withName(VALID_NAME_BENCH_PRESS).withWeight(VALID_WEIGHT_BENCH_PRESS).withSets(VALID_SETS_BENCH_PRESS)
            .withRep(VALID_REP_BENCH_PRESS).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical exercises.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Exercise exercise : getTypicalExercises()) {
            ab.addExercise(exercise);
        }
        return ab;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
