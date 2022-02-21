# Chasm Plugin Test Project

This is a simple test project for [Chasm](https://github.com/QuiltMC/chasm) you can clone and experiment with.
Chasm is included as a submodule, so when cloning you should run:
```
git clone --recurse-submodules git@github.com:CheaterCodes/chasm-gradle-plugin-test.git
```

# The Example

In this repository, you'll find the main entry point in `src/main/java/codes/cheater/Main.java`.
In the `main` function, an instance of `ExampleClass` is created.
Then, we try to obtain the secret value hidden in that class.

```java
public class Main {
    public static void main(String[] args) {
        ExampleClass exampleClass = new ExampleClass();
        String secretValue = exampleClass.getSecretValue();
        System.out.println(secretValue);
    }
}
```

If you look in the class definition in `subproject/src/main/java/codes/cheater/example/ExampleClass`,
you'll see that the `getSecretValue` method is private.

```java
public class ExampleClass {
    private String getSecretValue() {
            return "They'll never get me!";
        }
}
```
However, by placing a transformer somewhere in the `org/quiltmc/chasm/transformers` resource directory,
we can change the access flag.
The given project should compile, even though we're accessing a private method.

```
{
    id: "secret-value-widener",
    target_class_name: "codes/cheater/example/ExampleClass",
    target_method_name: "getSecretValue",
    target_class: classes.<c -> c.name = $.target_class_name>.[0],
    target_method: $.target_class.methods.<m -> m.name = $.target_method_name>.[0],
    transformations: [
        {
            target: $.target_method.access,
            apply: args -> 1
        },
    ],
}
```

Note: The project will compile, but not run at this point in time.