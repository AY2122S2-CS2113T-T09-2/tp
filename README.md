# A tP Project

# WerkIt! 

WerkIt! is an application for managing workout routines, optimized for use via a Command Line Interface (CLI). 
This application is for you if you wish to have an application to keep track of your workouts, plans and workout routines for a week.
Given below are instructions on how to use it.

## Quick Start Guide

1. Ensure that you have [Java 11](https://www.oracle.com/java/technologies/downloads/) or above installed.
2. (For Microsoft Windows users) Download [Windows Terminal][1] from the Microsoft Store.
3. Download the latest version of WerkIt! from [here](https://github.com/AY2122S2-CS2113T-T09-2/tp/releases).
4. Create a new directory and move the WerkIt! JAR file to that directory.
5. Open Windows Terminal (for Microsoft Windows users) or the other recommended terminals as listed in 
['Recommended Terminals'](#recommended-terminals) based on your operating system.
6. Set your current working directory to the new directory.
7. Run the WerkIt! application with the command: `java -jar WerkIt.jar`.
8. If you have set up your environment correctly, you should see the following
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
9. Type `exit` to exit the program.

You are now ready to begin developing!

## Build automation using Gradle
[to be changed]
* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing
[to be changed]

### I/O redirection tests
[to be changed]
* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests
[to be changed]
* A skeleton JUnit test (`src/test/java/seedu/duke/DukeTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle
[to be changed]
* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation
[to be changed]
`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.
