package com.ericlam.mc.playerstate;

import java.util.regex.Pattern;

public class PlayerStateTest {
    public static void main(String[] args) {
        System.out.println(Pattern.compile("\\d{4}").matcher("1234").matches());
    }
}
