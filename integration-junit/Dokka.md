# Module integration-junit

Contains the infrastructure for integration with JUnit.

One can use the package by adding the dependency <TODO> and add a test. For example
```kotlin
@ExtendWith(OpenAPIValidationExtension::class)
@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")
class OpenAPIValidationExtensionTest {

    @Test
    fun `my petstore test`() {
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

In the test above, we have some points of interest. To add support for validation in your test class, add the two 
annotations: `@ExtendWith(OpenAPIValidationExtension::class)` and 
`@OpenAPITest(relativeUrl = "src/test/resources/petstore.yaml")`. Note that `@OpenAPITest` can be written at both the
class level and the function level. These two extensions together wil inject the OpenAPI specification into your tests,
on which you can assert using `assertDocumentIsValidFor`, which takes a rule as an argument.

The available rules can be found in the documentation of the core module.