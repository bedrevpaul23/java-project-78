### Hexlet tests and linter status:
[![Actions Status](https://github.com/bedrevpaul23/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/bedrevpaul23/java-project-78/actions)

### Build status:
[![Build](https://github.com/bedrevpaul23/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/bedrevpaul23/java-project-78/actions/workflows/main.yml)

### Maintainability and test coverage:
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bedrevpaul23_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=bedrevpaul23_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bedrevpaul23_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=bedrevpaul23_java-project-78)

# Data Validator

Data Validator is a Java library for validating strings, numbers, maps, and nested map values with a fluent interface.

Supported schemas:

- StringSchema
- NumberSchema
- MapSchema

## Requirements

- Java 21
- Gradle

## Build

```bash
cd app
./gradlew clean build
```

## Test

```bash
cd app
./gradlew clean check
```

## Usage

```text
import hexlet.code.Validator;

var validator = new Validator();

var stringSchema = validator.string().required().minLength(3);
stringSchema.isValid("hexlet"); // true
stringSchema.isValid(""); // false

var numberSchema = validator.number().required().positive();
numberSchema.isValid(10); // true
numberSchema.isValid(-10); // false

var mapSchema = validator.map().required().sizeof(1);
mapSchema.isValid(Map.of("key", "value")); // true
```

## Nested map validation

```text
import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

var validator = new Validator();
var schema = validator.map();

Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", validator.string().required());
schemas.put("lastName", validator.string().required().minLength(2));

schema.shape(schemas);

Map<String, String> human = new HashMap<>();
human.put("firstName", "John");
human.put("lastName", "Smith");

schema.isValid(human); // true
```
