import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> testListRandom = loadRandomList();
        ArrayList<Integer> testListNoUnique = loadNoUniqueList();
        ArrayList<Integer> testListUnique = loadUniqueList();
        //runSlowCheckMethod(testListRandom, testListNoUnique, testListUnique);
        //runMainCheckMethod(testListNoUnique, testListUnique, testListRandom);
        HashMap<Integer, Integer> testMap = checkHashMap(testListRandom);
    }

    private static HashMap<Integer, Integer> checkHashMap(ArrayList<Integer> list) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int integer : list) {
            if (map.containsKey(integer)) {
                int currentValue = map.get(integer);
                map.put(integer, currentValue + 1);
            } else {
                map.put(integer, 1);
            }
        }
        printHighestValue(map);
        return map;
    }

    //TODO Visar fel om det är flera tal är ett som är högsta värdet
    private static void printHighestValue(HashMap<Integer, Integer> map) {
        final int[] highestValue = {0};
        final int[] highestKey = {0};
        map.forEach((key, value) -> {
            if (value > highestValue[0]) {
                highestValue[0] = value;
                highestKey[0] = key;
            }
        });
        System.out.println("map = " + map);
        System.out.println("Talet som upprepas flest gånger är: " + highestKey[0]);
        System.out.println("Det upprepades totalt: " +highestValue[0] + " gånger");
    }

    private static void runSlowCheckMethod(ArrayList<Integer> testListRandom, ArrayList<Integer> testListNoUnique, ArrayList<Integer> testListUnique) {
        ArrayList<Integer> uniqueNumbersRandom = getUniqueInefficientWay(testListRandom);
        System.out.println("uniqueNumbersRandom = " + uniqueNumbersRandom);
        ArrayList<Integer> uniqueNumbersNoUnique = getUniqueInefficientWay(testListNoUnique);
        System.out.println("uniqueNumbersNoUnique = " + uniqueNumbersNoUnique);
        ArrayList<Integer> uniqueNumbersUniques = getUniqueInefficientWay(testListUnique);
        System.out.println("uniqueNumbersUniques = " + uniqueNumbersUniques);
    }

    private static void runMainCheckMethod(ArrayList<Integer> testListNoUnique, ArrayList<Integer> testListUnique, ArrayList<Integer> testListRandom) {
        ArrayList<Integer> noUniqueNumbers = checkUnique(testListNoUnique);
        System.out.println("noUniqueNumbers = " + noUniqueNumbers);
        ArrayList<Integer> uniqueNumbers = checkUnique(testListUnique);
        System.out.println("uniqueNumbers = " + uniqueNumbers);
        ArrayList<Integer> uniqueNumbersRandom = checkUnique(testListRandom);
        System.out.println("uniqueNumbersRandom.size() = " + uniqueNumbersRandom.size());
        System.out.println("uniqueNumbersRandom = " + uniqueNumbersRandom);
    }

    //Med Aizos hjälp - 2 listor behövs för att inte ta bort ett nummer i unika listan och
    // sen lägga tillbaka det igen om det dyker upp senare
    private static ArrayList<Integer> checkUnique(ArrayList<Integer> list) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        ArrayList<Integer> seenNumbers = new ArrayList<>();
        for (int integer : list) {
            if (seenNumbers.contains(integer)) {
                uniqueNumbers.remove(integer);
            } else {
                uniqueNumbers.add(integer);
                seenNumbers.add(integer);
            }
        }
        Collections.sort(uniqueNumbers);
        return uniqueNumbers;
    }


    private static ArrayList<Integer> loadRandomList() {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int randomNumber = random.nextInt(100) + 1;
            list.add(randomNumber);
        }
        return list;
    }

    private static ArrayList<Integer> loadNoUniqueList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        return list;
    }

    private static ArrayList<Integer> loadUniqueList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        return list;
    }

    //Aizo säger att denna funktion inte är optimal på större listor
    private static ArrayList<Integer> getUniqueInefficientWay(ArrayList<Integer> list) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        for (int number : list) {
            if (Collections.frequency(list, number) == 1) {
                uniqueNumbers.add(number);
            }
        }
        Collections.sort(uniqueNumbers);
        return uniqueNumbers;
    }

}