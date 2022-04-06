# WerkIt! Developer Guide

## Table of Contents
* [About this Guide](#about-this-guide)
* [Acknowledgements](#acknowledgements)
* [Setting Up your Development Environment](#setting-up-your-development-environment)
* [Design](#design)
* [Implementation](#implementation)
* [Product Scope](#product-scope)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Instructions for Manual Testing](#instructions-for-manual-testing)

## About this Guide
This developer guide serves a documentation of the development of WerkIt!, an application that was created to help
prospective users to plan their exercise routines.

This technical document is meant for current and future developers of WerkIt! as a reference point on the design,
implementation, and other technical and non-technical aspects of the application.

### Notations Used In This Guide
When reading this document, there are several icons that you may encounter. 
Below are the icons and their meanings:


<span class="box warning">:warning: **Warning**: Information that you may want to pay attention to in order to prevent 
possible issues from arising when using the application.</span>

<span class="box info">:memo: **Note**: Additional information that may be useful for you.</span>


## Acknowledgements
The following websites and codebases were referenced and adapted for our project:

* AddressBook-Level2 project ([Website](https://se-education.org/addressbook-level2/) | 
[GitHub](https://github.com/se-edu/addressbook-level2))
* AddressBook-Level3 project ([Website](https://se-education.org/addressbook-level3/DeveloperGuide.html) |
[GitHub](https://github.com/se-edu/addressbook-level3))
* Team Member Alan Low's individual project (iP) codebase ([GitHub](https://github.com/alanlowzies/ip))

## Setting Up your Development Environment
### Requirements
- [X] Java JDK version 11
- [X] An IDE of your choice, though IntelliJ IDEA is recommended as this project is developed
with this IDE.

<span class="box info">:memo: IDE-related references in this developer guide IDE will be tailored for IntelliJ IDEA.</span>

### Setting Up
1. Fork the [WerkIt! GitHub repository](https://github.com/AY2122S2-CS2113T-T09-2/tp).
2. Clone your fork to your machine.
3. Set up your local repo in your IDE.
    - Ensure that the project in your IDE is configured to run on Java JDK version 11. 
    - A guide on setting your project to use JDK 11 in your IntelliJ IDEA IDE can be found 
[here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
4. Run `Main.java`. If you have set up your environment correctly, you should see the following
output in your terminal: 

    ```
    ======================================================================
     __        __        _    ___ _   _ 
     \ \      / /__ _ __| | _|_ _| |_| |
      \ \ /\ / / _ \ '__| |/ /| || __| |
       \ V  V /  __/ |  |   < | || |_|_|
        \_/\_/ \___|_|  |_|\_\___|\__(_)

    Welcome to WerkIt!, your personal exercise planner.
    ----------------------------------------------------------------------
    Checking for required directory and files...
    - The required data directory was not found. It will be created.
    - The WerkIt! resource directory has been created in
      your terminal's current working directory.

    - The exercise file was not found. It will be created.
    - The exercise file 'exercises.txt' has been created in
      the WerkIt! resource directory.

    - The workout file was not found. It will be created.
    - The workout file 'workouts.txt' has been created in
      the WerkIt! resource directory.

    - The plan file was not found. It will be created.
    - The plan file 'plans.txt' has been created in
      the WerkIt! resource directory.

    - The schedule file was not found. It will be created.
    - The schedule file 'schedule.txt' has been
      created in the WerkIt! resource directory.

    Loading saved file data...
    - Exercises file	OK!
    ----------------------------------------------------------------------
    Now then, what can I do for you today?
    (Need help? Type 'help' for a guide!)
    ----------------------------------------------------------------------
    >
    ```
5. Type `exit` to exit the program.

You are now ready to begin developing!

## Design 
### Architecture Overview

![Architecture-Diagram](high-level-diagram/architecture_diagram.png)

The architecture diagram above shows the high-level design of the application.
Given below is a quick overview of the main components of the application
and their interactions.

#### Main components of the architecture
- `Main`: The main component that starts the application upon launch of the applicaiton.
- `WerkIt`: Initializes other components in the correct sequence, and connects them up with each other.
- `Storage`: Reads data from, and writes data to the user's local storage.
- `UI`: The UI of the application that deals with interaction with the user.
- `Parser`: Parses user input to make sense of the command supplied by the user.
- `Logic`: Executes the intended command of the user.

#### How the components interact with each other
[Sequence diagram]

### Component Overview

#### Storage component
[Writeup]
#### UI component

UI component consists of a single [UI class](https://github.com/AY2122S2-CS2113T-T09-2/tp/blob/master/src/main/java/werkit/UI.java)
which manages interaction (prompting for user input and displaying results
of commands/methods being called) between the user and the application.

How the UI class works:
* Upon the initialization of `WerkIt`, the `UI class` will called the `printGreetings()` method to display the greeting messages to
  the user.
* Additionally, local files storing the data of previous session of `WerkIt` will also be loaded into the program. Hence,
  the loading status of these files are also display by calling the `printFileLoadStatusMessage()` method.
* `UI class` is also responsible for getting the user input by calling the `getUserInput(String filename, boolean isLoadSuccessful)`
  method, which will then parse the input by sending it to the `Parser class`. The `Paser class` will process
  the input and call other relevant methods to execute the command.
* `UI class` also display messages when a
    * workout has been successfully created, updated and deleted.
    * plan has been successfully created and deleted.
    * schedule has been successfully created and removed.
* Help messages are also printed in the UI class by calling `printHelpMessage()` method.
* Lastly, when the user exits the program, the `printGoodBye()` method will be called to indicate that the 
user has successfully exited the program. 

#### Parser component
[Writeup]
#### Logic component
Below is a class diagram of `Logic` component:
![LogicUML](uml/classDiagrams/images/logicComponent.png)
<span class="box info">:memo: This is a high level overview of the `Logic` component, thus,
other components have been omitted from the diagram above.</span>

The `Logic` component consists of:
- `Command` abstract class. The `ExerciseCommand`, `SearchCommand`, `WorkoutCommand`, `ScheduleCommand`,
`PlanCommand`, `HelpCommand` and `ExitCommand` extends the `Command` class. These classes
identify the command action type supplied by the user and also executes the command.
The source of these classes can be found [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/tree/master/src/main/java/commands).
- The `[command name]List` classes. It includes `ExerciseList`,
`WorkoutList`,`PlanList` and `DayList`. These classes hold the methods
to perform the command action desired by the user. Examples of command actions
are create, delete, update and listing of the objects. The source of these classes
can be found [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/tree/master/src/main/java/data) 
(each class is grouped in packages according to their command name).

<br><br>
How the `Logic` component works:
<br><br>
1. The `Parser` class parses the user command and identifies the command type (e.g. plan/schedule/workout/exercise).
2. Depending on the command type, it creates the appropriate `Command` subclass object.
3. This subclass-of-`Command` object is executed by the `WerkIt` class, which calls the `execute()` method of that subclass-of-`Command` object.
4. Depending on the command action (e.g. create/delete/update/list), the `execute()` method will identify and perform the appropriate actions.

<br>
Illustration of the interactions within the `Logic` component can be found
in the sequence diagram below. The example given is for the creation of new workouts (`workout /new`):
<br><br>
![logicComponentUML](uml/sequenceDiagrams/miscellaneous/images/logicComponentSD.png)
<br><br>
<span class="box info">:memo: This is a high level overview of how the creation of workouts
is done. To improve readability, some classes and methods have been omitted from the diagram above.</span>

<br>
Each command types is a feature of the WerkIt! application.
Thus, the next section will explain the design of each
features in detail.

### Feature Overview

The features of WerkIt! are split and grouped into 5 **main** features:
1. [Exercise-related features](#exercise-related-features)
2. [Workout-related features](#workout-related-features)
3. [Plan-related features](#plan-related-features)
4. [Schedule-related features](#schedule-related-features)
5. [Search-related features](#search-related-features)

### Exercise-related features

Format: `exercise <commandAction> <condition>`

Below is a class diagram of the exercise-related features:

![ExerciseUML](uml/classDiagrams/images/exercise.png)
<br>

When WerkIt is running, the `WerkIt` class will keep prompting the user to enter command through the
`WerkIt#startContinuousUserPrompt()` method. After the user has entered command, The `UI#getUserInput()` method in `UI`
class will catch the user input, and it will be sent to `Parser#parseUserInput(String userInput)` method to analyse the
user's command. If the user's command type is `exercise`, the `Parser#parseUserInput(String userInput)` method will 
parse the 'exercise' base word and proceed to create exercise related command using 
`Parser#createExerciseCommand(String userInput)` method. This method will further evaluate the
`<commandAction>` and call the constructor of `ExerciseCommand` class by passing relevant parameters related
to the constructor. If the `<commandAction>` is null or incorrect, an `InvalidCommandException` will be thrown.

Currently, the exercise related feature is limited to `exercise /list` only. Therefore, the `condition` mentioned can
be ignored for now, and the only supported `commandAction` is `/list`. However, more exciting exercise-related features 
are expected to be delivered in future iterations, and we currently have set the framework to implement these features 
in the future. Thus, we have this standalone section specifically kept for exercise-related features.

---

### Workout-related features

Format: `workout /commandAction <condition>`

Below is a class diagram of the workout-related features:

![WorkoutUML](uml/classDiagrams/images/workoutRelatedFeatures.png)
<br>

The `Parser` class will call the `Parser#parseUserInput(userInput)` method
to analyse the user's command. If the user's command is of type 
`workout`, the `Parser#parseUserInput(userInput)` method
will parse the `workout` base word and proceed to create a `WorkoutCommand` object via the
`Parser#createWorkoutCommand(userInput)` method. 
<br><br>
Once the `WorkoutCommand` object is created, the `WorkoutCommand#execute()` method
is called. Depending on the type of command action, this method will
call the appropriate operations from the `WorkoutList` class. For instance, if the command action
is `/create`, the `WorkoutCommand#execute()` method will call `WorkoutList#createAndAddWorkout(userArgument)`
to create a new workout in the application. 
To view the details of the `WorkoutCommand#execute()`, click [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/blob/master/src/main/java/commands/WorkoutCommand.java). 
<br><br>
When all methods except the `listAllWorkout()` method is executed, the
`FileManager` and `UI` classes will call its appropriate methods depending on the command action.
From the previous example, the `/create` workout command action will call the 
`FileManager#writeNewWorkoutToFile(newWorkout)` and also the `UI#printNewCreatedMessage(newWorkout)`
methods after the new workout has been created.
<br><br>
Finally, methods in the `PlanList` class is only called when the `/delete` and `/update`
workout command actions are executed. These methods are used to modify the application's plans list
as the `/delete` and `/update` actions are cascading actions 
(i.e. deleting a workout will delete plan(s) containing that deleted workout).

---

### Plan-related features
_to be updated_

---

### Schedule-related features
![ScheduleUML](uml/classDiagrams/images/scheduleComponent.png)

Users are able to create and make changes to a 7-day workout plan schedule using the WerkIt application. For each day, users are only allowed
to schedule 1 workout plan. Click [here](#glossary) to have a better understanding of what `workout`, `plan` and `schedule`
means.

When WerkIt is running, the `WerkIt` class will keep prompting the user to enter command through the
`WerkIt#startContinuousUserPrompt()` method. After the user has entered command, the `UI#getUserInput()` method in `UI`
class will catch the user input, and it will be sent to `Parser#parseUserInput(String userInput)` method to analyse the
user's command.

If the user's command type is `schedule`, the `Parser#parseUserInput(String userInput)` method will parse the `schedule`
base word and proceed to create schedule related command using `Parser#createScheduleCommand(String userInput)` method.
The following table shows the schedule commands that WerkIt! are able to process by calling the `ScheduleCommand#execute()`
method.

| Command                                                             | `<commandAction>` | Parameters                                                                                                                                           | Method Called                               |
|---------------------------------------------------------------------|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------|
| [schedule /update `<day number>` `<plan number>`](#update-schedule) | update            | `<day number>` Number representing the day. <br/>`<plan number>` Index of the plan stored in planList. This is the plan to be scheduled for the day. | `DayList#updateDay(String userArgument)`    |
| [schedule /list](#view-schedule)                                    | list              |                                                                                                                                                      | `DayList#printSchedule() `                  |
| [schedule /clear `<day number>`](#clear-schedule-for-a-day)         | clear             | `<day number>` Number representing the day.                                                                                                          | `DayList#clearDayPlan(String userArgument)` |
| [schedule /clearall](#clear-schedule-for-the-week)                  | clearall          |                                                                                                                                                      | `DayList#clearAllSchedule()`                |
To view the details of the `ScheduleCommand#execute()`, click [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/blob/master/src/main/java/commands/ScheduleCommand.java).


The `<day number>` range from 1 to 7. The meaning of each day number is explained in the table below.

| Day Number | Meaning   |
|------------|-----------|
| 1          | Monday    |
| 2          | Tuesday   |
| 3          | Wednesday |
| 4          | Thursday  |
| 5          | Friday    |
| 6          | Saturday  |
| 7          | Sunday    |

The `ScheduleCommand#execute()` will further evaluate the `<commandAction>` and depending on the type of command action, 
this method will call the appropriate methods from the `DayList` class. If the `<commandAction>` is null or incorrect,
an `InvalidCommandException` will be thrown. If the `<parameters>` of certain commands are not specified or met, 
an `InvalidScheduleException` will be thrown.

For `commandAction` such as `/update`, `/clear` and `/clearall`, the method that was called to perform such commands will
modify the application's schedule list. Hence, appropriate methods in the `FileManager` will be called to manage the data 
and save them to the local file, `schedule.txt`. For more information on `FileManager` class, you can refer to this 
[section](#file-management).

Furthermore, when methods such as `DayList#updateDay()` and `DayList#clearAllSchedule` are being successfully executed, 
for the former method `UI#printNewScheduleCreatedMessage(Day newDay)` method will be called to display a message 
to indicate that the plan had been successfully scheduled on a day and for the latter method, 
`UI#printClearedScheduleMessage()` method will be called to display a message to indicate that the 
schedule list has successfully been reset.

---


### Search-related features

Format: `search <commandAction> <keywords>`

Below is a class diagram of the search-related features:


![SearchUML](uml/classDiagrams/images/SearchClass.png)


When WerkIt is running, the `WerkIt` class will keep prompting the user to enter command through the
`WerkIt#startContinuousUserPrompt()` method. After the user has entered command, The `UI#getUserInput()` method in `UI`
class will catch the user input, and it will be sent to `Parser#parseUserInput(String userInput)` method to analyse the
user's command. If the user's command type is search, i.e. `search <commandAction> <keywords>`, the
`Parser#parseUserInput(String userInput)` method will parse the 'search' base word and proceed to create search related
command using `Parser#createSearchCommand(String userInput)` method. This method will further evaluate the
`<commandAction>` and call the constructor of `SearchCommand` class by passing relevant parameters related to search to
the constructor. If the `<commandAction>` is null or incorrect, an `InvalidCommandException` will be thrown. If
the `<keywords>` is not specified, it will be deemed as searching for spacing.

---
## Implementation
### Overview
* [Getting User Input Continuously](#getting-user-input-continuously)
* [Parsing User Input and Getting the Right Command](#parsing-user-input-and-getting-the-right-command)
    * [Illegal Characters and Phrases](#illegal-characters-and-phrases)
* [Exercise](#exercise)
  * [List Exercise](#list-exercise)
* [Workout](#workout)
  * [Create New Workout](#create-new-workout)
    * [Design Considerations](#design-considerations-for-creating-a-new-workout)
  * [List All Workouts](#list-workout)
  * [Delete Existing Workout](#delete-existing-workout)
    * [Design Considerations](#design-considerations-for-deleting-existing-workout)
  * [Update Existing Workout](#update-existing-workout)
* [Plan](#plan)
  * [Create A New Plan](#create-a-new-plan)
  * [List Plans](#list-plans)
  * [List Workouts In A Plan](#list-workouts-in-a-plan)
  * [Delete Existing Plan](#delete-existing-plan)
* [Schedule](#schedule)
  * [Update Schedule](#update-schedule)
    * [Design Considerations](#design-considerations-for-update-schedule)
  * [View Schedule](#view-schedule)
  * [Clear plan scheduled for a day](#clear-schedule-for-a-day)
  * [Clear all plans in the Schedule](#clear-schedule-for-the-week)
* [Search](#search)
  * [Search for Exercise](#search-for-exercise)
  * [Search for Workout](#search-for-workout)
  * [Search for Plan](#search-for-plan)
  * [Search for All](#search-for-all)

---

### Getting User Input Continuously
Once `WerkIt` has finished loading any saved file data on the user's system, it will call 
`WerkIt#startContinuousUserPrompt()`. This method will call on `UI#printUserInputPrompt()` to print a prompt message
to the terminal and `UI#getUserInput()` to wait and capture the user's input. The input will be captured with the aid 
of Java's built-in `Scanner` class.

Once the user has entered an input, `UI#getUserInput()` trims any preceding and trailing whitespaces before returning 
the user's input as a `String` object to `WerkIt#startContinuousUserPrompt()`. Then, 
`WerkIt#startContinuousUserPrompt()` calls `Parser#parseUserInput()` to parse the user's input and create an 
object that is a subclass of the `Command` class. If there is no issue with the formatting of the user's input,
this subclass-of-`Command` object is returned to `WerkIt#startContinuousUserPrompt()`.

<span class="box info">:memo: A detailed implementation of the parsing and creation of subclass-of-`Command` 
object process can be found in 
'[Parsing User Input and Getting the Right Command](#parsing-user-input-and-getting-the-right-command)'.</span>

Next, `WerkIt#startContinuousUserPrompt()` calls on the `execute()` method of the subclass-of-`Command` object to
perform the user's requested action. If the execution goes smoothly, this completes the user's inputted command.
This process is repeated until the user enters `exit`, which will terminate the loop, call `UI#printGoodbye()` to
print a goodbye message to the user, before handing control back to `Main#main` to end the program.

#### Design Considerations
* `WerkIt#startContinuousUserPompt()` has a boolean flag `isFirstPrompt`. This flag allows WerkIt to
print a different prompt each time the application starts up, before defaulting to a different prompt message
for subsequent prompts.
   * When the user starts the application, `isFirstPrompt` is set to `true` and thus, the prompt will be
  ```
  ----------------------------------------------------------------------
  Now then, what can I do for you today?
  (Need help? Type 'help' for a guide!)
  ----------------------------------------------------------------------
  >
  ```
  * Subsequent prompts in that app's session will be
  ```
  ----------------------------------------------------------------------
  What's next?
  ----------------------------------------------------------------------
  >
  ```

---

### Parsing User Input and Getting the Right Command

![Obtain and Parse User Input](uml/sequenceDiagrams/miscellaneous/images/obtainAndParseUserInput.png)

<span class="box info">:memo: To improve the readability of the sequence diagram, the construction of the respective
objects which are subclasses of the `Command` class between Steps 4 and 17 are not included in the diagram.</span>

**(Steps 1 and 2)** When a user enters something into the terminal (when prompted), `UI#getUserInput()` will take in 
the user's input as a `String` and call `String#trim()` to remove leading and trailing whitespaces in the input.
Thereafter, a line is printed on the terminal to indicate that the user's input has been received
and will be processed, before returning the user input as a `String` to the calling method (i.e. 
`WerkIt#startContinuousUserPrompt()`).

**(Step 3)** In `WerkIt#startContinuousUserPrompt()`, the method will pass the obtained user string as a parameter into
`Parser#parseUserInput()`. The latter method will first check if the user input contains any characters
or symbols that are deemed as illegal (see [Illegal Characters and Phrases](#illegal-characters-and-phrases) for details).
If at least one illegal character or phrase is found, an `InvalidCommandException` will be thrown and the parsing is
aborted.

**(Steps 4 to 17)** If no illegal characters and phrases are found, `Parser#parseUserInput()` will examine the first
word in the user input. This first word should represent the command type that the user wish to execute (i.e. `exercise`,
`workout`, `plan`, `schedule`, `search`, `help`, or `exit`). Depending on the first word of the user input, different
methods will be invoked to create the appropriate object of the subclass of the `Command` abstract superclass (see the 
bulleted point after this paragraph for an example). However, if the first word is not a valid command type, an 
`InvalidCommandException` will be thrown and the parsing is aborted.
- For example, if the user input is `workout /new push up /reps 10`, `Parser#createWorkoutCommand()` will be invoked
and a `WorkoutCommand` object will be returned by this method.

Inside each of these 'create command' methods, the following generalised procedure to create an object of the subclass 
of `Command` is carried out:
1. (For commands that expect an action keyword (e.g. `/list`, `/new`)) The action keyword is parsed and determined.
    - If the action keyword is invalid, an `InvalidCommandException` is thrown and the parsing is aborted.
2. Depending on the action keyword (or lack thereof), the number of arguments are checked.
    - If insufficient or too many arguments are provided in the user input, an `InvalidCommandException` is thrown
   and the parsing is aborted.
3. A new object of the subclass of `Command` is created and if the object is successfully constructed with no errors,
it is returned to `Parser#parseUserInput()`.

**(Step 18)** The object created is then returned to `WerkIt#startContinuousUserInput()`.

<span class="box info">:memo: (About the sequence diagram) Strictly speaking, the object is returned right after whichever 
'create command' method is invoked. However, to improve the readability of the diagram, only one return line is shown,
since all alternate paths will return an object that is a subclass of the `Command` class.</span>

The final step of this section is to invoke the `Command#execute()` method, which will in turn call the
overridden `execute()` method of the subclass of `Command`.
- For example, if the user input is `workout /new push up /reps 10`, the created `WorkoutCommand` object is upcasted
to `Command` when returned to `WerkIt#startContinuousUserInput()`, but when `newCommand.execute()` is called,
`WorkoutCommand#execute()` is called.

Thereafter, the appropriate procedures are taken to complete the task requested by the user. The various procedures
are explained in later sections of this developer guide.

#### Illegal Characters and Phrases
Some symbols and phrases are reserved for use by the application and thus are not allowed to be used by the user
in his/her inputs to avoid any potential instabilities when processing his/her inputs.

| Illegal Character/Phrase | Purpose in Application                                                                                                                                                                |
|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| The pipe character &#124; | Used as a delimiter in the app data files to separate the various data. Allowing the user to use delimiters in their plan names may cause issues when storing them in the data files. |
| The phrase 'rest day' | Used as an indicator that a particular day in the user's schedule does not have a plan in it. Allowing the user to name a plan as 'rest day' may cause issues when displaying the schedule. |

If these characters are inputted by the user, as mentioned in Step 3 above, an `InvalidCommandException` will be thrown 
and the parsing is aborted.

---

### Exercise
#### List Exercise

If the user's command type is to list the exercises available, i.e. `exercise /list`, the
`Parser#parseUserInput(String userInput)` method will parse the 'exercise' base word and proceed to create exercise related
command using `Parser#createExerciseCommand(String userInput)` method. This method will further evaluate the
exercise action, in this case, `/list` and call the constructor of `ExerciseCommand` class by passing relevant parameters related to the
ExerciseCommand constructor. If the exercise action is null or incorrect, an InvalidCommandException will be thrown. Once the exercise command is created,
this exercise command is executed via the `ExerciseCommand#execute()` method. As it is executed, the method will check the
type of action to be executed, in this case, list. It will then list the exercises available for selection from the exerciseList using the `ExerciseList#printExerciseList()`.

The following sequence diagram illustrates how the `exercise /list` command works in greater detail:

![List Exercise Sequence Diagram](uml/sequenceDiagrams/exercises/images/viewExercise.png)

---

### Workout
#### Create New Workout

A summary of the general procedure of a new workout being inputted and stored into WerkIt! is as follows:
1. User enters the command `workout /new <workout name> /reps <number of repetitions>`.
2. A new `Workout` object is created and stored in the application.
3. The success response is printed to the user through the terminal.
4. The new `Workout` object data is written to the resource file `workouts.txt`.

The following sequence diagram illustrates how the `workout /new` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that are deemed to be trivial 
have been removed from the sequence diagram. Some reference frames will be elaborated further 
down this section. Reference frames that will not be elaborated on will be made known.</span>

![Create Workout Sequence Diagram](uml/sequenceDiagrams/workouts/images/CreateWorkout.png)

**(Before Step 1)** The user's input (in this case will be a `workout /new` command) is obtained and parsed to obtain
a `WorkoutCommand` object that contains the user's input.

<span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

**(Step 1)** When `WorkoutCommand#execute()` is called, because this is a `workout /new` command, the method will call
`WorkoutList#createAndAddWorkout()`.

The following sequence diagram is the detailed procedures for Step 2's `WorkoutList#createAndAddWorkout()`:
![createAndAddWorkout() Sequence Diagram (Part 1)](uml/sequenceDiagrams/workouts/images/CreateAndAddWorkout.png)

<span class="box info">:memo: To improve the diagram's readability, logging-related and input-checking method calls, and 
exception throws in `WorkoutList#createAndAddWorkout()` have been omitted.</span> 

**(Before Step 2.1)** Methods from the `String` and `Integer` classes are called to parse the
argument given to `WorkoutList#createAndAddWorkout()` to obtain the following information required to create the
`Workout` object:
1. Name of the exercise
2. Number of repetitions associated with the exercise in (1).

Next, validity checks of the user input are carried out to ensure that the data entered is valid as a
new `Workout` object. The requirements for a valid new `Workout` object are as follows:
1. The exercise name must exist in `ExerciseList`'s `exerciseList`, which is an `ArrayList<String>` of exercise 
names. An `InvalidExerciseException` is thrown if this requirement is not met. 
2. The repetition value must be a non-negative integer greater than 0.
3. The (exercise name, repetition value)-pair must not already exist in the list of workouts maintained in
`WorkoutList`. For example, if a workout of 20 reps of push-ups is already stored in the list,
it cannot be created again.

If any of the three requirements are not met, the entire workout creation process is aborted.
If requirement 1 is not met, an `InvalidExerciseException` will be thrown. If requirements 2 and/or 3 are not met, an 
`InvalidWorkoutException` will be thrown.

Note that the above methods and exception throws are not shown in the sequence diagram to improve the readability of the 
sequence diagram.

**(Step 2.1)** If the above checks pass, a new `Workout` object with the user-specified exercise name and
repetition value is created. 

**(Step 2.3)** Once the `Workout` object is created, a key of the object will be generated (see the 
[Design Considerations](#design-considerations-for-creating-a-new-workout) section for more details of the `HashMap`
implementation).

**(Step 2.5)** The key-`Workout` pair is stored in `workoutsHashMapList` which in turn is stored in `WorkoutList` 

**(Step 2.7)** The key of the newly-created `Workout` object is added to the `workoutsDisplayList`, an 
`ArrayList<String>` object stored in `WorkoutList`. This ArrayList will be used for displaying the workouts when the 
command `workout /list` is entered by the user. This is the final step of `WorkoutList#createAndAddWorkout()`.

**(Step 4)** Upon returning to `WorkoutCommand`, `UI#printNewWorkoutCreatedMessage()` is called to display a response to
the user via the terminal. The following is an example of a response after the user entered `workout /new russian twist 
/reps 50`:
```
----------------------------------------------------------------------
Alright, the following workout has been created:

	russian twist (50 reps)

----------------------------------------------------------------------
```

**(Step 6)** `FileManager#writeNewWorkoutToFile` is called to write the newly-created `Workout` object's data into 
`workouts.txt` which is stored on the user's local filesystem.

This completes the process of adding a new workout to WerkIt!

##### Design Considerations for Creating a New Workout
###### HashMaps - Motivation
Back in Version 1.0 of WerkIt!, workouts were stored in an ArrayList of `Workout` objects. In that version, plans
and schedules were not yet implemented and thus there was no real issues, since we can easily use index numbers
shown in `workout /list` to reference workouts when the user enters `workout /update` and `workout /delete` commands.

As an example, here is a list of workouts as shown when `workout /list` is used:
```
----------------------------------------------------------------------
> workout /list
----------------------------------------------------------------------
Showing workouts 1-3 of 3:

1. push up (10 reps)
2. sit up (10 reps)
3. pull up (10 reps)

Showed all workouts in list
----------------------------------------------------------------------
```

Thus, if we want to update the workout with 10 reps of push-ups, we can enter `workout /update 1 15` to update 
the repetition value to 15.

However, when we were designing and preparing for Version 2.0, we discovered that this **relative referencing** of
workouts by their indices pose a potentially cumbersome issue when implementing the plans and schedule features. If
we were to continue using relative indexing to reference workouts in plans, the effort needed to maintain the 
references in plans can become unnecessary complex.

For example, using the same list of workouts we have above, suppose we have a plan that includes workout indices
1 and 3 (10 push-ups and 10 pull-ups). Now, suppose the user decides to delete workout index 2 (10 sit-ups), this
means that the 10 pull-ups will now have an index number of 2. Thus, if we were to continue using relative indexing
to reference workouts in plans, there is a greater risk of making wrong references, and the amount of additional
code to update these references can become too complex.

###### Usage of HashMap
Thus, we have decided to use a HashMap on top of the existing ArrayList to store `Workout` objects. This will allow 
workouts to be referenced by their unique keys when creating plans and schedules, while allowing the user to continue
using the convenience of relative indexing for `workout /update` and `workout /delete` commands. The ArrayList of 
`Workout` objects from before is now converted into an ArrayList of Strings that will keep the keys of the `Workout` 
objects. Now, to manipulate the `Workout` object (e.g. `workout /update`),
1. User enters the index number of the workout he/she wants to update (as seen in `workout /list`).
2. The key of the `Workout` object is obtained from the ArrayList of keys (`workoutsDisplayList`).
3. The `Workout` object is obtained from the HashMap (`workoutsHashMapList`).

Note that the user will not have any direct interactions with the HashMap implementation and it should be transparent
to him/her.

---

#### List Workout
A summary of the general procedure of listing all the workouts being stored on WerkIt! is as follows:
1. User enters the command `workout /list`.
2. The application will then process this command and display all workouts stored in the workoutList at once.

The following sequence diagram illustrates how the `workout /list` command works in greater detail:
> To simplify the sequence diagram, some method invocations that deemed to be trivial
> have been removed from the sequence diagram. Reference frames will be elaborated further
> down this section.

![ListWorkoutUML](uml/sequenceDiagrams/workouts/images/listWorkout.png)
<br>

(Steps 1 to 3) After the user input is received, the `WerkIt` object will call the `Parser#parseUserInput(userInput)` method to parse the user input.
Upon parsing of the input, a `WorkoutCommand` object is obtained. This `WorkoutCommand` object is upcasted to a `Command` object on return
to the `WerkIt` object. It will then execute the workout command by calling the `WorkoutCommand#execute()` method.

(Steps 4) Since the workout command entered is `workout /list` the `WorkoutList#listAllWorkout()` method will be called 
to process the command and perform the action of listing the workouts. 

(Steps 5 and 6) To get each of the workouts stored in the workoutList, `WorkoutList#getWorkoutFromIndexNum(index)` method 
is called to obtain each of the `workout` object. Each `workout` object contains the exercise name as well as the 
number of repetitions of that exercise set by the user. 

(Steps 7 to 9) Upon obtaining the `workout` object, `Workout#toString()` method is called to formulate and print 
the workouts which is being displayed on the terminal to the user. 

---

#### Delete Existing Workout
A summary of the general procedure of an existing workout being removed from WerkIt! is as follows:
1. User enters the command `workout /delete <workout index number in workout list>`.
2. The workout with the corresponding workout index number in the workout list (can be determined by entering `workout /list`) is removed from the application's workout list.
3. The success response is printed to the user through the terminal.
4. The resource file, `workouts.txt`, is rewritten according to the application's workout list that has been modified.

The following sequence diagram illustrates how the `workout /delete` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
 have been removed from the sequence diagram. Reference frames will be elaborated further
 down this section.</span>

![Delete Workout Sequence Diagram](uml/sequenceDiagrams/workouts/images/deleteWorkout-Part1.png)
<br><br>
**(Before Step 1)** The user's input (in this case will be a `workout /delete` command) is obtained and parsed to obtain
a `WorkoutCommand` object that contains the user's input.

<span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
 ["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

**(Step 1)** When the `WorkoutCommand#execute()` method is called, it will identify
that the workout action is of type `delete`. Thus, it will subsequently call the 
`WorkoutList#deleteWorkout()` method to perform the deletion of the workout.
<br><br>
The following sequence diagram is the detailed procedure for Step 2's `WorkoutList#deleteWorkout()`:
<br><br>
![Delete Workout Detailed Sequence Diagram](uml/sequenceDiagrams/workouts/images/deleteWorkout-Part2.png)

<span class="box info">:memo: To improve the diagram's readability, logging-related and input-checking method calls, and exception throws in
 `WorkoutList#deleteWorkout()` have been omitted.</span>

**(Steps 2.1 to 2.2)** The `Integer#parseInt()` method is called to parse the user argument parameter given to `WorkoutList#deleteWorkout(userArgument)`.
In this case, the user argument parameter is the workout index number of the workout to be deleted from the workout list as a `String` object.
<br><br>
**(Steps 2.3 to 2.4)** With the workout index to delete, the `WorkoutList#deleteWorkout(userArgument)` method will then
fetch the `Workout` object to be deleted by calling the `WorkoutList#getWorkoutFromIndexNum(indexToDelete)` method.
However, before that `Workout` object is fetched, the `WorkoutList#deleteWorkout(userArgument)` method
checks that the workout index to delete is within the range of the application's workout list.
If index to delete is not within the range of the workout list, an `InvalidWorkoutException` exception is thrown.
<br><br>
**(Steps 2.5 to 2.8)** The `Workout` object to be deleted is subsequently removed from the ArrayList and HashMap which stores the
application's workout list.
<br><br>
**(Step 3)** The `WorkoutList#deleteWorkout(userArgument)` method returns the deleted `Workout` object to `WorkoutCommand`.
<br><br>
**(Steps 4 to 5)** Upon returning to the `WorkoutCommand` object, the `UI#printDeleteWorkoutMessage(deletedWorkout)` is called
to display the workout that has been deleted to the user via the terminal. The following is an example 
of a success deletion message after a valid workout is deleted from the application's workout list:
```
----------------------------------------------------------------------
Alright, the following workout has been removed:

	push up (20 reps)

----------------------------------------------------------------------
```

**(Steps 6 to 7)** The `WorkoutCommand#deletePlanContainsDeletedWorkout()` method will
be called to delete any existing plan(s) that contains the workout that has been deleted.
<br><br>
**(Steps 8 to 11)** The `FileManager#rewriteAllWorkoutsToFile(workoutList)` is called to rewrite
the `workouts.txt` file according to the newly modified application's workout list and the
the `FileManager#rewriteAllPlansToFile(planList)` is also called to rewrite
the `plans.txt` file according to the newly modified application's plan list.
<br><br>
This completes the process of deleting an existing workout in WerkIt!

##### Design Considerations for Deleting Existing Workout
###### Rewrite All Workout To File
Currently, the WerkIt! program will rewrite all workout to the resource file, `workouts.txt`, when the delete workout
function is executed. Such implementation may have performance issues as the program needs to rewrite the whole
file with the modified workout list whenever a workout is deleted in the application.
<br><br>
An alternative considered was to find the workout to be deleted in the resource file, and then
remove that workout. While this is a more efficient implementation, it is more complex due to the
way the workout data are formatted and stored in the `workouts.txt` file.
<br><br>
Hence, to simplify the implementation, the team decided to simply
rewrite all workouts to the resource file whenever a workout is deleted.

---

#### Update Existing Workout
A summary of the general procedure of an existing workout from WerkIt! being updated to new number of repetitions
is as follows:<br><br>
1. User enters the command `workout /update <workout index number> <new number of repetitions>`.
2. The workout with the corresponding workout index number in the workout list 
(can be determined by entering `workout /list`) is updated to the number of repetitions that user specified.
3. The success response is printed to the user through the terminal.
4. The resource file, `workouts.txt`, is rewritten according to the application's workout list that has been modified.

The following sequence diagram illustrates how the `workout /update` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial 
have been removed from the sequence diagram. Some reference frames will be elaborated further down this section.</span>

![Update Workout Sequence Diagram](uml/sequenceDiagrams/workouts/images/updateWorkout-Part1.png)
<br><br>
**(Before Step 1)** The user's input (in this case will be a `workout /update` command) is obtained and parsed to obtain
a `WorkoutCommand` object that contains the user's input.

<span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
 ["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

**(Step 1 to 3)** When the `WorkoutCommand#execute()` method is called, 'workout /update' command is identified, and
`WorkoutList#getCurrentWorkout()` will be called to get the name of the workout which will be updated later.

Subsequently, `WorkoutList#updateWorkout()` method will be called.
<br><br>

The following sequence diagram is the detailed procedure for Step 4's `WorkoutList#updateWorkout()`:
<br><br>
![Update Workout Detailed Sequence Diagram](uml/sequenceDiagrams/workouts/images/updateWorkout-Part2.png)

<span class="box info">:memo: To improve the diagram's readability, logging-related and input-checking method calls, 
and exception throws in `WorkoutList#updateWorkout()` have been omitted.</span>

**(Before Step 4.1)** Methods from the `String` and `Integer` classes are called to parse the
argument given to `WorkoutList#updateWorkout()` to obtain the following information required to update a
workout:
1. Workout index number in list.
2. New number of repetitions assigned to the workout in (1).

Next, validity checks of the user input are carried out to ensure that the data entered is valid. 
The requirements for a valid input are as follows:
1. Workout index number must be a positive integer smaller than the total number of workouts in list.
2. New repetition value must be a non-negative integer greater than 0.

If any of the two requirements are not met, an `InvalidWorkoutException` will be thrown and 
the entire update process is aborted.

Note that the above methods and exception throws are not shown in the sequence diagram to improve the readability of the
sequence diagram.

**(Steps 4.1 to 4.2)** With the workout index number to update, the `WorkoutList#updateWorkout()` method 
will then fetch the `Workout` object to be updated by calling method `WorkoutList#getWorkoutFromIndexNum()`.
After the `Workout` object is fetched, a check will be conducted to ensure that the 
(exercise name of the workout, new repetition value)-pair is not exist in the list of workouts maintained 
in `WorkoutList`. For example, if a workout of 20 reps of push-ups is already stored in the list, a workout of 
push up with 15 reps cannot be updated to push up with 20 reps.

If this check fails, an `InvalidWorkoutException` exception is thrown.
<br><br>
**(Steps 4.3 to 4.6)** The `Workout` object to be updated is modified to new number of reps and `workoutsHashMapLis` 
is subsequently updated.

This is the end of `WorkoutList#updateWorkout()` method.
<br><br>
**(Step 5)** The `WorkoutList#updateWorkout()` method returns the updated `Workout` object to `WorkoutCommand`.
<br><br>
**(Steps 6 to 7)** Upon returning to the `WorkoutCommand` object, the `UI#printUpdateWorkoutMessage()` is called
to display the workout that has been updated to the user via the terminal. The following is an example
of a success update message after a valid workout is updated from the workout list:
```
----------------------------------------------------------------------
Alright, the following workout has been updated:

	push up (10 reps)

----------------------------------------------------------------------
```

**(Steps 8 to 9)** The `WorkoutCommand#updatePlanContainsUpdatedWorkout()` method will
be called to update any existing plan(s) that contains the workout that has been updated.
<br><br>
**(Steps 10 to 13)** The `FileManager#rewriteAllWorkoutsToFile()` is called to rewrite
the `workouts.txt` file according to the modified workout list and the
the `FileManager#rewriteAllPlansToFile()` is also called to rewrite
the `plans.txt` file according to the newly modified plan list.
<br><br>
This completes the process of updating an existing workout in WerkIt!

---

### Plan
#### Create A New Plan

A summary of the general procedure of a new plan being created and stored in WerkIt! is as follows:
1. User enters the command `plan /new <plan name> /workouts <workout index numbers in workout list separated by comma>`.
2. A new `Plan` object is created and stored in the application.
3. The success response is printed to the user through the terminal.
4. The new `Plan` object data is written to the resource file `plans.txt`.

The following sequence diagram illustrates how the `plan /new` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
 have been removed from the sequence diagram. Reference frames will be elaborated further
 down this section.</span>

![Create Plan Sequence Diagram](uml/sequenceDiagrams/plans/images/createPlan-Part1.png)
<br><br>
**(Before Step 1)** The user's input (in this case will be a `plan /new` command) is obtained and parsed to obtain
a `PlanCommand` object that contains the user's input.

<span class="info box">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
 ["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

**(Step 1)** When the `PlanCommand#execute()` method is called, it will identify
that the plan action is of type `new`. Thus, it will subsequently call the
`PlanList#createAndAddPlan(userArgument)` method to perform the creation of the plan.
<br><br>
The following sequence diagram is the detailed procedure for Step 2's `PlanList#createAndAddPlan(userArgument)`:
<br><br>
![Create And Add Plan Detailed Sequence Diagram](uml/sequenceDiagrams/plans/images/createPlan-Part2.png)

<span class="box info">:memo: To improve the diagram's readability, logging-related and input-checking method calls, and exception throws in
 `PlanList#createAndAddPlan()` have been omitted.</span>

**(Before Steps 2.1 to 2.2)** The user argument parameter of the `PlanList#createAndAddPlan(userArgument)`
method is parsed to obtain the following information required to create the `Plan` object:
1. Name of the plan.
2. Workout index numbers in the workout list separated by comma.<br><br>

Once the information are obtained, the name of the plan to be created will be validated.
This is to ensure all plan names are acceptable and unique in the application.
If the plan name is invalid, an `InvalidPlanException` exception will be thrown.
<br><br>
Subsequently, this `PlanList#createAndAddPlan()` method will find out the number of workouts
to be added into the new plan. This is done in order to check that the number of workouts to be added into the new plan
does not exceed 10 workouts, and there should minimally
be 1 workout in a plan. If the new plan does not meet the minimum and maximum workout number requirement,
an `InvalidPlanException` will be thrown.
<br><br>
**(Steps 2.1 to 2.2)** An ArrayList of Workout object is created to store the workouts to be added into the new plan.
<br><br>
**(Steps 2.3 to 2.4)** As the workout indexes in the user argument parameter (e.g. "1, 2, 3") is of type `String`, 
the loop will split (by comma) and convert each number string into an `Integer`. 
These workout indexes are also checked to ensure that they are within
the application's workout list range.
<br><br>
If the workout indexes are valid, the valid `Workout` object is fetched from the application's workout list based 
on the workout index and then added into the `ArrayList` that was created in the previous step (Steps 2.1 to 2.2).
The loop will continue until all workouts to be added in the new plan is added into that `ArrayList`.
<br><br>
**(Steps 2.5 to 2.10)** With the valid plan name and the `ArrayList` containing the workouts to be added into the new plan, 
a new `Plan` object can be created. However, before creating the `Plan` object, the `PlanList#createAndAddPlan()` method will 
check that the new plan to be created does not contain the same workout order as any existing plans. If it does contain
the same workout order as any existing plan, an `InvalidPlanException` exception will be thrown.
<br><br>
If it is confirmed that the new plan does not contain
the same workout order as any existing plan, a new `Plan` object is created.
This new `Plan` object is then added to the application's plan list.
<br><br>
**(Step 3)** The `PlanList#createAndAddPlan(userArgument)` method returns the new `Plan` object to `PlanCommand`.
<br><br>
**(Steps 4 to 5)** Upon returning to the `PlanCommand` object, the `UI#printNewPlanCreatedMessage(newPlan)` is called
to display the plan that has been created to the user via the terminal. The following is an example
of a success plan creation message (new plan is called "Grow My Muscles"):
```
----------------------------------------------------------------------
Alright, the following plan has been created:

	grow my muscles

----------------------------------------------------------------------
```
**(Steps 6 to 7)** `FileManager#writeNewPlanToFile(newPlan)` is called to write the newly-created `Plan` 
object's data into `plans.txt`, which is stored on the user's local filesystem.
<br><br>
This completes the process of creating and adding a new plan to WerkIt!.

---

#### List Plans

A summary of the general procedure of listing all plans in the application is as follows:
1. User enters the command `plan /list`.
2. A list of plan names is displayed to the user via the terminal.

The following sequence diagram illustrates how the `plan /list` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
 have been removed from the sequence diagram. Reference frames will be elaborated further
 down this section.</span>

![List Plan Sequence Diagram](uml/sequenceDiagrams/plans/images/listPlan.png)
<br><br>
**(Before Step 1)** The user's input (in this case will be a `plan /list` command) is obtained and parsed to obtain
a `PlanCommand` object that contains the user's input.

<span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
 ["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

**(Steps 1 to 2)** When the `PlanCommand#execute()` method is called, it will identify
that the plan action is of type `list`. Thus, it will subsequently call the
`PlanList#listAllPlan()` method to display all available plan names.
<br><br>
**(Step 3)** The `PlanList#listAllPlan()` method will first check if the application's plan list is empty.
If it is, it will display to the user that no plan has been created yet.
<br><br>
**(Step 4)** The `PlanList#listAllPlan()` method will then loop through the application's plan list and show
the names of the plan to the user. The following is an example of what is 
displayed to the user when the `plan /list` command is entered while the application's plan list is not empty:
```
----------------------------------------------------------------------
Here are all your plan(s).
To view each plan in detail, enter
'plan /details <plan number in list>'.

1. Test
2. Grow My Muscles
----------------------------------------------------------------------
```
**(Steps 5 to 6)** The `PlanList#listAllPlan()` method returns to the `PlanCommand` object
and the `PlanCommand` object returns to the `WerkIt` object.
<br><br>
This completes the process of displaying all plans in WerkIt!.

---

#### List Workouts In A Plan
A summary of the general procedure of listing all workouts in a plan is as follows:
1. User enters the command `plan /details <plan index number>`.
2. A list of workouts that user specified is displayed through the terminal.

The following sequence diagram illustrates how the `plan /details` command works:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
have been removed from the sequence diagram.</span>

![Details Of Plan Sequence Diagram](uml/sequenceDiagrams/plans/images/detailsOfPlan.png)
<br><br>
**(Before Step 1)** The user's input (in this case will be a `plan /details` command) is obtained and parsed to obtain
a `PlanCommand` object that contains the user's input.

<span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

**(Step 1 to 2)** When the `PlanCommand#execute()` method is called, it will identify that the plan action is of type 
`details`. Thus, `PlanList#listPlanDetails(userArgument, ui)` will be called to display all the workouts in the plan
which user specified.

<span class="box info">:memo: To improve the diagram's readability, logging-related, input-checking method calls,
and exception throws in `PlanList#listPlanDetails()` have been omitted.</span>

**(Before Step 3)** Methods from the `String` and `Integer` classes are called to parse the
argument given to `PlanList#listPlanDetails()` to obtain the plan index number in list.

Next, validity checks of the user input are carried out to ensure that the data entered is valid.
Plan index number must be a positive integer and smaller than the total number of plan in list 
in order to pass the check. Otherwise, an `InvalidPlanException` will be thrown and
the entire process is aborted.

Note that the above methods and exception throws are not shown in the sequence diagram to improve the readability.

**(Steps 3 to 4)** With the plan index number, a `Plan` object which user want to view details 
will be fetched by calling method `PlanList#getPlanFromIndexNum()`.

**(Steps 5 to 6)** An ArrayList of `Workout` object is created to store the workouts in the `Plan` object we get 
in the previous step.

**(Step 7 to 9)** The `PlanList#listPlanDetails()` method will then loop through the workout list which we get in the 
previous step and show the name of the workouts with number of repetitions to the user. 
The following is an example of what will be displayed to the user when the `plan /details` command is entered:

```
----------------------------------------------------------------------
Here are the 3 workouts in [grow my muscles].
1. push up (20 reps)
2. sit up (10 reps)
3. pull up (10 reps)
----------------------------------------------------------------------
```
This completes the process of displaying all workouts in a plan in WerkIt!

---

#### Delete Existing Plan


---
### Schedule
The overview of the design on schedule features can be found [here](#schedule-related-features). 

#### Update Schedule
A summary of the general procedure of updating a plan for a particular day to the schedule in WerkIt! is as follows:
1. User enters the command `schedule /update <day number> <plan number>`.
2. If there are no plan being scheduled for the day, a new Day object is created and stored in the application.
   If there is an existing plan scheduled for that particular day, the Day object that had already been created,
   will then be updated to store the latest plan scheduled for the day.
3. The success response is printed to the user through the terminal.
4. The Day object data is written to the resource file `schedule.txt`.

The following sequence illustrates how the schedule /update command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
 have been removed from the sequence diagram. Reference frames will be elaborated further
 down this section.</span>

![Update Schedule Sequence Diagram](uml/sequenceDiagrams/schedule/images/updateSchedule.png)
<br><br>

(Before step 1) <span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

(Step 1) The program waits for the user's input, which in this case,
is the schedule `/update <day number> <plan number>` command.
An example of a valid command would be `schedule /update 1 1`.This command entered
by the user is a schedule command, hence it is being executed by calling the `ScheduleCommand#execute()` method.

Steps 2 and 3 are explained in greater details in the following sequence diagram:

![updateScheduleDetails](uml/sequenceDiagrams/schedule/images/updateScheduleDetails.png)

(Steps 2.1 to 2.2) The `DayList#updateDay(userArgument)` method will be called to update/add a plan for a particular 
day in the schedule stated by the user. It will fist call the `String#split(" ")` method 
to separate out the `userArgument` given by the user. Upon, splitting of the whitespaces in `userArgument`, 
it will then check if the `userArgument` is valid. If it is invalid, an 
`Exception` would be thrown to the user and following the termination of the process (step 2.3).

(Steps 2.5 to 2.8) After splitting and checking the validity of `userArgument`, variables `userArgument[0]` representing
`dayNumber` and `userArgument[1]` representing the plan index of the plan stored in the planList (`planNumber`) are obtained. Both the 
variables are then converted from data type string to integer. In addition, there is a check executed on both the converted 
`dayNumber` and `planNumber` to ensure that they are valid. This check is done so by calling the `DayList#isDayValid(DayNumber)` and
`DayList#isPlanValid(planNumber)` methods respectively.

(Steps 2.9 and 2.11) If the `dayNumber` or `planNumber` is not valid, an `InvalidScheduleException` would be thrown to the user,
and the entire process of updating of a plan for a particular day in the schedule is aborted.

(Steps 2.13 and 2.14) `PlanList#getPlanDisplayList()` method is called to find and return the hash value of the `planNumber`, 
`planToAddKey:String` to be scheduled for a particular day. The `planToAddKey` is used to get the `plan` object in the 
planList by calling the `PlanList#getPlanFromKey` (steps 2.15 to 2.16).

(Steps 2.17 to 2.18) Once the `Plan` object is retrieved, if there are no plan being scheduled for the day, 
a new `Day` object is created and stored in the application.

(Steps 2.19 to 2.20) However, if there is an existing plan scheduled for that particular day, the `Day` object that 
had already been created, will then be updated to store the latest plan scheduled for the day. This process is done so by
calling `Day#setNewPlanForThisDay(newDay, planToAdd)` method.

(Steps 4 and 5) After successfully created/updated the Day object, the `UI#printNewSchedule(newDay)` method
will be called to display the day and the corresponding plan scheduled for it via the terminal. The following is an
example of the message after the user had successfully scheduled a plan for the day (e.g. `schedule /update 1 1`):
```
----------------------------------------------------------------------
Alright, the following plan schedule has been created:

Monday -- arms

----------------------------------------------------------------------
```
(Step 6) Lastly, before the `ScheduleCommand` object is discarded, the `FileManager#rewriteAllDaysScheduleToFile(dayList)`
is called to rewrite the `schedule.txt` file according to the newly modified application's day list.

This completes the process of scheduling a plan for a particular day in WerkIt!

##### Design considerations for update schedule
###### Day Object
For the application, schedule is defined to be a 7-days workout plan. The days that do not have any plan scheduled
would be considered a rest day for the user. Therefore, when implementing the creation of `Day` object, a total of 7
`Day` objects at most would be created and be stored in the dayList with size 7.

Initially, if no plan has been scheduled for a particular day, the corresponding `Day` object would not be created.
For example, if no plan is being scheduled for Monday, there will be no `Day` object created for Monday and the dayList
with index 0 will not have any `Day` object being stored.

If `dayList[0]` contains a `Day` object, it would mean that the user scheduled a plan on Monday. If the
user were to execute the `schedule /update` command again to update the plan to be scheduled for Monday, the application
will update the content in the Day object stored in `dayList[0]`. It will not recreate a `Day` object for Monday
to store the new plan.

---

#### View Schedule
To view the schedule in WerkIt! User can enter the command `schedule /list`.

A schedule in the WerkIt! refers to a 7-days workout plan schedule. 
For example, a plan named "leg day" which consists of 3 workouts "5 squats, 5 lunges, 5 squats"
can be added into the schedule by entering `schedule /update <day number> <plan number>` command. Hence, "leg day" plan
can be schedule on Monday by the command of `schedule /upate 1 1`. To view the plan in the schedule, user can enter the
command `schedule /list`.

The following sequence illustrates how the `schedule /list` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
 have been removed from the sequence diagram. Reference frames will be elaborated further
 down this section.</span>

![ListSchedule](uml/sequenceDiagrams/schedule/images/listSchedule.png)
<br><br>

(Before step 1) <span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

(Step 1 and 2) The command passed in by the user is `schedule /list`, it is a schedule command 
and will be executed by calling the ScheduleCommand#execute() method. Since the command action is `list`
the application will execute the `DayList#printSchedule()` method.
No parameters are needed to be passed in the method as the method loop through the scheduleList, which stores all the plan names
scheduled for the individual days.

(Steps 3 and 4) To ensure the printing of the schedule is formatted properly with a common standard, when `DayList#printSchedule()`
method is called, it will invoke a for loop to pad the plan name for all the plans in the scheduleList
with spaces by calling the `DayList#padWithSpaces(planForDay)` method. This method will pad both the front and back of the
plan name with spaces. Total characters that the padding and the plan name combined should not exceed 30 characters.

(Step 5) Upon the successful execution of the `DayList#printSchedule()` method, the plan scheduled on each of the day
will be display on the console to the user. An expected outcome of the `schedule /list` command would be:

```
----------------------------------------------------------------------

                         WORKOUT SCHEDULE
----------------------------------------------------------------------
     Day       |            Plan Name
----------------------------------------------------------------------
      Monday   |              arms                          
     Tuesday   |            rest day                      
   Wednesday   |            leg day                      
    Thursday   |            rest day                      
      Friday   |            rest day                      
    Saturday   |            leg day                      
      Sunday   |            rest day                      

----------------------------------------------------------------------
```

By default, if no plan is being scheduled for any of the day, the day is to be considered as a rest day for the user.

---
#### Clear Schedule For A Day
A summary of the general procedure of clearing a plan scheduled for a particular day of the schedule in WerkIt! is as follows:
1. User enters the command `schedule /clear <day number>`.
2. The application will locate the index in the DayList which stores the corresponding `Day` object.
   This `Day` object will then be deleted from the DayList. For example, if `schedule /clear 1` command is entered,
   the index where the `Day` object storing information of the plan scheduled for Monday would be store in index 0,
   (day number -1), of the DayList.
3. DayList[day number - 1] would become null after the command is successfully being executed.
4. The success response is printed to the user through the terminal.
5. The `schedule.txt` will also be rewritten to reflect the changes. 

The following sequence illustrates how the `schedule /clear` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
 have been removed from the sequence diagram. Reference frames will be elaborated further
 down this section.</span>

![ClearSchedule](uml/sequenceDiagrams/schedule/images/clearSchedule.png) 
<br><br>

(Before step 1) <span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

(Steps 1) The program waits for the user's input, which in this case,
is the `schedule /clear <day number>` command. An example of a valid command would be `schedule \clear 1`. This command entered
by the user is a schedule command, hence it is being executed by calling the `ScheduleCommand#execute()` method.

(Step 2) Since the command entered is `schedule /clear <day number>`, the `DayList#clearDayPlan(userArgument)` method will
be called. This method will first convert the userArgument to an Integer data type (steps 3 and 4) and will then call
the `DayList#isDayValid(dayNumber)` method to check whether the day number entered by the user is valid. 
If the day number falls within the range of 1 to 7 then it is considered a valid day else 
an `InvalidScheduleException` would be thrown to the user, and the entire clearing of plan for a 
particular day in the schedule process is aborted (steps 5 and 6).

(Steps 7 and 8) If the `dayNumber` is valid, the method `DayList#clearPlan(dayNumber)` will be called to remove the plan scheduled
on that day. The `Day` object that stores the plan details for the specified day in the user command will be deleted.

(Steps 9 and 10) After which, the `DayList#convertDayNumberToDay(dayNumber)` method will be called.
As the method name suggests, this method will convert the day number to its corresponding meaning. 
For example, day number 1 will be converted to Monday. The purpose of this method is to 
make the success message to be displayed to the user more user-readable.

(Step 11 and 12) After the plan is successfully cleared for that indicated day, a success message of the process would be
printed to the user through the terminal by calling the `UI#printClearedScheduleOnADat` method. 
An example of a success message would be

```
----------------------------------------------------------------------
Plan had been cleared for Monday.
----------------------------------------------------------------------
```

(Step 13) `FileManager#rewriteAllDaysScheduleToFile(dayList)` is called to write all the `Day` objects' data stored 
in the dayList into `schedule.txt` which is stored on the user's local filesystem.

This completes the process of clearing a plan on a particular day of the schedule on WerkIt!

---
#### Clear Schedule For The Week
A summary of the general procedure of clearing all the plans stored in the schedule in WerkIt! is as follows:
1. User enters the command `schedule /clearall`.
2. The application will delete all the plans that had been added to the schedule.
3. The success response is printed to the user through the terminal. 
4. The `schedule.txt` will also be rewritten to reflect the changes.

The following sequence illustrates how the `schedule /clearall` command works in greater detail:

<span class="box info">:memo: To simplify the sequence diagram, some method invocations that deemed to be trivial
 have been removed from the sequence diagram. Reference frames will be elaborated further
 down this section.</span>

![ClearSchedule](uml/sequenceDiagrams/schedule/images/clearAllSchedule.png)
<br><br>

(Before step 1) <span class="box info">:memo: For more information on the obtaining and parsing functionality of WerkIt!, please refer to
["Parsing User Input and Getting the Right Command"](#parsing-user-input-and-getting-the-right-command) section.</span>

(Step 1) The program waits for the user's input, which in this case,
is the `schedule /clearall` command. This command entered by the user is a schedule command, 
hence it is being executed by calling the `ScheduleCommand#execute()` method.

(Step 2) Since the command entered is `schedule /clearall`, the `DayList#clearAllSchedule()` method will
be called. This method will delete all the `Day` object stored in the `dayList` using a for loop. 

(Step 3 and 4) `DayList#clearPlan(dayNumber)` will be called 7 times in a for loop to 
delete all the `Day` object stored in the `dayList`.

(Steps 5 and 6) After all the plan is successfully cleared from the schedule, `UI#printClearedScheduleMessage()` method 
will be called to print a success message of the process. This message would be printed to the user through the terminal. 
An example of a success message would be

```
----------------------------------------------------------------------
Schedule had been cleared and reset.
There is no plan scheduled for any day.
To add plan for any day, enter:
schedule /update <day number [1-7]> <plan number>
----------------------------------------------------------------------
```

(Step 7) `FileManager#rewriteAllDaysScheduleToFile(dayList)` is called to write all the `Day` objects' data stored 
in the dayList into `schedule.txt` which is stored on the user's local filesystem. 
Since all Day objects are deleted, the writing of data into `schedule.txt` would be an equivalent of 
resetting the text file. 

This completes the process of clearing of all plans stored in the schedule on WerkIt!

---

### Search

#### Search For Exercise
Format: `search /exercise <keywords>`

The `Parser#createSearchCommand(String userInput)` method will further evaluate the user input
`/exercise` and call the constructor of `SearchCommand` class by passing relevant parameters related to search exercise
to the constructor. The created `SearchCommand` object is returned by the `Parser#createSearchCommand(String userInput)`
method to `Parser#parseUserInput(String userInput)` method, and finally returned by
`Parser#parseUserInput(String userInput)` method to `WerkIt#startContinuousUserPrompt()` method. The search command will
be executed in `WerkIt#startContinuousUserPrompt()`. And based on the `<keywords>` specified by the user, the output
will either be a list of matching exercises or 'Sorry, no matching exercise found' if the user has entered the command
correctly.

The following sequence diagram illustrates how the `search /exercise` command works in greater detail:

![Search Exercise Sequence Diagram](uml/sequenceDiagrams/search/images/searchExercise.png)

#### Search For Workout
Format: `search /workout <keywords>`

The `Parser#createSearchCommand(String userInput)` method will further evaluate the user input
`/workout` and call the constructor of `SearchCommand` class by passing relevant parameters to the constructor.
The created `SearchCommand` object is returned by the `Parser#createSearchCommand(String userInput)`
method to `Parser#parseUserInput(String userInput)` method, and finally returned by
`Parser#parseUserInput(String userInput)` method to `WerkIt#startContinuousUserPrompt()` method. The search command will
be executed in `WerkIt#startContinuousUserPrompt()`. And based on the `<keywords>` specified by the user, the output
will either be a list of matching names of workout or 'Sorry, no matching workout found' if the user has entered the command
correctly.

The following sequence diagram illustrates how the `search /workout` command works in greater detail:

![Search Exercise Sequence Diagram](uml/sequenceDiagrams/search/images/searchWorkout.png)

#### Search For Plan
Format: `search /plan <keywords>`

The `Parser#createSearchCommand(String userInput)` method will further evaluate the user input
`/plan` and call the constructor of `SearchCommand` class by passing relevant parameters to the constructor.
The created `SearchCommand` object is returned by the `Parser#createSearchCommand(String userInput)`
method to `Parser#parseUserInput(String userInput)` method, and finally returned by
`Parser#parseUserInput(String userInput)` method to `WerkIt#startContinuousUserPrompt()` method. The search command will
be executed in `WerkIt#startContinuousUserPrompt()`. And based on the `<keywords>` specified by the user, the output
will either be a list of matching names of plan or 'Sorry, no matching plan found' if the user has entered the command
correctly.

The following sequence diagram illustrates how the `search /plan` command works in greater detail:

![Search Exercise Sequence Diagram](uml/sequenceDiagrams/search/images/searchPlan.png)

#### Search For All
Format: `search /all <keywords>`

The `Parser#createSearchCommand(String userInput)` method will further evaluate the user input
`/all` and call the constructor of `SearchCommand` class by passing relevant parameters to the constructor.
The created `SearchCommand` object is returned by the `Parser#createSearchCommand(String userInput)`
method to `Parser#parseUserInput(String userInput)` method, and finally returned by
`Parser#parseUserInput(String userInput)` method to `WerkIt#startContinuousUserPrompt()` method. The search command will
be executed in `WerkIt#startContinuousUserPrompt()`. And based on the `<keywords>` specified by the user, the output
will either be a list of matching names of exercise, workout and plan or not found messages if the user has entered the command correctly.

The following sequence diagram illustrates how the `search /all` command works in greater detail:

![Search Exercise Sequence Diagram](uml/sequenceDiagrams/search/images/searchAll.png)

---

### File Management

#### Design Considerations For Inconsistent Data Between Resource Files

The first step of loading local files to the app involves the checking of validity of data. That is, before loading plan
data, `FileManager` will check whether the workouts in the plan exist in the `workouts.txt` file, and before loading
schedule data, `FileManager` will also check whether the plans in the `schedule.txt` could be found in `plan.txt`. If 
all the data can be matched, the files will be loaded successfully, otherwise only the unmatched data are classified as 
"corrupted data" and will be deleted and the deletion will be cascaded. 

Although the users are warned not to edit  the local resource files as this action may corrupt the stored data,
resulting in WerkIt unable to load the data properly, there may still be scenarios where the users accidentally edited 
the files. Thus, other than the warning in our [UserGuide](https://ay2122s2-cs2113t-t09-2.github.io/tp/UserGuide.html),
we also implemented error handling methods to handle the situation where users edited the files and caused data 
corruptions. We could have implemented the handling of "corrupted data" in a more hassle-free way by simply clearing 
all local data. However, in order to provide the best possible user experience by minimising the amount of data lost in 
such situations, we decided to implement the validity checking such that only the affected data are removed while 
keeping all the non-affected data safely.


## Product Scope
### Target User Profile

{Describe the target user profile}

### Value Proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...                                        | So that I can ...                                                      |
|---------|----------|------------------------------------------------------|------------------------------------------------------------------------|
| v1.0    | user     | view exercises                                       | create my workout                                                      |
| v1.0    | user     | create a workout                                     | keep track of how many repetitions I would like to do with an exercise |
| v1.0    | user     | view workouts                                        | see what are the workouts I can add into my workout plan               |
| v1.0    | user     | delete workout                                       | remove any workouts that I will not be doing                           |
| v1.0    | user     | update workout                                       | make modification to my workouts after I got stronger                  |
| v1.0    | user     | work on the workouts/plans I have created previously | use those workouts in my current workout sessions                      |
| v2.0    | user     | create a workout plan                                | perform multiple workouts at a time                                    |
| v2.0    | user     | search for the plan I have created                   | follow the workouts listed in it                                       |
| v2.0    | user     | view all plans I have created                        | see what plans I have already created                                  |
| v2.0    | user     | be able to delete a plan I have created              | remove the plans that I will not be doing                              |
| v2.0    | user     | schedule a plan on a particular day                  | plan my workout routine                                                |
| v2.0    | user     | view my schedule                                     | see what plans I have scheduled for the week                           |
| v2.0    | user     | remove the plan scheduled on a particular day        | change the workout plan that I want to do                              |
| v2.0    | user     | reset my workout plan schedule                       | easily re-schedule the plans that I want to do                         |
| v2.0    | user     | search for exercise                                  | find the exercises that I am interested                                |
| v2.0    | user     | search for workouts that I have created              | find the workouts that I am interested                                 |
| v2.0    | user     | search for plans that I have created                 | find the plans that I am interested                                    |
| v2.0    | user     | view the summary of what I can do in the application | know which command to use to perform the actions I want                |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* **Repetitions** - The process of repeating an exercise. Often abbreviated to 'reps'.
* **Exercise** - A single 'unit' of exercise. A type of exercise.
    * e.g. push up, jumping jacks, sit-ups
* **Workout** - A single 'unit' of exercise with a number of repetitions associated with it.
    * e.g. push up (5 reps), jumping jacks (2 reps), sit-ups (7 reps)
* **Plan** - A set of workouts
    * Example:


| Plan Name      | Contains                                                                                                               |
|----------------|------------------------------------------------------------------------------------------------------------------------|
| Grow my Biceps | Barbell curls (3 reps), push ups (10 reps), deadlift (2 reps)                                                          |
| Whole Body!    | Crunches (10 reps), jumping jack (3 reps), lift ups (4 reps), pull ups (3 reps), planking (2 reps), leg cycle (2 reps) |


* **Schedule** - Consists of Days 1 to 7. Users will add or modify a plan to that particular day
of their schedule. For instance, the user's daily schedule can look like this:

| Day   | Plan Name      |
|-------|----------------|
| Day 1 | Grow my Biceps |
| Day 2 | Rest Day       |
| Day 3 | Whole Body!    |
| Day 4 | Leg Day        |
| Day 5 | Grow my Biceps |
| Day 6 | Whole Body!    |
| Day 7 | Rest Day       |


## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
