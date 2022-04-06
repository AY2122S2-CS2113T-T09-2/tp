package data.plans;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.workouts.InvalidWorkoutException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.LogHandler;
import textcolors.TextColor;
import werkit.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlanListTest {
    ExerciseList exerciseList;
    WorkoutList workoutList;
    PlanList planList;
    UI ui;

    @BeforeEach
    void setUp() throws InvalidWorkoutException, InvalidExerciseException {
        LogHandler.startLogHandler();
        exerciseList = new ExerciseList();
        workoutList = new WorkoutList(exerciseList);
        planList = new PlanList(workoutList);
        ui = new UI();

        exerciseList.addExerciseToList("push up");
        exerciseList.addExerciseToList("pull up");
        exerciseList.addExerciseToList("sit up");
        exerciseList.addExerciseToList("burpee");
        exerciseList.addExerciseToList("russian twist");

        workoutList.createAndAddWorkout("push up /reps 10");
        workoutList.createAndAddWorkout("sit up /reps 15");
        workoutList.createAndAddWorkout("burpee /reps 20");
        workoutList.createAndAddWorkout("burpee /reps 50");
        workoutList.createAndAddWorkout("pull up /reps 10");
    }

    @Test
    void listAllPlan_expectNoPlanPrints() {
        String expectedOutput =
                "Oops! You have not created any plans yet!"
                        + "\nTo create a new plan, enter 'plan /new <plan name> /workouts "
                        + "\n<workout number(s) to add, separated by comma>'."
                        + "\nAlternatively, enter 'help' for more information.";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        planList.listAllPlan();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void listAllPlan_expectThreePrints() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,2,3");
        planList.createAndAddPlan("Plan 2 /workouts 1");
        planList.createAndAddPlan("Plan 3 /workouts 1,5,4,3");
        String expectedOutput =
                "Here are all your plan(s).\n"
                        + "To view each plan in detail, enter\n'plan /details <plan number in list>'.\n"
                        + "1. plan 1\n"
                        + "2. plan 2\n"
                        + "3. plan 3\n";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        planList.listAllPlan();
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);
    }

    @Test
    void createAndAddPlan_validPlansAdded_expectValidPlans() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,5,4,3");
        planList.createAndAddPlan("Plan 2 /workouts 1,5,4,3,1,1,1");
        planList.createAndAddPlan("Plan 3 /workouts 1, 3, 4");
        planList.createAndAddPlan("Plan 4 /workouts 1,1, 5,2");

        for (int i = 0; i < planList.getPlansDisplayList().size(); i += 1) {
            assertEquals("plan " + (i + 1), planList.getPlansDisplayList().get(i).toString());
        }
    }

    @Test
    void createAndAddPlan_invalidPlanName_expectInvalidPlanException() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,2,3");
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Plan 1 /workouts 2,2,2"));
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("rest day /workouts 3,2,3"));
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("REST DAY       /workouts 3,2,3,1"));
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("        /workouts 3,2,3,1,1"));
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Exceed the 30 characters limit yes /workouts 1,2,1"));
    }

    @Test
    void createAndAddPlan_minMaxWorkoutsToAdd_expectException() {
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Exceed 10 Plan /workouts 1,1,1,1,1,2,2,2,2,2,3"));
        assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> planList.createAndAddPlan("Min 1 Plan /workouts"));
    }

    @Test
    void createAndAddPlan_workoutIndexOutOfRange_expectInvalidPlanException() {
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Plan 1 /workouts 1,2,3,9"));
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Plan 2 /workouts 9"));
    }

    @Test
    void createAndAddPlan_workoutWithSameSequence_expectInvalidPlanException() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,2,3");
        assertThrows(InvalidPlanException.class,
            () -> planList.createAndAddPlan("Duplicate Sequence /workouts 1,2,3"));
    }

    @Test
    void listPlanDetails_validPlanNumber_expectThreeWorkouts() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,2,3");
        String expectedOutput =
                "Here are the 3 workouts in [" + ui.getColorText(TextColor.COLOR_YELLOW, "plan 1")
                        + "].\n"
                        + "1. push up (10 reps)\n"
                        + "2. sit up (15 reps)\n"
                        + "3. burpee (20 reps)\n";
        expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        planList.listPlanDetails("1", ui);
        System.out.flush();
        String consoleOutputs = consoleOutput.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expectedOutput, consoleOutputs);

    }

    @Test
    void listPlanDetails_planNumberOutOfRange_expectInvalidPlanException() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,5,4,3");
        planList.createAndAddPlan("Plan 2 /workouts 1,5,4,3,1,1,1");
        planList.createAndAddPlan("Plan 3 /workouts 1, 3, 4");

        String planNumber = "5";
        assertThrows(InvalidPlanException.class,
            () -> planList.listPlanDetails(planNumber, ui));

    }

    @Test
    void listPlanDetails_inputArgumentIsNotANumber_expectNumberFormatException() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,5,4,3");
        planList.createAndAddPlan("Plan 2 /workouts 1,5,4,3,1,1,1");
        planList.createAndAddPlan("Plan 3 /workouts 1, 3, 4");

        String invalidArgument = "3a";
        assertThrows(NumberFormatException.class,
            () -> planList.listPlanDetails(invalidArgument, ui));

    }

    @Test
    void deletePlan_validIndexToDelete_expectDeletePlanMessage() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,5,4,3");
        planList.createAndAddPlan("Plan 2 /workouts 1,5,4,3,1,1,1");
        planList.createAndAddPlan("Plan 3 /workouts 1, 3, 4");

        int planNumberToDelete = 2;

        assertEquals("plan 2", planList.getPlansDisplayList().get(planNumberToDelete - 1).toString());
        planList.deletePlan(Integer.toString(planNumberToDelete));
        assertEquals("plan 3", planList.getPlansDisplayList().get(planNumberToDelete - 1).toString());
    }

    @Test
    void deletePlan_indexOutOfRange_expectInvalidPlanException() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,5,4,3");
        planList.createAndAddPlan("Plan 2 /workouts 1,5,4,3,1,1,1");
        planList.createAndAddPlan("Plan 3 /workouts 1, 3, 4");

        String planNumberToDelete = "5";
        assertThrows(InvalidPlanException.class,
            () -> planList.deletePlan(planNumberToDelete));

    }

    @Test
    void deleteWorkout_inputArgumentIsNotANumber_expectNumberFormatException() throws InvalidPlanException {
        planList.createAndAddPlan("Plan 1 /workouts 1,5,4,3");
        planList.createAndAddPlan("Plan 2 /workouts 1,5,4,3,1,1,1");
        planList.createAndAddPlan("Plan 3 /workouts 1, 3, 4");

        String invalidArgument = "3a";
        assertThrows(NumberFormatException.class,
            () -> planList.deletePlan(invalidArgument));

    }

}