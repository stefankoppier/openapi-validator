# OpenAPI Validator

## Features

### Preconditions

### Extensibility

## Usage

### JUnit Integration

```kotlin
@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIValidationExtensionTest(private val document: OpenAPI) {

    @Test
    fun `this is my test`() {
        assertDocumentIsValidFor(document, openAPI("My specification") {
            info { title { exactly("OpenAPI Petstore") } }
        })
    }
}
```

## Roadmap

- Add support for more integration options than JUnit.
- Add test generation for a given specification. e.g.
  - Breaking changes
  - Exact match