package com.sorting;
import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-dataType")) {
                type = args[i + 1];
            }
        }

        switch (type) {
            case "long":
                greatestNumber(scanner);
                break;
            case "line":
                longestLine(scanner);
                break;
            case "word":
                longestWord(scanner);
                break;
            default:
                System.out.println("Incorrect option! Try again.");
        }
    }

    private static void greatestNumber(Scanner scanner) {
        int totalCount = 0;
        int totalMax = 1;
        long max = Integer.MIN_VALUE;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();

            if (number > max) {
                max = number;
                totalMax = 1;
            } else if (number == max) {
                totalMax++;
            }

            totalCount++;

        }

        int per = 100 / totalCount;

        System.out.println("Total numbers: " + totalCount + ".\n" + "The greatest number: " + max + " (" + totalMax + " time(s) " + per + "%).");
    }

    private static void longestLine(Scanner scanner){
        int totalString = 0;
        int totalMaxString = 1;
        String maxLine = "";


        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            if(line.length() > maxLine.length()){
                maxLine = line;
                totalMaxString = 1;
            }
            else if(line.equals(maxLine)){
                totalMaxString++;
            }
            else if(line.length() == maxLine.length()){
                int rs = line.compareTo(maxLine);

                if(rs > 0){
                    maxLine = line;
                    totalMaxString++;
                }
            }

            totalString++;
        }

        int per = 100 / totalString;

        System.out.println("Total lines: " + totalString + ".\n" + "The longest line:\n" + maxLine + "\n(" + totalMaxString + " time(s), " + per + "%).");
    }

    private static void longestWord(Scanner scanner) {
        int totalWord = 0;
        int totalMaxWord = 1;
        String maxWord = "";

        while(scanner.hasNext()){
            String word = scanner.next();

            if(word.length() > maxWord.length()){
                maxWord = word;
                totalMaxWord = 1;
            }
            else if(word.equals(maxWord)){
                totalMaxWord++;
            }
            else if(word.length() == maxWord.length()){
                int rs = word.compareTo(maxWord);

                if(rs > 0){
                    maxWord = word;
                    totalMaxWord++;
                }
            }

            totalWord++;
        }

        int per = 100 / totalWord;

        System.out.println("Total words: " + totalWord + ".\n The longest word: " + maxWord + " (" + totalMaxWord + " time(s), " + per + "%).");
    }

}

