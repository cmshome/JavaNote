package com.lxk.jdk8.lambda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * Optional测试
 *
 * @author lxk on 2018/1/30
 */
public class OptionalTest {
    public static void main(String[] args) {
        List<String> friends = Lists.newArrayList("NBA", "hanks", "jim", "jamie", "Bob", "lily", "trump");
        String startingLetter = "NBA";
        pickNameBefore(friends, startingLetter);
        pickNameNow(friends, startingLetter);
    }

    private static void pickNameNow(List<String> friends, String startingLetter) {
        final Optional<String> foundName = friends.stream()
                .filter(name -> name.startsWith(startingLetter))
                .findFirst();
        System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
        foundName.ifPresent(name -> System.out.println("Hello " + name));
    }

    private static void pickNameBefore(final List<String> names, final String startingLetter) {
        String foundName = null;
        for (String name : names) {
            if (name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }
    }

}
