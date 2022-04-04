package commands;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.InvalidPlanException;
import data.plans.PlanList;
import data.workouts.InvalidWorkoutException;
import data.workouts.Workout;
import data.workouts.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.FileManager;
import storage.LogHandler;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkoutCommandTest {
    @BeforeEach
    public void setUp() {
        LogHandler.startLogHandler();
    }

    @Test
    public void workoutCommand_normalWorkoutNewConstruction_constructSuccess() throws
            InvalidCommandException {
        String userInput = "workout /new russian twist /reps 1000";
        String userAction = "/new";
        String userArguments = "russian twist /reps 1000";
        ExerciseList el = new ExerciseList();
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);

        WorkoutCommand commandTest = new WorkoutCommand(userInput, fm, wl, pl, userAction, userArguments);

        assertEquals("workout /new russian twist /reps 1000", commandTest.getUserInput());
        assertEquals("/new", commandTest.getUserAction());
        assertEquals("russian twist /reps 1000", commandTest.getUserArguments());
    }

    @Test
    public void workoutCommand_invalidAction_exceptionThrown() {
        String userInput1 = "workout /pancakes";
        String userAction1 = "/pancakes";
        String userArguments1 = null;

        String userInput2 = "workout /create running /reps 20";
        String userAction2 = "/create";
        String userArguments2 = "running /reps 20";

        ExerciseList el = new ExerciseList();
        WorkoutList wl = new WorkoutList(el);
        PlanList pl = new PlanList(wl);
        FileManager fm = new FileManager(pl);

        assertThrows(InvalidCommandException.class,
            () -> new WorkoutCommand(userInput1, fm, wl, pl, userAction1, userArguments1));
        assertThrows(InvalidCommandException.class,
            () -> new WorkoutCommand(userInput2, fm, wl, pl, userAction2, userArguments2));
    }

    @Test
    public void workoutCommand_deleteWorkout_noPlanDeleted() throws InvalidWorkoutException,
            InvalidExerciseException, InvalidPlanException, InvalidCommandException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("push up");
        el.addExerciseToList("sit up");

        WorkoutList wl = new WorkoutList(el);
        wl.createAndAddWorkout("push up /reps 10");
        wl.createAndAddWorkout("push up /reps 15");
        wl.createAndAddWorkout("sit up /reps 10");

        PlanList pl = new PlanList(wl);
        pl.createAndAddPlan("Plan 1 /workouts 1,1,2,2");
        pl.createAndAddPlan("Plan 2 /workouts 1, 1, 1");

        FileManager fm = new FileManager(pl);

        String userInput = "workout /delete 3";
        String userAction = "/delete";
        String userArguments = "3";

        int totalPlanNumberBeforeDelete = pl.getPlansDisplayList().size();

        WorkoutCommand commandTest = new WorkoutCommand(userInput, fm, wl, pl, userAction, userArguments);
        commandTest.execute();
        int totalPlanNumberAfterDelete = pl.getPlansDisplayList().size();

        assertEquals(totalPlanNumberBeforeDelete, totalPlanNumberAfterDelete);
    }

    @Test
    public void workoutCommand_deleteWorkout_deleteOnePlan() throws InvalidWorkoutException,
            InvalidExerciseException, InvalidPlanException, InvalidCommandException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("push up");
        el.addExerciseToList("sit up");

        WorkoutList wl = new WorkoutList(el);
        wl.createAndAddWorkout("push up /reps 10");
        wl.createAndAddWorkout("push up /reps 15");
        wl.createAndAddWorkout("sit up /reps 10");

        PlanList pl = new PlanList(wl);
        pl.createAndAddPlan("Plan 1 /workouts 1,1,2,2");
        pl.createAndAddPlan("Plan 2 /workouts 1, 2, 3");

        FileManager fm = new FileManager(pl);

        String userInput = "workout /delete 3";
        String userAction = "/delete";
        String userArguments = "3";

        int totalPlanNumberBeforeDelete = pl.getPlansDisplayList().size();

        WorkoutCommand commandTest = new WorkoutCommand(userInput, fm, wl, pl, userAction, userArguments);
        commandTest.execute();
        int totalPlanNumberAfterDelete = pl.getPlansDisplayList().size();

        assertEquals(totalPlanNumberBeforeDelete - 1, totalPlanNumberAfterDelete);
    }

    @Test
    public void workoutCommand_updateWorkout_noPlanDetailsUpdated() throws InvalidWorkoutException,
            InvalidExerciseException, InvalidPlanException, InvalidCommandException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("push up");
        el.addExerciseToList("pull up");
        el.addExerciseToList("sit up");

        WorkoutList wl = new WorkoutList(el);
        wl.createAndAddWorkout("push up /reps 15");
        wl.createAndAddWorkout("pull up /reps 15");
        wl.createAndAddWorkout("sit up /reps 10");

        PlanList pl = new PlanList(wl);
        pl.createAndAddPlan("Plan 1 /workouts 1, 2");

        FileManager fm = new FileManager(pl);

        String userInput = "workout /update 3 15";
        String userAction = "/update";
        String userArguments = "3 15";

        ArrayList<Workout> oldListOfWorkouts = pl.getPlanFromIndexNum(1).getWorkoutsInPlanList();
        ArrayList<String> oldWorkoutDetails = new ArrayList<String>();
        for (int i = 0; i < oldListOfWorkouts.size(); i++) {
            oldWorkoutDetails.add(oldListOfWorkouts.get(i).toString());
        }

        WorkoutCommand commandTest = new WorkoutCommand(userInput, fm, wl, pl, userAction, userArguments);
        commandTest.execute();
        ArrayList<Workout> newWorkoutDetails = pl.getPlanFromIndexNum(1).getWorkoutsInPlanList();


        boolean isSameDetails = true;
        for (int i = 0; i < oldWorkoutDetails.size(); i++) {
            if (!oldWorkoutDetails.get(i).equals(newWorkoutDetails.get(i).toString())) {
                isSameDetails = false;
                break;
            }
        }

        assertEquals(true, isSameDetails);
    }

    @Test
    public void workoutCommand_updateWorkout_onePlanDetailsUpdated() throws InvalidWorkoutException,
            InvalidExerciseException, InvalidPlanException, InvalidCommandException {
        ExerciseList el = new ExerciseList();
        el.addExerciseToList("push up");
        el.addExerciseToList("pull up");
        el.addExerciseToList("sit up");

        WorkoutList wl = new WorkoutList(el);
        wl.createAndAddWorkout("push up /reps 15");
        wl.createAndAddWorkout("pull up /reps 15");
        wl.createAndAddWorkout("sit up /reps 10");

        PlanList pl = new PlanList(wl);
        pl.createAndAddPlan("Plan 1 /workouts 1, 2, 3");

        FileManager fm = new FileManager(pl);

        String userInput = "workout /update 3 20";
        String userAction = "/update";
        String userArguments = "3 20";

        ArrayList<Workout> oldListOfWorkouts = pl.getPlanFromIndexNum(1).getWorkoutsInPlanList();
        ArrayList<String> oldWorkoutDetails = new ArrayList<String>();
        for (int i = 0; i < oldListOfWorkouts.size(); i++) {
            oldWorkoutDetails.add(oldListOfWorkouts.get(i).toString());
        }

        WorkoutCommand commandTest = new WorkoutCommand(userInput, fm, wl, pl, userAction, userArguments);
        commandTest.execute();
        ArrayList<Workout> newWorkoutDetails = pl.getPlanFromIndexNum(1).getWorkoutsInPlanList();


        boolean isSameDetails = true;
        for (int i = 0; i < oldWorkoutDetails.size(); i++) {
            if (!oldWorkoutDetails.get(i).equals(newWorkoutDetails.get(i).toString())) {
                isSameDetails = false;
                break;
            }
        }

        assertEquals(false, isSameDetails);
    }

}
