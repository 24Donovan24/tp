package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.DESC_AMY;
import static gim.logic.commands.CommandTestUtil.DESC_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_REP_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.testutil.EditExerciseDescriptorBuilder;




public class EditExerciseDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditExerciseDescriptor descriptorWithSameValues = new EditExerciseDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditExerciseDescriptor editedAmy = new EditExerciseDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different weight -> returns false
        editedAmy = new EditExerciseDescriptorBuilder(DESC_AMY).withWeight(VALID_WEIGHT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different sets -> returns false
        editedAmy = new EditExerciseDescriptorBuilder(DESC_AMY).withSets(VALID_SETS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditExerciseDescriptorBuilder(DESC_AMY).withRep(VALID_REP_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditExerciseDescriptorBuilder(DESC_AMY).withDates(VALID_DATE).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
