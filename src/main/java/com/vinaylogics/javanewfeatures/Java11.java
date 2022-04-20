package com.vinaylogics.javanewfeatures;



import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Java11 {

    public static void main(String[] args) throws IOException, InterruptedException, ScriptException {
        // strings
        stringFeatures();

        // lambdas
        lambdasFeatures();

        // httpclient
        httpClientFeatures();

        // unicode 10 -> 15000 new characters
        unicode10Features();

        // JavascriptEngine ... nashorn // replace with rhino and now it is deprecated and
        // new engine graalvm js engine has released
        javascriptEngineFeatures();

        // FileMethods
        filesFeatures();

        // collection to array features
        collectionToArrayFeatures();

    }

    private static void collectionToArrayFeatures() {
        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String[] sampleArray = sampleList.toArray(String[]::new);
        assertTrue(Arrays.stream(sampleArray).allMatch(sampleList::contains));
    }

    private static void filesFeatures() throws IOException {
        Path filePath = Files.writeString(Files.createTempFile( "demo", ".txt"), "Sample text");
        String fileContent = Files.readString(filePath);
        assertThat(fileContent, is(equalTo("Sample text")));
    }

    private static void javascriptEngineFeatures() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        var data = engine.eval("print('help, I am dying'); testString(); function testString() { return 'hello'};");
        System.out.println(data);
    }

    private static void unicode10Features() {
        System.out.println("\u20B3"); //cryptocurrencies
    }

    private static void httpClientFeatures() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.google.com"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    private static void lambdasFeatures() {
        Consumer<BigDecimal> moneyConsumerPrev = (money) -> System.out.println("I got this much money! = " + money);
        Consumer<BigDecimal> moneyConsumer = (var money) -> System.out.println("I got this much money! = " + money);
        Consumer<BigDecimal> moneyConsumer2 = (@Deprecated var money) -> System.out.println("I got this much money! = " + money);
        List<String> sampleList = Arrays.asList("Java", "\n \n", "Kotlin", " ");
        List<String> withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        withoutBlanks.forEach(System.out::println);
    }

    private static void stringFeatures() {
        var chant = "    Java11Features!!     ";
        System.out.println("chant.repeat(3) = " + chant.repeat(3));
        System.out.println("chant.isBlank() = " + chant.isBlank());
        System.out.println("chant.strip() = " + chant.strip()); // chant.trim();

        var chant2 = "    Java11Features!! \n Java     ";
        System.out.println("chant2.lines() = ");
        chant2.lines().forEach(System.out::println);
        String multilineString = "Java11 \n \n features \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
        lines.forEach(System.out::println);
    }
}
