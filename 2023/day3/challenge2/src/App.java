import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;

public class App {
    private Scanner sc;
    private List<String> contentFile;

    public App() {
        try {
            this.sc = new Scanner(new File("src/input.txt"));
            this.contentFile = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void challenge1() {
        getFullList();
        int result = 0;

        int[] tmp = IntStream.range(0, contentFile.size()).toArray();
        for (int index : tmp) {
            String x = contentFile.get(index);
            List<String> numbers = findMatches(x, "\\d+");

            int[] nIndex = getAllIndex(numbers, x);

            String checkStr = "";
            if (index != 0 && index != contentFile.size() - 1) {
                for (int j = 0; j < nIndex.length; j++) {
                    int nLenght = numbers.get(j).toCharArray().length;

                    if (nIndex[j] != 0 && nIndex[j] != x.length() - 1 && nIndex[j] <= x.length() - (nLenght + 1)) {
                        checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1);

                        System.out.println(
                                contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) + "\n" +
                                        x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) + "\n" +
                                        contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1)
                                        + "\n*\n\n");

                    } else if (nIndex[j] == 0) {
                        checkStr = contentFile.get(index - 1).substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                x.substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                contentFile.get(index + 1).substring(nIndex[j], nIndex[j] + nLenght + 1);

                        System.out.println(contentFile.get(index - 1).substring(nIndex[j], nIndex[j] + nLenght + 1)
                                + "\n" +
                                x.substring(nIndex[j], nIndex[j] + nLenght + 1) + "\n" +
                                contentFile.get(index + 1).substring(nIndex[j], nIndex[j] + nLenght + 1) + "\n**\n\n");

                    } else if (nIndex[j] == x.length() - 1) {
                        checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + 1) +
                                x.substring(nIndex[j] - 1, nIndex[j] + 1) +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + 1);

                        System.out.println(contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + 1) + "\n" +
                                x.substring(nIndex[j] - 1, nIndex[j] + 1) + "\n" +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + 1) + "\n***\n\n");

                    } else if (nIndex[j] > x.length() - (nLenght + 1)) {
                        checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                x.substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght);

                        System.out.println(
                                contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght) + "\n" +
                                        x.substring(nIndex[j] - 1, nIndex[j] + nLenght) + "\n" +
                                        contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght)
                                        + "\n****\n\n");

                    }
                    if (isMatch(checkStr, "^(?![.\\d]+$).+$")) {
                        result += Integer.parseInt(numbers.get(j));
                    }
                }
            } else if (index == 0) {
                for (int j = 0; j < nIndex.length; j++) {
                    int nLenght = numbers.get(j).toCharArray().length;
                    if (nIndex[j] != 0 && nIndex[j] != x.length() && nIndex[j] <= x.length() - (nLenght + 1)) {
                        checkStr = x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1);

                        System.out.println(x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) + "\n" +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1)
                                + "\n-\n\n");

                    } else if (nIndex[j] == 0) {
                        checkStr = x.substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                contentFile.get(index + 1).substring(nIndex[j], nIndex[j] + nLenght + 1);

                        System.out.println(x.substring(nIndex[j], nIndex[j] + nLenght + 1) + "\n" +
                                contentFile.get(index + 1).substring(nIndex[j], nIndex[j] + nLenght + 1) + "\n--\n\n");

                    } else if (nIndex[j] == x.length() - 1) {
                        checkStr = x.substring(nIndex[j] - 1, nIndex[j] + 1) +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + 1);

                        System.out.println(x.substring(nIndex[j] - 1, nIndex[j] + 1) + "\n" +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + 1) + "\n---\n\n");

                    } else if (nIndex[j] > x.length() - (nLenght + 1)) {
                        checkStr = x.substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght);

                        System.out.println(x.substring(nIndex[j] - 1, nIndex[j] + nLenght) + "\n" +
                                contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght)
                                + "\n----\n\n");

                    }
                    if (isMatch(checkStr, "^(?![.\\d]+$).+$")) {
                        result += Integer.parseInt(numbers.get(j));
                    }

                }
            } else if (index == contentFile.size() - 1) {
                for (int j = 0; j < nIndex.length; j++) {
                    int nLenght = numbers.get(j).toCharArray().length;
                    if (nIndex[j] != 0 && nIndex[j] != x.length() && nIndex[j] <= x.length() - (nLenght + 1)) {
                        checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1);

                        System.out.println(
                                contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) + "\n" +
                                        x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) + "\n,\n\n");

                    } else if (nIndex[j] == 0) {
                        checkStr = contentFile.get(index - 1).substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                x.substring(nIndex[j], nIndex[j] + nLenght + 1);

                        System.out.println(
                                contentFile.get(index - 1).substring(nIndex[j], nIndex[j] + nLenght + 1) + "\n" +
                                        x.substring(nIndex[j], nIndex[j] + nLenght + 1) + "\n,,\n\n");

                    } else if (nIndex[j] == x.length() - 1) {
                        checkStr = x.substring(nIndex[j] - 1, nIndex[j]) +
                                contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j]);

                        System.out.println(x.substring(nIndex[j] - 1, nIndex[j]) + "\n" +
                                contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j]) + "\n,,,\n\n");

                    } else if (nIndex[j] > x.length() - (nLenght + 1)) {
                        checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                x.substring(nIndex[j] - 1, nIndex[j] + nLenght);

                        System.out.println(
                                contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght) + "\n" +
                                        x.substring(nIndex[j] - 1, nIndex[j] + nLenght) + "\n,,,,\n\n");

                    }
                    if (isMatch(checkStr, "^(?![.\\d]+$).+$")) {
                        result += Integer.parseInt(numbers.get(j));
                    }
                }
            }
            // new Scanner(System.in).nextLine();
        }
        System.out.println(result);
    }

    private int[] getAllIndex(List<String> numbers, String x) {
        List<Integer> nIndex = new ArrayList<>();
        int lastIndex = 0;
        for (String n : numbers) {
            int index = x.indexOf(n, lastIndex);
            lastIndex = index + 1;
            nIndex.add(index);
        }

        return nIndex.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isMatch(String inputStr, String regex) {
        return Pattern.matches(regex, inputStr);
    }

    private List<String> findMatches(String inputStr, String regex) {
        List<String> returnList = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(inputStr);

        while (matcher.find())
            returnList.add(matcher.group());

        return returnList;
    }

    private void getFullList() {
        while (sc.hasNextLine()) {
            contentFile.add(sc.nextLine());
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        new App().challenge1();
    }
}