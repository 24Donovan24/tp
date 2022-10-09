package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REP;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import gim.commons.core.index.Index;
import gim.logic.commands.EditCommand;
import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.logic.parser.exceptions.ParseException;
import gim.model.tag.Date;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_WEIGHT, PREFIX_SETS, PREFIX_REP, PREFIX_DATE);


        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditExerciseDescriptor editExerciseDescriptor = new EditExerciseDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editExerciseDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            editExerciseDescriptor.setWeight(ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_SETS).isPresent()) {
            editExerciseDescriptor.setSets(ParserUtil.parseSets(argMultimap.getValue(PREFIX_SETS).get()));
        }
        if (argMultimap.getValue(PREFIX_REP).isPresent()) {
            editExerciseDescriptor.setRep(ParserUtil.parseRep(argMultimap.getValue(PREFIX_REP).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_DATE)).ifPresent(editExerciseDescriptor::setTags);

        if (!editExerciseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editExerciseDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Date>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
