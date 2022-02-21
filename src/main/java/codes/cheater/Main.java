package codes.cheater;

import codes.cheater.example.ExampleClass;

public class Main {
    public static void main(String[] args) {
        ExampleClass exampleClass = new ExampleClass();
        String secretValue = exampleClass.getSecretValue();
        System.out.println(secretValue);
    }
}
