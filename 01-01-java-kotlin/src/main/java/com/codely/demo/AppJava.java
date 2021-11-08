package com.codely.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppJava {
    public String toUpperCaseBreed(String lowercaseBreed) {
        return lowercaseBreed.toUpperCase();
    }

    public void execute() {
        System.out.println("=======>START JAVA CODE<=======");
        List<String> list = new ArrayList<>();
        list.add("SIAMES");
        list.add("AZUL RUSO");
        list.add("COMUN EUROPEO");
        App appKotlin = new App();
        System.out.println(list.stream().map((
                appKotlin::toLowerCaseBreed
        )));
        System.out.println(list.stream().map((
                appKotlin::toLowerCaseBreed
        )).filter(
                breed -> breed.contains("europeo")
        ));
        System.out.println(list.stream().map((
                appKotlin::toLowerCaseBreed
        )).collect(Collectors.toList()));
        System.out.println(list.stream().map((
                appKotlin::toLowerCaseBreed
        )).filter(
                breed -> breed.contains("europeo")
        ).collect(Collectors.toList()));
        System.out.println("=======>END JAVA CODE<=======");
    }
}
