package com.sorting;

import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s = "-sortingType";
        String s1 = "-dataType";

        DataType dataType = null;
        SortingType sortingType = null;


        for (int i = 0; i < args.length; i++) {

            if (args[i].equals(s)) {
                sortingType = SortingType.findByLabel(args[i + 1]);
            } else if (args[i].equals(s1)) {
                dataType = DataType.findByLabel(args[i + 1]);
            }
        }

        if (sortingType == null) {
            sortingType = SortingType.NATURAL;
        }

        if (dataType == null) {
            System.out.println("Incorrect option! Try again.");
            System.exit(1);
        }

        switch (dataType){
            case LONG:
                greatestNumber(scanner, sortingType);
                break;
            case LINE:
                longestLine(scanner, sortingType);
                break;
            case WORD:
                longestWord(scanner, sortingType);
                break;
            default: {
                System.out.println("Incorrect option! Try again.");
                System.exit(1);
            }
        }

    }

    private static void greatestNumber(Scanner scanner, SortingType sortingType) {

        if (sortingType == SortingType.NATURAL) {

            List<Integer> arrayList = new ArrayList<>();
            int totalCount = 0;

            while (scanner.hasNextLong()) {
                int number = scanner.nextInt();
                arrayList.add(number);
                totalCount++;

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
            return;

        }

        if (sortingType == SortingType.BY_COUNT) {

            List<Integer> arrayList = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();

            int totalCount = 0;

            while (scanner.hasNextLong()) {
                int number = scanner.nextInt();
                arrayList.add(number);
                totalCount++;

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

            System.out.println("Total numbers: " + totalCount + ".");

            for(Integer key : sorted.keySet()){
                Integer val = sorted.get(key);
                Long percentage = Math.round((double)val / totalCount * 100);

                System.out.println(String.format("%s: %s  time(s), %s%%", key, val, percentage));

            }
            return;
        }

        System.err.println("Not found sorting type " + sortingType);

    }
    private static void longestWord(Scanner scanner, SortingType sortingType) {

        if (sortingType == SortingType.NATURAL) {
            List<String> arrayList = new ArrayList<>();

            int totalWords = 0;
            String temp;

            while(scanner.hasNext()){
                String string = scanner.next();
                arrayList.add(string);
                totalWords++;
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
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            return;

        }

        if(sortingType == SortingType.BY_COUNT) {

            List<String> arrayList = new ArrayList<>();
            Map<String, Integer> map = new TreeMap<>();

            int totalWords = 0;

            while(scanner.hasNext()){
                String string = scanner.next();
                arrayList.add(string);
                totalWords++;
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

            System.out.println("Total words: " + totalWords + ".");
            for(String key : sorted.keySet()){
                Integer val = sorted.get(key);
                Long percentage = Math.round((double)val / totalWords * 100);

                System.out.println(String.format("%s: %s  time(s), %s%%", key, val, percentage));

            }
        }

    }

    private static void longestLine(Scanner scanner, SortingType sortingType) {


        if (sortingType == SortingType.NATURAL) {

            List<String> arrayList = new ArrayList<>();

            int totalLines = 0;
            String temp;

            while(scanner.hasNext()){
                String string = scanner.nextLine();
                arrayList.add(string);
                totalLines++;
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
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i] + " ");
            }
            return;
        }

        if(sortingType == SortingType.BY_COUNT) {

            List<String> arrayList = new ArrayList<>();
            Map<String, Integer> map = new TreeMap<>();

            int totalLines = 0;

            while(scanner.hasNext()){
                String string = scanner.nextLine();
                arrayList.add(string);
                totalLines++;
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

            System.out.println("Total words: " + totalLines + ".");
            for(String key : sorted.keySet()){
                Integer val = sorted.get(key);
                Long percentage = Math.round((double)val / totalLines * 100);

                System.out.println(String.format("%s: %s  time(s), %s%%", key, val, percentage));

            }

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
