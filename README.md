## Description
This repo contains part of autotests needed to cover Triangle Service API.

## Minimal requirements:
1. JDK 8
2. Lombok plugin for intellij idea

## Initialize project
1. File > new > Project from version control 
2. Set the repository URL as git@github.com:KramskoiSergei/triangle_test.git
3. Set the path to project directory
4. Press the clone button
 
## Project build
Project is building via gradle which is configuring at ./gradle/wrapper/gradle-wrapper.properties

## Open project with Idea
1. Open build.gradle file as Project
2. Ensure enable annotation processing

## Run with Idea
Recommend to switch the mode from "Gradle" to "Idea" to run tests" (Preferences -> Build -> Gradle -> Run test using -> Idea)