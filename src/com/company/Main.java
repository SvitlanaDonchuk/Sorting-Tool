package com.company;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalCount = 0;
        int totalMax = 1;
        long max = Integer.MIN_VALUE;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();

            if(number > max){
                max = number;
                totalMax = 1;
            }
            else if(number == max){
                totalMax ++;
            }

            totalCount++;

        }

        System.out.println("Total numbers: " + totalCount + ".\n" + "The greatest number: " + max + " (" + totalMax + " time(s))." );
    }
}
