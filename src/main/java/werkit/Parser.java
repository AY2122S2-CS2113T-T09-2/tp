package werkit;

import commands.Command;
import commands.ExitCommand;
import commands.InvalidCommandException;
import commands.WorkoutCommand;
import commands.HelpCommand;
import commands.ExerciseCommand;
import commands.SearchCommand;
import commands.PlanCommand;
import commands.ScheduleCommand;
import data.exercises.ExerciseList;
import data.plans.PlanList;
import data.schedule.DayList;
import data.workouts.WorkoutList;
import storage.FileManager;
import storage.LogHandler;

import java.util.logging.Logger;
import java.util.logging.Level;

import static commands.PlanCommand.ACTION_KEYWORD_DETAILS;
import static commands.ScheduleCommand.ACTION_KEYWORD_LIST;
import static commands.ScheduleCommand.ACTION_KEYWORD_UPDATE;
import static commands.ScheduleCommand.ACTION_KEYWORD_CLEAR_ALL;
import static commands.ScheduleCommand.ACTION_KEYWORD_CLEAR;
import static commands.SearchCommand.SEARCH_PLAN_ACTION_KEYWORD;
import static commands.SearchCommand.SEARCH_EXERCISE_ACTION_KEYWORD;
import static commands.SearchCommand.SEARCH_WORKOUT_ACTION_KEYWORD;
import static commands.SearchCommand.SEARCH_ALL_ACTION_KEYWORD;
import static commands.WorkoutCommand.CREATE_ACTION_KEYWORD;
import static commands.WorkoutCommand.LIST_ACTION_KEYWORD;
import static commands.WorkoutCommand.DELETE_ACTION_KEYWORD;
import static commands.WorkoutCommand.UPDATE_ACTION_KEYWORD;


/**
 * This class will parse the input that the user enters into the WerkIt! application into data
 * that can be further processed by other classes in this application.
 * Design of the commands is inspired by the AddressBook-Level2 project
 * Link: https://se-education.org/addressbook-level2/
 */
public class Parser {
    private UI ui;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;
    private PlanList planList;
    private DayList dayList;
    private FileManager fileManager;
    public static final String SPACE_CHARACTER = " ";
    public static final int EXPECTED_NUMBER_OF_PARAMETERS_HELP = 1;
    public static final int EXPECTED_NUMBER_OF_PARAMETERS_EXIT = 1;
    public static final int EXPECTED_NUMBER_OF_PARAMETERS_SEARCH_SPACE = 2;
    public static final int EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS = 2;
    public static final int EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS = 3;
    private static Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Constructs the Parser object with the required components for this instance to operate.
     *
     * @param ui           An instance of the UI class.
     * @param exerciseList An instance of the ExerciseList class.
     * @param workoutList  An instance of the WorkoutList class.
     * @param planList An instance of the PlanList class.
     * @param dayList An instance of the DayList class.
     * @param fileManager  An instance of the FileManager class.
     */
    public Parser(UI ui, ExerciseList exerciseList, WorkoutList workoutList,
                  FileManager fileManager, PlanList planList, DayList dayList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
        this.workoutList = workoutList;
        this.fileManager = fileManager;
        this.planList = planList;
        this.dayList = dayList;

        LogHandler.linkToFileLogger(logger);
    }

    /**
     * Gets the UI object stored in this Parser object.
     *
     * @return The UI object.
     */
    public UI getUi() {
        return ui;
    }

    /**
     * Gets the ExerciseList object stored in this Parser object.
     *
     * @return The ExerciseList object.
     */
    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    /**
     * Gets the WorkoutList object stored in this Parser object.
     *
     * @return The WorkoutList object.
     */
    public WorkoutList getWorkoutList() {
        return workoutList;
    }

