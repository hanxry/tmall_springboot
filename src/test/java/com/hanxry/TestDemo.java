package com.hanxry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hanxry
 * @since 2021/10/20
 */
public class TestDemo {
    static class Name {
        String name;
    }

    public static void main(String[] args) {
        LocalDateTime parse = LocalDateTime.parse("2021-01-21 00:44:32", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);
    }
}
