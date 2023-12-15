package test.docker.container.TestDocker.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode {

    public static List<String> generateUniqueSubstrings(String s) {
        List<String> uniqueSubstrings = new ArrayList<>();

        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = s.substring(i, j);
                uniqueSubstrings.add(substring);
            }
        }

        return new ArrayList<>(uniqueSubstrings);
    }

    public static void main(String[] args) {
        String inputString = "ABC";
        List<String> uniqueSubstrings = generateUniqueSubstrings(inputString);

        System.out.println("Unique Substrings:"+uniqueSubstrings.size());

    }
}
