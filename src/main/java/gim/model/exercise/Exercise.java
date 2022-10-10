package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import gim.model.tag.Date;

/**
 * Represents an Exercise in the exercise tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise {

    // Identity fields
    private final Name name;
    private final Weight weight;
    private final Sets sets;

    // Data fields
    private final Reps reps;
    private final Set<Date> dates = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Exercise(Name name, Weight weight, Sets sets, Reps reps, Set<Date> dates) {
        requireAllNonNull(name, weight, sets, reps, dates);
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
        this.dates.addAll(dates);
    }

    public Name getName() {
        return name;
    }

    public Weight getWeight() {
        return weight;
    }

    public Sets getSets() {
        return sets;
    }

    public Reps getReps() {
        return reps;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Date> getDates() {
        return Collections.unmodifiableSet(dates);
    }

    /**
     * Returns true if both exercises have the same name.
     * This defines a weaker notion of equality between two exercises.
     */
    public boolean isSameExercise(Exercise otherExercise) {
        if (otherExercise == this) {
            return true;
        }

        return otherExercise != null
                && otherExercise.getName().equals(getName());
    }

    /**
     * Returns true if both exercises have the same identity and data fields.
     * This defines a stronger notion of equality between two exercises.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exercise)) {
            return false;
        }

        Exercise otherExercise = (Exercise) other;
        return otherExercise.getName().equals(getName())
                && otherExercise.getWeight().equals(getWeight())
                && otherExercise.getSets().equals(getSets())
                && otherExercise.getReps().equals(getReps())
                && otherExercise.getDates().equals(getDates());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, weight, sets, reps, dates);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Weight: ")
                .append(getWeight())
                .append("; Sets: ")
                .append(getSets())
                .append("; Reps: ")
                .append(getReps());

        Set<Date> dates = getDates();
        if (!dates.isEmpty()) {
            builder.append("; Dates: ");
            dates.forEach(builder::append);
        }
        return builder.toString();
    }

}
