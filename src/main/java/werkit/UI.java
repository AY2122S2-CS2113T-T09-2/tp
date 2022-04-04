package werkit;

import data.plans.Plan;
import data.schedule.Day;
import data.workouts.Workout;
import storage.FileManager;
import storage.LogHandler;
import storage.UnknownFileException;
import textcolors.TextColor;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 * This class contains all the user interface-related texts and methods for the WerkIt! Application.
 */
public class UI {
    private static Logger logger = Logger.getLogger(UI.class.getName());

    // WerkIt ASCII Banner Logo Art
    public static final String WERKIT_BANNER_LOGO = " __        __        _    ___ _   _ \n"
            + " \\ \\      / /__ _ __| | _|_ _| |_| |\n"
            + "  \\ \\ /\\ / / _ \\ '__| |/ /| || __| |\n"
            + "   \\ V  V /  __/ |  |   < | || |_|_|\n"
            + "    \\_/\\_/ \\___|_|  |_|\\_\\___|\\__(_)\n"
            + "                                    ";

    public static final String WELCOME_MESSAGE = "Welcome to WerkIt!, your personal exercise planner.";
    public static final String GOODBYE_MESSAGE = "Thank you for using WerkIt! See you again soon...";
    // The default parameters for printing the formatting lines
    public static final int DEFAULT_LINE_LENGTH = 70;
    public static final String DEFAULT_LINE_CHAR = "-";
    // Prompt symbol
    public static final String PROMPT_SYMBOL = ">";
    // File loading-related messages
    public static final String CHECK_DIRECTORY_AND_FILES_MSG = "Checking for required directory and files...";
    public static final String ALL_DIRECTORY_AND_FILES_PRESENT = "All OK!";
    public static final String DIRECTORY_NOT_FOUND_MSG = "- The required data directory was not found. It will "
            + "be created.";
    public static final String DIRECTORY_CREATED_MSG = "- The WerkIt! resource directory has been created in\n"
            + "  your terminal's current working directory.";
    public static final String EXERCISE_FILE_NOT_FOUND_MSG = "- The exercise file was not found. It will be created.";
    public static final String EXERCISE_FILE_CREATED_MSG = "- The exercise file 'exercises.txt' has been created in\n"
            + "  the WerkIt! resource directory.";
    public static final String WORKOUT_FILE_NOT_FOUND_MSG = "- The workout file was not found. It will be created.";
    public static final String WORKOUT_FILE_CREATED_MSG = "- The workout file 'workouts.txt' has been created in\n"
            + "  the WerkIt! resource directory.";
    public static final String PLAN_FILE_NOT_FOUND_MSG = "- The plan file was not found. It will be created.";
    public static final String PLAN_FILE_CREATED_MSG = "- The plan file 'plans.txt' has been created in\n"
            + "  the WerkIt! resource directory.";
    public static final String SCHEDULE_FILE_NOT_FOUND_MSG = "- The schedule file was not found. It will be created.";
    public static final String SCHEDULE_FILE_CREATED_MSG = "- The schedule file 'schedule.txt' has been\n"
            + "  created in the WerkIt! resource directory.";
    public static final String FILE_LOAD_OK = "OK!";
    public static final String FILE_LOAD_NOT_OK = "Not OK...";
    public static final String LOADING_FILE_DATA_MSG = "Loading saved file data...";
    public static final String EXERCISES_FILE_LOADED_MSG =  "- Exercises file\t%s\n";
    public static final String WORKOUTS_FILE_LOADED_MSG = "- Workouts file \t%s\n";
    public static final String PLANS_FILE_LOADED_MSG = "- Plans file \t\t%s\n";
    public static final String SCHEDULE_FILE_LOADED_MSG = "- Schedule file \t%s\n";
    // Workout-related messages
    public static final String NEW_WORKOUT_CREATED_MESSAGE = "Alright, the following workout has been created:";
    public static final String DELETED_WORKOUT_MESSAGE = "Alright, the following workout has been removed:";
    public static final String UPDATED_WORKOUT_MESSAGE = "Alright, the following workout has been updated to:";
    //Plan-related Message
    public static final String NEW_PLAN_CREATED_MESSAGE = "Alright, the following plan has been created:";
    public static final String DELETED_PLAN_MESSAGE = "Alright, the following plan has been removed:";
    public static final String NEW_DAY_CREATED_MESSAGE = "Alright, the following plan schedule has been created:";

