#### What is this?
This is a repository intended for [Revapi](https://github.com/revapi/revapi), in order to help fixing a bug where changes to a parameter type parameter are not detected.

Relevant issue can be found [here](https://github.com/revapi/revapi/issues/149).

#### Steps to reproduce
1) Clone repo.

2) Execute `mvn install` to generate jar file (jar version 1.0.0). `target/revapi_output.txt` file should look like: 
    ```
    Analysis results
    ----------------
    
    Old API: 
    New API: com.feedzai:revapi-paramtype-poc:jar:1.0.0
    ```
3) Change version in pom.xml to 1.0.1.

4) Modify `ListWrapper#mergeList` parameter from `List<String>` to `List<Integer>` and arbitrarily fix any errors that pop up (e.g. by commenting the method body and its usage in the main class). This should be a breaking change as evidenced by the need to modify the Main class.

5) Execute `mvn clean install` to generate new jar file (jar version 1.0.1).

6) Verify no breaking changes were detected by checking `target/revapi_output.txt`.

    Console should print:
    ```
    [INFO] Comparing [com.feedzai:revapi-paramtype-poc:jar:1.0.0] against [com.feedzai:revapi-paramtype-poc:jar:1.0.1].
    [INFO] API checks completed without failures.
    ```
    
    And `target/revapi_output.txt` should (incorrectly) read something like:
    ```
    Analysis results
    ----------------
    
    Old API: com.feedzai:revapi-paramtype-poc:jar:1.0.0
    New API: com.feedzai:revapi-paramtype-poc:jar:1.0.1
    ```

#### Sanity Check
In order to test Revapi is working normally, we did the following:
1) Clone repo.

2) Execute `mvn install` to generate jar file (jar version 1.0.0). `target/revapi_output.txt` file should look like: 
    ```
    Analysis results
    ----------------
    
    Old API: 
    New API: com.feedzai:revapi-paramtype-poc:jar:1.0.0
    ```
3) Change version in pom.xml to 1.0.1.

4) Modify `ListWrapper#mergeList` parameter from `List<String>` to `int` and arbitrarily fix any errors that pop up (e.g. by commenting the method body and its usage in the main class). This should be a breaking change as evidenced by the need to modify the Main class.

5) Execute `mvn clean install` to generate new jar file (jar version 1.0.1).

6) Verify that breaking changes were correctly detected by checking `target/revapi_output.txt`.

    Console should print:
    ```
    [INFO] Comparing [com.feedzai:revapi-paramtype-poc:jar:1.0.0] against [com.feedzai:revapi-paramtype-poc:jar:1.0.1].
    [INFO] API problems found but letting the build pass as configured.
    ```
    
    And `target/revapi_output.txt` should (correctly) read something like:
    ```
    Analysis results
    ----------------
    
    Old API: com.feedzai:revapi-paramtype-poc:jar:1.0.0
    New API: com.feedzai:revapi-paramtype-poc:jar:1.0.1
    old: parameter void util.ListWrapper::mergeList(===java.util.List<java.lang.String>===)
    new: parameter void util.ListWrapper::mergeList(===int===)
    java.method.parameterTypeChanged: The type of the parameter changed from 'java.util.List<java.lang.String>' to 'int'.
    SOURCE: POTENTIALLY_BREAKING, BINARY: BREAKING
    ```
