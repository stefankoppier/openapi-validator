[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=stefankoppier_openapi-validator&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=stefankoppier_openapi-validator)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=stefankoppier_openapi-validator&metric=coverage)](https://sonarcloud.io/summary/new_code?id=stefankoppier_openapi-validator)

# OpenAPI Validator

The OpenAPI Validator allows you to validate your OpenAPI specification by writing a descriptive ruleset in Kotlin!
There is an extensive set of built-in rules and is extensible, as it allows you to add custom rules easily. 

Visit [the project documentation](https://stefankoppier.github.io/openapi-validator/) for a complete overview of the 
functionality.

## Features

### Rules
All properties of the OpenAPI specification have a corresponding rule. For example, if you want to validate that the
path `/pet/findByStatus` has a get operation with the required query parameter `status` you can write
```kotlin
openAPI {
    paths {
        required()
        path(named = "/pet/findByStatus") {
            get { 
                parameters {
                    parameter(named = "status") {
                      required()
                      `in` { exactly("query") }
                    }
                }
            }
        }
    }
}
```

### Quantifier Rules

### Preconditions
There is support for preconditions. The general precondition `given` validates a given rule only if
the predicate is true. For example
```kotlin
openAPI {
    info {
        given({ it != null && it.length > 20 }) {
            description { lowercase() }
        }
    }
}
```
states that the field `description` of the `info` field must lowercase when it is not null, and it's length is
greater than 20.

There is also the `since` precondition, which states that something should hold only if the given date is in
the past. For example
```kotlin
openAPI {
    info {
        since(LocalDate.of(2024, 1, 1)) {
            title { lowercase() }
        }
    }
}
```
states that the field `title` of the `info` field must be in lowercase after January 1ˢᵗ of 2024.  

## Usage

The OpenAPI Validator allows for two ways to execute the validation: via Gradle or via JUnit.

### Gradle Integration

Apply the plugin
```kotlin
plugins {
    id("io.github.stefankoppier.openapi.validator") version "0.0.1"
}
```
and then configure the document to be verified using the given rules using
```kotlin
openAPIValidate {
    document.set(uri("petstore.yaml"))
    rules.set(
        openAPI {
            info {
                title { exactly("OpenAPI Petstore") }
            }
        }
    )
}
```

### JUnit Integration

Add the package to your dependencies.

Then extend your test class with `@ExtendWith(OpenAPIValidationExtension::class)` to add loading of a specification. 
The specification can be loaded using the annotation `@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")` to 
specify the location of the specification. For example
```kotlin
@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIValidationExtensionTest {

    @Test
    fun `my test`() {
        assertDocumentIsValidFor {
            openAPI("My specification") {
                info {
                    title { exactly("OpenAPI Petstore") }
                }
            }
        }
    }
}
```