    // IOException Error Message
    public static final String IOEXCEPTION_ERROR_MESSAGE = "[ERROR] The program has encountered an IOException "
            + "and needs to close. Sorry about that...";

    // Scanner object for reading in user input from standard input
    Scanner inputReader = new Scanner(System.in);

    /**
     * Constructs an instance of the UI class. The constructor will link the Logger object in this UI class
     * to the log file as specified in the LogHandler class.
     */
    public UI() {
        LogHandler.linkToFileLogger(logger);
    }

    /**
     * Prints a line on the console based on the default parameters defined in this Java class.
     * Source: Team Member Alan Low's iP codebase
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     */
    public void printLine() {
        // Prints a line based on the default parameters
        printLine(DEFAULT_LINE_LENGTH, DEFAULT_LINE_CHAR);
    }

    /**
     * Prints a line on the console based on the specified length and the character/symbol to use
     * Source: Team Member Alan Low's iP codebase
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     *
     * @param lineLen The length of the line (measured in 'number of characters').
     * @param character The character that should be used to print the line.
     */
    public void printLine(int lineLen, String character) {
        // Generate a line of whitespaces
        String lineOfWhitespaces = String.format("%" + lineLen + "s", " ");

        // Replace each whitespace with the specified character
        String lineOfChars = lineOfWhitespaces.replaceAll(" ", character);

        // Finally, print out the line of characters
        System.out.println(lineOfChars);
    }

    /**
     * Prints the greetings when the user starts the WerkIt! application. The greeting includes the
     * banner logo as well as a welcome message.
     */
    public void printGreetings() {
        printLine(DEFAULT_LINE_LENGTH, "=");
        System.out.println(WERKIT_BANNER_LOGO);
        System.out.println(WELCOME_MESSAGE);
        printLine();
    }

