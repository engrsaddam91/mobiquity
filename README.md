# Mobiquity Assignment
Assignment: Packaging System

## Approach
- Every Class is created Based on single responsibility principle.
- TextFileReaderSevice is used to read text file data
- FileParserService is used to parse the file data and returns List of Packages. Where each package contains package weight and Items,which can be included
- PackageCreationService is used to Create Package of allowed weight.This package is made of Items which Qualify the given rules.
- I have used the Max Heap and Map. Which will contain the Items on the bases of Cost and Weight.After the map of Items is created then i am returning the list of Qualified Items. 

### Prerequisite:
- Java JDK 11
- Maven

### Technologies
- Java 11
- Maven
- Spring boot
- Spring AOP Logging
- Integration & Unit Testing
- Junit5
- Lombok
- Single Responsibilities

### Clean And Build
mvn clean install

### Build And Test
mvn clean test
