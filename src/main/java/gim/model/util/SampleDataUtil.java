package gim.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import gim.model.AddressBook;
import gim.model.ReadOnlyAddressBook;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Rep;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            new Exercise(new Name("Alex Yeoh"), new Weight("87438807"), new Sets("alexyeoh@example.com"),
                new Rep("1"),
                getTagSet("friends")),
            new Exercise(new Name("Bernice Yu"), new Weight("99272758"), new Sets("berniceyu@example.com"),
                new Rep("2"),
                getTagSet("colleagues", "friends")),
            new Exercise(new Name("Charlotte Oliveiro"), new Weight("93210283"), new Sets("charlotte@example.com"),
                new Rep("3"),
                getTagSet("neighbours")),
            new Exercise(new Name("David Li"), new Weight("91031282"), new Sets("lidavid@example.com"),
                new Rep("4"),
                getTagSet("family")),
            new Exercise(new Name("Irfan Ibrahim"), new Weight("92492021"), new Sets("irfan@example.com"),
                new Rep("5"),
                getTagSet("classmates")),
            new Exercise(new Name("Roy Balakrishnan"), new Weight("92624417"), new Sets("royb@example.com"),
                new Rep("1"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Exercise sampleExercise : getSampleExercises()) {
            sampleAb.addExercise(sampleExercise);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
