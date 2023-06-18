[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=stefankoppier_openapi-validator&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=stefankoppier_openapi-validator)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=stefankoppier_openapi-validator&metric=coverage)](https://sonarcloud.io/summary/new_code?id=stefankoppier_openapi-validator)

# OpenAPI Validator

The OpenAPI Validator tool allows you to validate your OpenAPI specification by writing a descriptive ruleset in Kotlin!
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
    paths {
        all {
            post
        }
    }
}
```

### Extensibility

## Usage

The main way to use the ruleset is by using JUnit. 

### JUnit Integration

Add the package TODO to your Gradle dependencies.

```kotlin
```

Then extend your test class with `@ExtendWith(OpenAPIValidationExtension::class)` to add loading of a specification. The
specification can be loaded using the annotation `@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")` to 
specify the location of the specification. The specification will be passed as an argument to the constructor of the test
class. For example
```kotlin
@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class MyTestClass(private val document: OpenAPI) {

    @Test
    fun `this is my test`() {
        assertDocumentIsValidFor(document, openAPI {
            info { title { exactly("OpenAPI Petstore") } }
        })
    }
}
```

## Roadmap

- Add support for more integration options than JUnit.
- Add test generation. e.g.
  - Validate if another specification has breaking changes relative to the existing specification.
  - Validate if another specification exactly matches an existing specification. 