# OpenAPI Validator

The OpenAPI Validator allows you to validate your OpenAPI specification by writing a descriptive ruleset in Kotlin!
There is an extensive set of built-in rules and is extensible, as it allows you to add custom rules easily. 

The project is divided into three modules: `core`, `integration-gradle`, and `integration-junit`.  When rules validations, 
you can use either Gradle or JUnit using the respective modules. As a user, the `core` module will be provided as a 
transitive dependency and contains all rules.

