#### What is this?
This is a repository intended for [Revapi](https://github.com/revapi/revapi), in order to help fixing a bug.

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

5) Execute `mvn install` again to generate new jar file (jar version 1.0.1).

6) Verify no breaking changes were detected by checking `target/revapi_output.txt`.

    Console should print:
    ```
    [INFO] Comparing [com.feedzai:revapi-paramtype-poc:jar:1.0.0] against [com.feedzai:revapi-paramtype-poc:jar:1.0.1].
    [INFO] API checks completed without failures.
    ```
    
    And `target/revapi_output.txt` should read something like:
    ```
    Analysis results
    ----------------
    
    Old API: 
    New API: com.feedzai:revapi-paramtype-poc:jar:1.0.0
    
    Analysis results
    ----------------
    
    Old API: com.feedzai:revapi-paramtype-poc:jar:1.0.0
    New API: com.feedzai:revapi-paramtype-poc:jar:1.0.1
    ```
