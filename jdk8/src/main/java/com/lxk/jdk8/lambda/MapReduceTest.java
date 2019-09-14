package com.lxk.jdk8.lambda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * map（映射）和reduce(归约，化简）
 *
 * @author lxk on 2018/1/30
 */
public class MapReduceTest {
    public static void main(String[] args) {
        List<String> friends = Lists.newArrayList("NBA", "hanks", "jim", "jamie", "Bob", "lily", "trump");

        System.out.println("Total number of characters in all names: " + friends.stream()
                .mapToInt(String::length).sum()
        );

        final Optional<String> aLongName = friends.stream()
                .reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name -> System.out.println(String.format("A longest name: %s", name)));

        final String steveOrLonger = friends.stream()
                .reduce("Steve", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
        System.out.println(steveOrLonger);

        //A List of Strings to Uppercase
        System.out.println(friends.stream().map(String::toUpperCase).collect(joining(", ")));
        System.out.println(friends.stream().map(String::toUpperCase).collect(Collectors.toList()));

        System.out.println(friends.stream().map(String::toLowerCase).collect(Collectors.toList()));


    }
}
