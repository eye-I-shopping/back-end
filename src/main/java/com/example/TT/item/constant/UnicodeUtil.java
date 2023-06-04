package com.example.TT.item.constant;

public class UnicodeUtil{
    public static String convertToUnicodeEscapes(String string) {
        StringBuilder escapedString = new StringBuilder(string.length());

        for (char c : string.toCharArray()) {
            if (c >= 128) {
                escapedString.append(String.format("\\u%04X", (int) c));
            } else {
                escapedString.append(c);
            }
        }

        return escapedString.toString();
    }
}
