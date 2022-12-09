# Differ generator

This project is a CLI application that allows you to determine the difference between two data structures.

Application features:

* Supports different input formats: *JSON* and *YAML*
* Report generation as *plain text*, *stylish* and *JSON*

Result of the project - console utility `gendiff` with the following parameters:
```sh
./build/install/app/bin/app -h

Usage: gendiff [-hV] [-f=format] filepath1 filepath2
Compares two configuration files and shows a difference.
      filepath1         path to first file
      filepath2         path to second file
  -f, --format=format   output format [default: stylish]
  -h, --help            Show this help message and exit.
  -V, --version         Print version information and exit.
```
___

## Service badges

| Description | Badge |
|---|:---|
|Hexlet tests and linter status:|[![Actions Status](https://github.com/KarUrals/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/KarUrals/java-project-71/actions)|
|GitHub Actions workflow status:|[![.github/workflows/test-and-linter-check.yml](https://github.com/KarUrals/java-project-71/actions/workflows/test-and-linter-check.yml/badge.svg)](https://github.com/KarUrals/java-project-71/actions/workflows/test-and-linter-check.yml)|
|CodeClimate maintainability status:|[![Maintainability](https://api.codeclimate.com/v1/badges/de755853738f0d90fece/maintainability)](https://codeclimate.com/github/KarUrals/java-project-71/maintainability)|
|CodeClimate TestCoverage status:|[![Test Coverage](https://api.codeclimate.com/v1/badges/de755853738f0d90fece/test_coverage)](https://codeclimate.com/github/KarUrals/java-project-71/test_coverage)|

___
## Application launch example
#### Example of comparison two files in different formatters (*[Asciinema Link](https://asciinema.org/a/532406)*):
![Example GIF](https://github.com/KarUrals/java-educational-project-2/blob/main/app/src/main/resources/example.gif)
___

## Frequently used commands

#### Install
```
make install
```

#### Run with a test files to get result in *stylish* format
```
make run-dist-stylish
```

#### Run with a test files to get result in *plain text* format
```
make run-dist-plain
```

#### Run with a test files to get result in *JSON* format
```
make run-dist-json
```

#### Build
```
make build
```

#### Run checkstyle
```
make lint
```

#### Check for Dependency Updates
```
make check-updates
```