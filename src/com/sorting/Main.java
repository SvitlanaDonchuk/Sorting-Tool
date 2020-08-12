package com.sorting;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;


public class Main {
    public static void main(final String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String s = "-sortingType";
        String s1 = "-dataType";
        String s2 = "-inputFile";
        String s3 = "-outputFile";

        DataType dataType = null;
        SortingType sortingType = null;
        String inputFileName = null;
        String outputFileName = null;

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals(s2)) {
                inputFileName = args[i + 1];
            } else if (args[i].equals(s3)) {
                outputFileName = args[i + 1];
            }
        }

        File file = new File("./" + outputFileName);
        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals(s)) {
                try {
                    sortingType = SortingType.findByLabel(args[i + 1]);
                } catch (Exception e) {
                    writer.write("No sorting type defined!");
                }
            } else if (args[i].equals(s1)) {
                dataType = DataType.findByLabel(args[i + 1]);
            }
        }

        if (sortingType == null) {
            sortingType = SortingType.NATURAL;
//            writer.write("No sorting type defined!");
        }

        if (dataType == null) {
            writer.write("Incorrect option! Try again.");
            writer.close();
            System.out.println(outputFileName);
            System.exit(1);
        }

        switch (dataType){
            case LONG:
                greatestNumber(scanner, sortingType, inputFileName, file);
                break;
            case LINE:
                longestLine(scanner, sortingType, inputFileName, file);
                break;
            case WORD:
                longestWord(scanner, sortingType, inputFileName, file);
                break;
            default: {
                writer.write("Incorrect option! Try again.");
                System.exit(1);
            }
        }

        if(outputFileName != null) {
            try (Scanner scannerFile = new Scanner(file)) {
                System.out.println(outputFileName);
                while (scannerFile.hasNext()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                writer.write("No file found");
            }
        }

        writer.close();
    }

    private static void greatestNumber(Scanner scanner, SortingType sortingType, String inputFileName, File file) throws IOException{
        FileWriter writer = new FileWriter(file);

        if (sortingType == SortingType.NATURAL) {

            List<String> arrayStrings = new ArrayList<>();
            List<Integer> arrayList = new ArrayList<>();
            int totalCount = 0;

            if (inputFileName == null) {
                while (scanner.hasNext()) {
                    String string = scanner.next();
                    arrayStrings.add(string);
                }
            } else {
                File inputFile = new File("/Users/svitlanadonchuk/projects/Sorting Tool/" + inputFileName);
                try(Scanner scannerFile = new Scanner(inputFile)){
                    while (scannerFile.hasNext()){
                        String string = scannerFile.next();
                        arrayStrings.add(string);
                    }
                } catch (FileNotFoundException e){
                    writer.write("Incorrect option! Try again.");
                }

            }

            for(String str : arrayStrings) {
                try {
                    int num = Integer.parseInt(str);
                    arrayList.add(num);
                    totalCount++;
                } catch (Exception e) {
                    writer.write('\"' + str + '\"' + " isn't a long. It's skipped.");
                }
            }

            int[] arr = new int[arrayList.size()];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = arrayList.get(i);
            }

            margeSortIntegers(arr, 0, arr.length);


            System.out.print("Total numbers: " + totalCount + "\nSorted data: ");

            for (int num : arr){
                System.out.print(num +  " ");
            }
            writer.close();

            return;
        }

        if (sortingType == SortingType.BY_COUNT) {

            List<Integer> arrayList = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();

            int totalCount = 0;

            if (inputFileName == null) {
                while (scanner.hasNextLong()) {
                    int number = scanner.nextInt();
                    arrayList.add(number);
                    totalCount++;
                }
            } else {
                File inputFile = new File("./" + inputFileName);
                try(Scanner scannerFile = new Scanner(inputFile)){
                    while (scannerFile.hasNextLong()){
                        int number = scannerFile.nextInt();
                        arrayList.add(number);
                        totalCount++;
                    }
                } catch (FileNotFoundException e){
                    writer.write("Incorrect option! Try again.");
                }

            }

            for (Integer val : arrayList){
                Integer num = map.get(val);

                if(num == null){
                    map.put(val, 1);
                } else {
                    map.put(val, ++num);
                }
            }

            Map<Integer, Integer> sorted = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            System.out.print("Total numbers: " + totalCount + ".");

            for(Integer key : sorted.keySet()){
                Integer val = sorted.get(key);
                Long percentage = Math.round((double)val / totalCount * 100);

                System.out.print(String.format("\n%s: %s  time(s), %s%%", key, val, percentage));

            }

            writer.close();
        }
    }

    private static void longestWord(Scanner scanner, SortingType sortingType, String inputFile, File file) throws IOException {
        FileWriter writer = new FileWriter(file);

        if (sortingType == SortingType.NATURAL) {
            List<String> arrayList = new ArrayList<>();

            int totalWords = 0;
            String temp;

            if (inputFile == null) {
                while (scanner.hasNext()) {
                    String string = scanner.next();
                    arrayList.add(string);
                    totalWords++;
                }
            } else {
                File fileInput = new File("./" + inputFile);
                try(Scanner scannerFile = new Scanner(fileInput)){
                    while (scannerFile.hasNext()){
                        String string = scannerFile.next();
                        arrayList.add(string);
                        totalWords++;
                    }
                } catch (FileNotFoundException e){
                    writer.write("Incorrect option! Try again.");
                }

            }

            String[] arr = new String[arrayList.size()];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = arrayList.get(i);
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if(arr[i].compareTo(arr[j]) > 0){
                        temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }

            System.out.print("Total words: " + totalWords + "\nSorted data: ");
            for (String s : arr) {
                System.out.print(s + " ");
            }

            writer.close();
            return;
        }

        if(sortingType == SortingType.BY_COUNT) {

            List<String> arrayList = new ArrayList<>();
            Map<String, Integer> map = new TreeMap<>();

            int totalWords = 0;

            if (inputFile == null) {
                while (scanner.hasNext()) {
                    String string = scanner.next();
                    arrayList.add(string);
                    totalWords++;
                }
            } else {
                File fileInput = new File("./" + inputFile);
                try(Scanner scannerFile = new Scanner(fileInput)){
                    while (scannerFile.hasNext()){
                        String string = scannerFile.next();
                        arrayList.add(string);
                        totalWords++;
                    }
                } catch (FileNotFoundException e){
                    writer.write("Incorrect option! Try again.");
                }

            }

            for(String val : arrayList) {
                Integer num = map.get(val);

                if (num == null) {
                    map.put(val, 1);
                } else {
                    map.put(val, ++num);
                }
            }

            Map<String, Integer> sorted = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            System.out.print("Total words: " + totalWords + ".");

            for(String key : sorted.keySet()){
                Integer val = sorted.get(key);
                Long percentage = Math.round((double)val / totalWords * 100);

                System.out.print(String.format("\n%s: %s  time(s), %s%%", key, val, percentage));
            }

            writer.close();
        }

    }

    private static void longestLine(Scanner scanner, SortingType sortingType, String inputFile, File file) throws IOException{
        FileWriter writer = new FileWriter(file);

        if (sortingType == SortingType.NATURAL) {

            List<String> arrayList = new ArrayList<>();

            int totalLines = 0;
            String temp;

            if (inputFile == null) {
                while (scanner.hasNext()) {
                    String string = scanner.nextLine();
                    arrayList.add(string);
                    totalLines++;
                }
            } else {
                File fileInput = new File("./" + inputFile);
                try(Scanner scannerFile = new Scanner(fileInput)){
                    while (scannerFile.hasNextLine()){
                        String string = scannerFile.nextLine();
                        arrayList.add(string);
                        totalLines++;
                    }
                } catch (FileNotFoundException e){
                    writer.write("Incorrect option! Try again.");
                }

            }

            String[] arr = new String[arrayList.size()];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = arrayList.get(i);
            }

            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = i + 1; j < arrayList.size(); j++) {
                    if(arr[i].length() < arr[j].length()){
                        temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }

            System.out.println("Total lines: " + totalLines + "\nSorted data: ");

            for (String s : arr) {
                System.out.println(s + " ");
            }
            writer.close();
            return;
        }

        if(sortingType == SortingType.BY_COUNT) {
            List<String> arrayList = new ArrayList<>();
            Map<String, Integer> map = new TreeMap<>();

            int totalLines = 0;

            if (inputFile == null) {
                while (scanner.hasNext()) {
                    String string = scanner.nextLine();
                    arrayList.add(string);
                    totalLines++;
                }
            } else {
                File fileInput = new File("./" + inputFile);
                try(Scanner scannerFile = new Scanner(fileInput)){
                    while (scannerFile.hasNextLine()){
                        String string = scannerFile.nextLine();
                        arrayList.add(string);
                        totalLines++;
                    }
                } catch (FileNotFoundException e){
                    writer.write("Incorrect option! Try again.");
                }

            }

            for(String val : arrayList){
                Integer num = map.get(val);

                if(num == null){
                    map.put(val, 1);
                } else {
                    map.put(val, ++num);
                }
            }

            Map<String, Integer> sorted = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            System.out.print("Total words: " + totalLines + ".");

            for(String key : sorted.keySet()){
                Integer val = sorted.get(key);
                Long percentage = Math.round((double)val / totalLines * 100);

                System.out.print(String.format("\n%s: %s  time(s), %s%%", key, val, percentage));

            }

            writer.close();
        }
    }

    public static void margeSortIntegers(int[] list, int leftIncl, int rightExcl){
        if(rightExcl <= leftIncl + 1){
            return;
        }

        int mid = leftIncl + (rightExcl - leftIncl) / 2;

        margeSortIntegers(list, leftIncl, mid);
        margeSortIntegers(list, mid, rightExcl);

        marge(list, leftIncl, mid, rightExcl);
    }

    private static void marge(int[] list, int left, int mid, int right){
        int i = left;
        int j = mid;
        int k = 0;

        int[] temp = new int[right - left];

        while(i < mid && j < right){
            if(list[i] <= list[j]){
                temp[k] = list[i];
                i++;
            }
            else{
                temp[k] = list[j];
                j++;
            }
            k++;
        }

        for (; i < mid; i++, k++) {
            temp[k] = list[i];
        }

        for (; j < right; j++, k++) {
            temp[k] = list[j];
        }

        System.arraycopy(temp, 0, list, left, temp.length);
    }
}

enum SortingType {
    NATURAL("natural"),
    BY_COUNT("byCount")
    ;

    private String label;

    SortingType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static SortingType findByLabel(String label) {
        for (SortingType type : SortingType.values()) {
            if (label.equalsIgnoreCase(type.getLabel())) {
                return type;
            }
        }
        return null;
    }
}

enum DataType {
    LINE("line"),
    WORD("word"),
    LONG("long")
    ;

    private String label;

    DataType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static DataType findByLabel(String label) {
        for (DataType type : DataType.values()) {
            if (label.equalsIgnoreCase(type.getLabel())) {
                return type;
            }
        }
        return null;
    }
}