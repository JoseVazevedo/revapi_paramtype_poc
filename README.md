#### Steps to reproduce
1) Clone repo.

2) Execute `mvn install` to generate jar file (jar version 1.0.0).

3) Change version in pom.xml to 1.0.1.

4) Modify `ListWrapper#mergeList` parameter from `List<String>` to `List<Integer>` (and arbitrarily fix any errors that pop up). This should be a breaking change as evidenced by the need to modify the Main class.

5) Execute `mvn install` again to generate new jar file (jar version 1.0.1).

6) Verify no breaking changes were detected by checking `target/revapi_output.txt`.
