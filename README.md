# advent-of-code-2025
Code solutions for Advent of Code challenge 2025 in Java

## Setup Instructions

1. **Clone the Repository**: 
   ```
   git clone <repository-url>
   cd advent-of-code-2025
   ```

2. **Build the Project**: 
   Use Gradle to build the project:
   ```
   ./gradlew build
   ```

3. **Run a Day's Challenge**: 
   To run a specific day's challenge, navigate to the corresponding directory and execute the main class. For example, to run Day 1:
   ```
   cd app/src/main/java/advent/of/code/day01
   java Day01.java
   ```

4. **Run Tests**: 
   To run the tests for all days, use:
   ```
   ./gradlew test
   ```

## Day 00
As this is my first time setting up a Java project, I created a "Day 0" challenge to warm things up before AOC starts officially.
Please pretend like this is told like a cute Elf story.

### Part A
Given an input text file, return the amount of times a characters is present below the same one.
For example, given:
```
Hello
World
```

We expect an output of 1, only the 4th letter - `l`
```
   v
Hello
World
   ^
```

### Part B
Same as part A, but this time, consider letters to the right as well.
Looking at the same example as before, this time we expect to return 2, since `Hello` has 2 consecutive `l`s.
Add that to the `l`s from part A, we get 2.