    /**
     * Parses the user's input and determines the correct command object to instantiate.
     *
     * @param userInput The unparsed input given by the user.
     * @return A subclass of the Command object. The type of Command object is determined based on the
     *         user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public Command parseUserInput(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Check for illegal characters
        boolean hasIllegalCharacters = checkInputForIllegalCharacters(userInput);
        String className = this.getClass().getSimpleName();
        if (hasIllegalCharacters) {
            logger.log(Level.WARNING, "Illegal character used by user.");
            throw new InvalidCommandException(className, InvalidCommandException.ILLEGAL_CHARACTER_USED_ERROR_MSG);
        }

        // Determine the type of Command subclass to instantiate
        String commandKeyword = userInput.split(" ", 2)[0];

        switch (commandKeyword) {
        case WorkoutCommand.BASE_KEYWORD:
            return createWorkoutCommand(userInput);
        case ExitCommand.BASE_KEYWORD:
            return createExitCommand(userInput);
        case HelpCommand.BASE_KEYWORD:
            return createHelpCommand(userInput);
        case ExerciseCommand.BASE_KEYWORD:
            return createExerciseCommand(userInput);
        case SearchCommand.BASE_KEYWORD:
            return createSearchCommand(userInput);
        case PlanCommand.KEYWORD_BASE:
            return createPlanCommand(userInput);
        case ScheduleCommand.KEYWORD_BASE:
            return createScheduleCommand(userInput);
        default:
            logger.log(Level.WARNING, "Unknown command entered by user.");
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_COMMAND_ERROR_MSG);
        }
    }

    /**
     * Checks if the user's input contains illegal characters. Illegal characters are characters that are
     * required by the application for purposes such as reading/writing to resource files. Thus, these
     * characters cannot be used by the user.
     *
     * @param userInput The user's input.
     * @return Returns true if the user's input contains at least one illegal character. Otherwise,
     *         returns false.
     */
    private boolean checkInputForIllegalCharacters(String userInput) {
        for (String illegalCharacter : FileManager.ILLEGAL_CHARACTERS) {
            if (userInput.contains(illegalCharacter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new workout command with the appropriate parameters stored into the object.
     *
     * @param userInput The user's input.
     * @return A WorkoutCommand object containing the parsed parameters obtained from the user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public WorkoutCommand createWorkoutCommand(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Determine the action the user has entered
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        String className = this.getClass().getSimpleName();
        switch (actionKeyword) {
        case CREATE_ACTION_KEYWORD:
            arguments = getArgsForCreateAction(userInput);
            break;
        case DELETE_ACTION_KEYWORD:
            arguments = getArgsForDeleteAction(userInput);
            break;
        case UPDATE_ACTION_KEYWORD:
            arguments = getArgsForUpdateAction(userInput);
            break;
        case LIST_ACTION_KEYWORD:
            checkListActionArgs(userInput);
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid workout command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
        return new WorkoutCommand(userInput, fileManager, workoutList, planList, actionKeyword, arguments);
    }

    public void checkListActionArgs(String userInput) throws InvalidCommandException {
        String className = this.getClass().getSimpleName();
        if (userInput.split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS) {
            logger.log(Level.WARNING, "User has entered an invalid list workout command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_WORKOUT_LIST_COMMAND_ERROR_MSG);
        }
    }

    public String getArgsForUpdateAction(String userInput) throws InvalidCommandException {
        String className = this.getClass().getSimpleName();
        if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
            logger.log(Level.WARNING, "User has entered an invalid update workout command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_UPDATE_WORKOUT_COMMAND_ERROR_MSG);
        }
        String arguments = userInput.split(" ", 3)[2];
        return arguments;
    }

    public String getArgsForDeleteAction(String userInput) throws InvalidCommandException {
        String className = this.getClass().getSimpleName();
        if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
            logger.log(Level.WARNING, "User has entered an invalid delete workout command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_DELETE_WORKOUT_COMMAND_ERROR_MSG);
        }
        String arguments = userInput.split(" ", 3)[2];
        return arguments;
    }

    public String getArgsForCreateAction(String userInput) throws InvalidCommandException {
        String className = this.getClass().getSimpleName();
        if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
            logger.log(Level.WARNING, "User has entered an invalid create workout command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_NEW_WORKOUT_COMMAND_ERROR_MSG);
        }
        String arguments = userInput.split(" ", 3)[2];
        return arguments;
    }

    public ExitCommand createExitCommand(String userInput) throws InvalidCommandException {
        String className = this.getClass().getSimpleName();
        if (userInput.trim().split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_EXIT) {
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_EXIT_COMMAND_ERROR_MSG);
        }
        ExitCommand newCommand = new ExitCommand(userInput);
        return newCommand;
    }

    /**
     * Creates a new exercise command with the appropriate parameters stored into the object.
     *
     * @param userInput The user's input.
     * @return A ExerciseCommand object containing the parsed parameters obtained from the user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public ExerciseCommand createExerciseCommand(String userInput) throws
            InvalidCommandException, ArrayIndexOutOfBoundsException {
        logger.entering(getClass().getName(), "createExerciseCommand");
        String className = this.getClass().getSimpleName();
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        switch (actionKeyword) {
        case LIST_ACTION_KEYWORD:
            if (userInput.split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid list exercise command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_EXERCISE_LIST_COMMAND_ERROR_MSG);
            }
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid action under exercise command.");
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
        logger.exiting(getClass().getName(), "createExerciseCommand");
        return new ExerciseCommand(userInput, ui, exerciseList, actionKeyword, arguments);

    }

    /**
     * Creates a new help command.
     *
     * @param userInput The user's input.
     * @return A HelpCommand object.
     */
    public HelpCommand createHelpCommand(String userInput) throws InvalidCommandException {
        String className = this.getClass().getSimpleName();
        if (userInput.trim().split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_HELP) {
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_HELP_COMMAND_ERROR_MSG);
        }
        return new HelpCommand(userInput);
    }

    /**
     * Creates a new search command with the appropriate parameters stored into the object.
     *
     * @param userInput The user's input.
     * @return A SearchCommand object containing the parsed parameters obtained from the user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public SearchCommand createSearchCommand(String userInput) throws
            InvalidCommandException, ArrayIndexOutOfBoundsException {
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        String className = this.getClass().getSimpleName();
        switch (actionKeyword) {
        case SEARCH_EXERCISE_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length == EXPECTED_NUMBER_OF_PARAMETERS_SEARCH_SPACE) {
                arguments = SPACE_CHARACTER;
                return new SearchCommand(userInput, ui, exerciseList, actionKeyword, arguments);
            }
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid search exercise command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SEARCH_EXERCISE_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2].trim();
            return new SearchCommand(userInput, ui, exerciseList, actionKeyword, arguments);
        case SEARCH_PLAN_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length == EXPECTED_NUMBER_OF_PARAMETERS_SEARCH_SPACE) {
                arguments = SPACE_CHARACTER;
                return new SearchCommand(userInput, ui, planList, actionKeyword, arguments);
            }
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid search plan command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SEARCH_PLAN_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2].trim();
            return new SearchCommand(userInput, ui, planList, actionKeyword, arguments);
        case SEARCH_WORKOUT_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length == EXPECTED_NUMBER_OF_PARAMETERS_SEARCH_SPACE) {
                arguments = SPACE_CHARACTER;
                return new SearchCommand(userInput, ui, workoutList, actionKeyword, arguments);
            }
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid search workout command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SEARCH_WORKOUT_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2].trim();
            return new SearchCommand(userInput, ui, workoutList, actionKeyword, arguments);
        case SEARCH_ALL_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length == EXPECTED_NUMBER_OF_PARAMETERS_SEARCH_SPACE) {
                arguments = SPACE_CHARACTER;
                return new SearchCommand(userInput, ui, exerciseList, workoutList, planList, actionKeyword, arguments);
            }
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid search all command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SEARCH_ALL_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2].trim();
            return new SearchCommand(userInput, ui, exerciseList, workoutList, planList, actionKeyword, arguments);
        default:
            logger.log(Level.WARNING, "User has entered an invalid search command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
    }

    /**
     * Creates a new plan command with the appropriate parameters stored into the object.
     *
     * @param userInput The user's input.
     * @return A PlanCommand object containing the parsed parameters obtained from the user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public PlanCommand createPlanCommand(String userInput) throws
            ArrayIndexOutOfBoundsException, InvalidCommandException {
        // Determine the action the user has entered
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        String className = this.getClass().getSimpleName();
        switch (actionKeyword) {
        case CREATE_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid create plan command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_NEW_PLAN_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        case DELETE_ACTION_KEYWORD:
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid delete plan command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_DELETE_PLAN_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        case LIST_ACTION_KEYWORD:
            if (userInput.split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid list plan command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_PLAN_LIST_COMMAND_ERROR_MSG);
            }
            break;
        case ACTION_KEYWORD_DETAILS:
            if (userInput.split(" ", 3).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid plan details command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_PLAN_DETAILS_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid plan command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
        return new PlanCommand(userInput, fileManager, planList, actionKeyword, arguments);
    }

    /**
     * Creates a new schedule command with the appropriate parameters stored into the object.
     *
     * @param userInput The user's input.
     * @return A ScheduleCommand object containing the parsed parameters obtained from the user's input.
     * @throws ArrayIndexOutOfBoundsException If the user's input contains insufficient information to parse.
     * @throws InvalidCommandException        If the user's input contains invalid or insufficient information
     *                                        to parse.
     */
    public ScheduleCommand createScheduleCommand(String userInput) throws
            ArrayIndexOutOfBoundsException, InvalidCommandException {
        // Determine the action the user has entered
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        String className = this.getClass().getSimpleName();
        switch (actionKeyword) {
        case ACTION_KEYWORD_UPDATE:
            if (userInput.split(" ", -1).length < EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid update schedule command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SCHEDULE_UPDATE_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        case ACTION_KEYWORD_LIST:
            if (userInput.split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid list schedule command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SCHEDULE_LIST_COMMAND_ERROR_MSG);
            }
            break;
        case ACTION_KEYWORD_CLEAR:
            if (userInput.split(" ", -1).length != EXPECTED_NUMBER_OF_PARAMETERS_WITH_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid clear schedule command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SCHEDULE_CLEAR_COMMAND_ERROR_MSG);
            }
            arguments = userInput.split(" ", 3)[2];
            break;
        case ACTION_KEYWORD_CLEAR_ALL:
            if (userInput.split(" ", -1).length > EXPECTED_NUMBER_OF_PARAMETERS_NO_ARGUMENTS) {
                logger.log(Level.WARNING, "User has entered an invalid clear all schedule command action.");
                throw new InvalidCommandException(className,
                        InvalidCommandException.INVALID_SCHEDULE_CLEAR_ALL_COMMAND_ERROR_MSG);
            }
            break;
        default:
            logger.log(Level.WARNING, "User has entered an invalid schedule command action.");
            throw new InvalidCommandException(className,
                    InvalidCommandException.INVALID_ACTION_ERROR_MSG);
        }
        return new ScheduleCommand(userInput, fileManager, dayList, actionKeyword, arguments);
    }
}
