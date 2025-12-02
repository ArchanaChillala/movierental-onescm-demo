# Refactoring Exercise

This source code follows Martin Fowler's book "Refactoring, Improving the Design of Existing Code".

When you find you have to add a feature to a program, and the program's code is not
structured in a convenient way to add the feature, first refactor the program to make it
easy to add the feature, then add the feature.

Whenever you do refactoring, the first step is always the same.
You need to build a solid set of tests for that section of code. The tests are essential because even
though you follow refactorings structured to avoid most of the opportunities for introducing bugs,
you are still human and still make mistakes. Thus, you need solid tests.

## Problem Statements
This codebase includes two refactoring exercises under separate packages
- `gildedrose`
- `movierental`

Each of these refactoring exercises, when performed systematically provides insight into understanding different code smells, the technique to refactor and the design principles to consider while refactoring. This is an exercise to demonstrate how clean code can be evolved from legacy codebase. 

## Usage

The purpose of this is to provide good examples for the refactoring workshop.

## Build

All you need to build this project is Java 11 or later, Maven 3.0 or later.

## Testing

Unit tests can be run using maven[1]:

    $ mvn test

[1]: http://maven.apache.org/

Tests are located in the test directory and run using Junit.
