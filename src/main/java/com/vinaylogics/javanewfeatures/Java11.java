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
import java.util.function.Consumer;

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

    }

    private static void javascriptEngineFeatures() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        var data = engine.eval("print('help, I am dying'); testString(); function testString() { return 'hello'};");
        System.out.println(data);
    }

    private static void unicode10Features() {
        System.out.println("\u20BF"); //cryptocurrencies
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

    }

    private static void stringFeatures() {
        var chant = "    Java11Features!!     ";
        System.out.println("chant.repeat(3) = " + chant.repeat(3));
        System.out.println("chant.isBlank() = " + chant.isBlank());
        System.out.println("chant.strip() = " + chant.strip()); // chant.trim();

        var chant2 = "    Java11Features!! \n Java     ";
        System.out.println("chant2.lines() = ");
        chant2.lines().forEach(System.out::println);
    }
}