    /**
     * Prints the message that will greet the user farewell when the user exits the application.
     */
    public void printGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
        printLine(DEFAULT_LINE_LENGTH, "=");
    }

    /**
     * Prints a message that prompts the user to enter a command
     * Method adapted from Team Member Alan Low's iP codebase.
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     *
     * @param isFirstPrompt state whether the input taken from the console is the first command given by user.
     */
    public void printUserInputPrompt(boolean isFirstPrompt) {
        printLine();
        if (isFirstPrompt) {
            System.out.println("Now then, what can I do for you today?");
            System.out.println("(Need help? Type 'help' for a guide!)");
        } else {
            System.out.println("What's next?");
        }

        printLine();
        System.out.print(PROMPT_SYMBOL + " ");
    }

    /**
     * Reads in a line of the user from the standard input, trims the input, and returns it as a String.
     * Method adapted from Team Member Alan Low's iP codebase.
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraParser.java
     *
     * @return userInputTrimmed user input with no spaces at the back of the input.
     */
    protected String getUserInput() {
        String userInput = inputReader.nextLine();
        String userInputTrimmed = userInput.trim();
        printLine();

        return userInputTrimmed;
    }

    /**
     * Prints a message when a new workout has been created. The newly created workout will also be displayed.
     *
     * @param newWorkout The Workout object that is newly created.
     */
    public void printNewWorkoutCreatedMessage(Workout newWorkout) {
        System.out.println(NEW_WORKOUT_CREATED_MESSAGE);
        System.out.println();
        System.out.println("\t" + newWorkout.toString());
        System.out.println();
    }

    /**
     * Prints a message when a workout has been deleted. Deleted workout will also be displayed.
     *
     * @param deletedWorkout The workout to be deleted.
     */
    public void printDeleteWorkoutMessage(Workout deletedWorkout) {
        System.out.println(DELETED_WORKOUT_MESSAGE);
        System.out.println();
        System.out.println("\t" + deletedWorkout.toString());
        System.out.println();
    }

    /**
     * Prints a message when a workout has been updated.
     * Shows updated workout.
     *
     * @param updatedWorkout The workout that is updated.
     */
    public void printUpdateWorkoutMessage(Workout updatedWorkout) {
        System.out.println(UPDATED_WORKOUT_MESSAGE);
        System.out.println();
        System.out.println("\t" + updatedWorkout.toString());
        System.out.println();
    }

    /**
     * Prints the text in colors pre-defined in TextColor class.
     * @param color ANSI color codes defined in TextColor class ONLY.
     * @param text The string text that needs to be colored.
     */
    public void printColorText(String color, String text) {
        System.out.println(color + text + TextColor.COLOR_RESET);
    }

    /**
     * Formats the string to contain the ANSI color code specified.
     *
     * @param color The color to format the string into.
     * @param text The text to be formatted with a color.
     * @return The string formatted with the ANSI color code.
     */
    public String getColorText(String color, String text) {
        String textWithColor = color + text + TextColor.COLOR_RESET;
        return textWithColor;
    }

    /**
     * Prints the help messages. To be updated.
     */
    public void printHelpMessage() {
        printExerciseHelp();
        printLine();
        printLine();
        printWorkoutHelp();
        printLine();
        printLine();
        printPlanHelp();
        printLine();
        printLine();
        printScheduleHelp();
        printLine();
        printLine();
        printSearchHelp();
        printLine();
        printLine();
        printExitHelp();
    }

    /**
     * Prints help message for 'exercise /list' command.
     */
    public void printExerciseListHelp() {
        System.out.println("\t To view all exercises available, please enter:");
        printColorText(TextColor.COLOR_YELLOW, "\t exercise /list");
        System.out.println("\t This will print all the exercises available.");
    }

    /**
     * Prints help message for 'list' command.
     */
    public void printWorkoutListHelp() {
        System.out.println("\t To view all workouts, please enter:");
        printColorText(TextColor.COLOR_YELLOW, "\t workout /list");
        System.out.println("\t This will print all the existing workouts.");
    }

    /**
     * Prints help message for 'workout /new' command.
     */
    public void printWorkoutAddHelp() {
        System.out.println("\t To add a workout, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t workout /new <exercise name> /reps <no. of repetitions>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t workout /new push up /reps 10");
        System.out.println("\t This will add a workout with " + getColorText(TextColor.COLOR_YELLOW, "10 reps")
                + " of " + getColorText(TextColor.COLOR_YELLOW, "push up"));
    }

    /**
     * Prints help message for 'workout /delete' command.
     */
    public void printWorkoutDeleteHelp() {
        System.out.println("\t To delete a workout, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t workout /delete <index of workout>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t workout /delete 1");
        System.out.println("\t This will delete the workout with index "
                + getColorText(TextColor.COLOR_YELLOW, "1") + " if exists.");
    }

    /**
     * Prints help message for 'workout /update' command.
     */
    public void printWorkoutUpdateHelp() {
        System.out.println("\t To update a workout, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t workout /update <index of workout> <quantity>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t workout /update 1 15");
        System.out.println("\t This will update the workout with index "
                + getColorText(TextColor.COLOR_YELLOW, "1") + " to "
                + getColorText(TextColor.COLOR_YELLOW, "15 reps") + " if exists.");
    }

    /**
     * Prints help message for 'exit' command.
     */
    public void printExitHelp() {
        System.out.println("\t To exit werkIt, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t exit");
        System.out.println("\t This will exit werkIt.");
    }

    /**
     * Prints a message that tells the user that the saved file data is being loaded
     * into the application.
     */
    public void printLoadingFileDataMessage() {
        System.out.println(LOADING_FILE_DATA_MSG);
    }

    /**
     * Prints the result of the file load process.
     *
     * @param filename         The name of the file that is loaded.
     * @param isLoadSuccessful Whether the file load is successful or not.
     * @throws UnknownFileException If the filename specified is unknown.
     */
    public void printFileLoadStatusMessage(String filename, boolean isLoadSuccessful) throws UnknownFileException {
        String statusMessage;
        if (isLoadSuccessful) {
            statusMessage = getColorText(TextColor.COLOR_GREEN, FILE_LOAD_OK);
        } else {
            statusMessage = getColorText(TextColor.COLOR_RED, FILE_LOAD_NOT_OK);
        }

        String messageToPrint;
        switch (filename) {
        case FileManager.EXERCISE_FILENAME:
            messageToPrint = EXERCISES_FILE_LOADED_MSG;
            break;
        case FileManager.WORKOUT_FILENAME:
            messageToPrint = WORKOUTS_FILE_LOADED_MSG;
            break;
        case FileManager.PLAN_FILENAME:
            messageToPrint = PLANS_FILE_LOADED_MSG;
            break;
        case FileManager.SCHEDULE_FILENAME:
            messageToPrint = SCHEDULE_FILE_LOADED_MSG;
            break;
        default:
            logger.log(Level.WARNING, "Unknown filename encountered.");
            throw new UnknownFileException(filename, UnknownFileException.UNKNOWN_FILE_MSG);
        }

        System.out.printf(messageToPrint, statusMessage);
    }

    /**
     * Prints a message to tell the user that the application is currently checking whether the required
     * directory and files exist in the user's system.
     */
    public void printCheckingDirectoryAndFilesMessage() {
        System.out.print(CHECK_DIRECTORY_AND_FILES_MSG);
    }

    /**
     * Prints a message to tell the user that the required directory is not found and a new one
     * will be created.
     */
    public void printDirectoryNotFoundMessage() {
        System.out.println();
        System.out.println(DIRECTORY_NOT_FOUND_MSG);
    }

    /**
     * Prints a message to tell the user that the required directory has been successfully created.
     */
    public void printDirectoryCreatedMessage() {
        System.out.println(DIRECTORY_CREATED_MSG);
    }

    /**
     * Prints a message to tell the user that the exercise file is not found on the user's system
     * and a new one will be created.
     */
    public void printExerciseFileNotFoundMessage() {
        System.out.println();
        System.out.println(EXERCISE_FILE_NOT_FOUND_MSG);
    }

    /**
     * Prints a message to tell the user that the exercise file has been successfully created.
     */
    public void printExerciseFileCreatedMessage() {
        System.out.println(EXERCISE_FILE_CREATED_MSG);
    }

    /**
     * Prints a message to tell the user that the workout file is not found on the user's system
     * and a new one will be created.
     */
    public void printWorkoutFileNotFoundMessage() {
        System.out.println();
        System.out.println(WORKOUT_FILE_NOT_FOUND_MSG);
    }

    /**
     * Prints a message to tell the user that the workout file has been successfully created.
     */
    public void printWorkoutFileCreatedMessage() {
        System.out.println(WORKOUT_FILE_CREATED_MSG);
    }

    public void printPlanFileNotFoundMessage() {
        System.out.println();
        System.out.println(PLAN_FILE_NOT_FOUND_MSG);
    }

    public void printPlanFileCreatedMessage() {
        System.out.println(PLAN_FILE_CREATED_MSG);
    }

    /**
     * Prints a message to tell the user that the schedule file is not found on the user's system
     * and a new one will be created.
     */
    public void printScheduleFileNotFoundMessage() {
        System.out.println();
        System.out.println(SCHEDULE_FILE_NOT_FOUND_MSG);
    }

    /**
     * Prints a message to tell the user that the schedule file has been successfully created.
     */
    public void printScheduleFileCreatedMessage() {
        System.out.println(SCHEDULE_FILE_CREATED_MSG);
    }

    /**
     * Prints either the status or nothing depending on the presence of the required directory and
     * files on the user's system. If all the required directory and files are present, a status
     * will be printed. Otherwise, an empty line will be printed.
     *
     * @param areAllDirectoryAndFilesPresent A boolean indicating if the required directory and
     *                                       files are present.
     */
    public void printEmptyLineOrStatus(boolean areAllDirectoryAndFilesPresent) {
        if (areAllDirectoryAndFilesPresent) {
            String status = getColorText(TextColor.COLOR_GREEN, ALL_DIRECTORY_AND_FILES_PRESENT);
            System.out.println(" " + status);
            System.out.println();
        } else {
            System.out.println();
        }
    }

    /**
     * Prints a message when a new plan has been created. The newly created plan will also be displayed.
     *
     * @param newPlan The Plan object that is newly created.
     */
    public void printNewPlanCreatedMessage(Plan newPlan) {
        System.out.println(NEW_PLAN_CREATED_MESSAGE);
        System.out.println();
        System.out.println("\t" + newPlan.toString());
        System.out.println();
    }

    /**
     * Prints a message when a plan has been deleted and shows the deleted workout.
     *
     * @param deletedPlan The plan to be deleted.
     */
    public void printDeletePlanMessage(Plan deletedPlan) {
        System.out.println(DELETED_PLAN_MESSAGE);
        System.out.println();
        System.out.println("\t" + deletedPlan.toString());
        System.out.println();
    }

    /**
     * Prints a message when a new day schedule has been created. The newly created schedule will also be displayed.
     *
     * @param newDay The Day object that is newly created.
     */
    public void printNewScheduleCreatedMessage(Day newDay) {
        System.out.println(NEW_DAY_CREATED_MESSAGE);
        System.out.println();
        System.out.println("\t" + newDay.toString());
        System.out.println();
    }

    /**
     * Prints a message when all day schedule had been cleared.
     */
    public void printClearedScheduleMessage() {
        System.out.println("Schedule had been cleared and reset.");
        System.out.println("There is no plan scheduled for any day.");
        System.out.println("To add plan for any day, enter:");
        printColorText(TextColor.COLOR_YELLOW, "schedule /update <day number [1-7]> <plan number>");
    }

    /**
     * Prints message when no matching result is found under search command.
     *
     * @param category The category that will be searched.
     */
    public void printSearchNotFoundMessage(String category) {
        System.out.println("Sorry, no matching " + category + " found.");
    }

    /**
     * Prints help message for 'plan /new' command.
     */
    public void printPlanAddHelp() {
        System.out.println("\t To add a plan, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t plan /new <plan name> /workouts"
                + " <workout index(s) separated by ','>");
        System.out.println("\t Example: ");
        printWorkoutExample();
        printPlanAddExample();
    }

    /**
     * Prints workout list examples.
     */
    public void printWorkoutExample() {
        System.out.println("\t The workout list upon entering "
                + getColorText(TextColor.COLOR_YELLOW, "workout /list")
                + ", contains the\n \t following workouts:");
        printColorText(TextColor.COLOR_YELLOW, "\t 1. push up (10 reps)");
        printColorText(TextColor.COLOR_YELLOW, "\t 2. sit up (10 reps)");
        printColorText(TextColor.COLOR_YELLOW, "\t 3. pull up (10 reps)");
    }

    /**
     * Prints the example of adding plans.
     */
    public void printPlanAddExample() {
        System.out.println("\t To create a new plan, enter the following command:");
        printColorText(TextColor.COLOR_YELLOW, "\t plan /new Grow My Muscles /workouts 1, 2");
        System.out.println("\t A new plan named " + getColorText(TextColor.COLOR_YELLOW, "Grow My Muscles")
                + " with workout index " + getColorText(TextColor.COLOR_YELLOW, "1") + " and "
                + getColorText(TextColor.COLOR_YELLOW, "2\n")
                + "\t will be created and added to the application's list of plans.");
    }

    /**
     * Prints the example plan list to be used in schedule update help message.
     */
    public void printPlanExample() {
        System.out.println("\t The plan list upon entering "
                + getColorText(TextColor.COLOR_YELLOW, "plan /list") + ", contains the\n \t following plans:");
        printColorText(TextColor.COLOR_YELLOW, "\t 1. arms");
        printColorText(TextColor.COLOR_YELLOW, "\t 2. stronger arms");
        printColorText(TextColor.COLOR_YELLOW, "\t 3. Grow My Muscles");
    }

    /**
     * Prints help message for 'plan /list' command.
     */
    public void printPlanListHelp() {
        System.out.println("\t To view all plans, please enter:");
        printColorText(TextColor.COLOR_YELLOW, "\t plan /list");
        System.out.println("\t This will print all the existing plans.");
    }

    /**
     * Prints help message for 'plan /details' command.
     */
    public void printPlanDetailsHelp() {
        System.out.println("\t To view each plan in detail, please enter:");
        printColorText(TextColor.COLOR_YELLOW, "\t plan /details <plan index in list>");
        System.out.println("\t This will print all the workouts in the plan of given index.");
    }

    /**
     * Prints help message for 'schedule /list' command.
     */
    public void printScheduleListHelp() {
        System.out.println("\t To view the schedule, please enter:");
        printColorText(TextColor.COLOR_YELLOW, "\t schedule /list");
        System.out.println("\t This will print the schedule.");
    }

    /**
     * Prints help message for 'schedule /update' command.
     */
    public void printScheduleUpdateHelp() {
        System.out.println("\t To update schedule, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t schedule /update <day index [1-7]> <plan index in the list>");
        System.out.println("\t Example: ");
        printPlanExample();
        System.out.println("\t To update the plan schedule for Monday with plan 1,"
                + " enter the\n \t following command:");
        printColorText(TextColor.COLOR_YELLOW, "\t schedule /update 1 1");
        System.out.println("\t This will update the schedule of "
                + getColorText(TextColor.COLOR_YELLOW, "Monday")
                + " with a plan named " + getColorText(TextColor.COLOR_YELLOW, "arms") + ".");
    }

    /**
     * Prints help message for 'schedule /clear' and 'schedule /clearall' commands.
     */
    public void printScheduleClearHelp() {
        System.out.println("\t To clear schedule, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t schedule /clear <day index [1-7]>");
        System.out.println("\t This will clear the schedule for the given day.");
        printColorText(TextColor.COLOR_YELLOW, "\t schedule /clearall");
        System.out.println("\t This will clear the schedule for everyday.");
    }

    /**
     * Prints help message for 'plan /delete' command.
     */
    public void printPlanDeleteHelp() {
        System.out.println("\t To delete a plan, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t plan /delete <index of plan>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t plan /delete 1");
        System.out.println("\t This will delete the plan with index "
                + getColorText(TextColor.COLOR_YELLOW, "1") + " if exists.");
    }

    /**
     * Prints help message for 'search /exercise' command.
     */
    public void printSearchExerciseHelp() {
        System.out.println("\t To search for exercise(s), please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /exercise <exercise keyword>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /exercise up");
        System.out.println("\t This will show the exercise(s) containing keyword "
                + getColorText(TextColor.COLOR_YELLOW, "up") + " if exist.");
    }

    /**
     * Prints help message for 'search /workout' command.
     */
    public void printSearchWorkoutHelp() {
        System.out.println("\t To search for workout(s), please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /workout <exercise keyword or number of reps>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /workout up");
        System.out.println("\t This will show the workout(s) containing exercise with keyword\n\t "
                + getColorText(TextColor.COLOR_YELLOW, "up") + " if exist.");
        printColorText(TextColor.COLOR_YELLOW, "\t search /workout 15");
        System.out.println("\t This will show the workout(s) with "
                + getColorText(TextColor.COLOR_YELLOW, "reps = 15") + " if exist.");
    }

    /**
     * Prints help message for 'search /plan' command.
     */
    public void printSearchPlanHelp() {
        System.out.println("\t To search for plan(s), please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /plan <plan keyword>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /plan grow");
        System.out.println("\t This will show the plan(s) containing keyword "
                + getColorText(TextColor.COLOR_YELLOW, "grow") + " if exist.");
    }

    /**
     * Prints help message for 'search /all' command.
     */
    public void printSearchAllHelp() {
        System.out.println("\t To search for everything related to the keyword, please enter: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /all <keyword>");
        System.out.println("\t Example: ");
        printColorText(TextColor.COLOR_YELLOW, "\t search /all a");
        System.out.println("\t This will show the exercise(s), workout(s) and plan(s) containing\n\t keyword "
                + getColorText(TextColor.COLOR_YELLOW, "a") + " if exist.");
    }

    /**
     * Prints all exercise related help messages.
     */
    public void printExerciseHelp() {
        printExerciseListHelp();
    }

    /**
     * Prints all workout related help messages.
     */
    public void printWorkoutHelp() {
        printWorkoutListHelp();
        printLine();
        printWorkoutAddHelp();
        printLine();
        printWorkoutDeleteHelp();
        printLine();
        printWorkoutUpdateHelp();
    }

    /**
     * Prints all plan related help messages.
     */
    public void printPlanHelp() {
        printPlanListHelp();
        printLine();
        printPlanDetailsHelp();
        printLine();
        printPlanAddHelp();
        printLine();
        printPlanDeleteHelp();
    }

    /**
     * Prints all schedule related help messages.
     */
    public void printScheduleHelp() {
        printScheduleListHelp();
        printLine();
        printScheduleUpdateHelp();
        printLine();
        printScheduleClearHelp();
    }

    /**
     * Prints all search related help messages.
     */
    public void printSearchHelp() {
        printSearchExerciseHelp();
        printLine();
        printSearchWorkoutHelp();
        printLine();
        printSearchPlanHelp();
        printLine();
        printSearchAllHelp();
    }
}
