import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;

public class App {
    private Scanner sc;
    private List<String> contentFile;

    public App(){
        try{
            this.sc = new Scanner(new File("src/input.txt"));
            this.contentFile = new ArrayList<>();
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }
    }

    private void challenge1() {
        getFullList();
        AtomicInteger result = new AtomicInteger(0);
    
        IntStream.range(0, contentFile.size())
            .forEach(index -> {
                String x = contentFile.get(index);
                List<String> numbers = findMatches(x, "\\d+");
    
                int[] nIndex = numbers.stream()
                    .mapToInt(word -> IntStream.range(0, x.length())
                        .filter(i -> x.startsWith(word, i))
                        .findFirst()
                        .orElse(-1))
                    .toArray();
    
                String checkStr = "";
                if (index != 0 && index != contentFile.size() - 1) {
                    for (int j = 0; j < nIndex.length; j++) {
                        int nLenght = numbers.get(j).toCharArray().length;
                        if (nIndex[j] != 0 && nIndex[j] != x.length() && nIndex[j] <= x.length() - (nLenght + 1)) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                    x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                    contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1);
                        } else if (nIndex[j] == 0) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                    x.substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                    contentFile.get(index + 1).substring(nIndex[j], nIndex[j] + nLenght + 1);
                        } else if (nIndex[j] > x.length() - (nLenght + 1)) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                    x.substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                    contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght);
                        }
                        if (isMatch(checkStr, "^(?![.\\d]+$).+$")) {
                            result.addAndGet(Integer.parseInt(numbers.get(j)));
                        }
                    }
                } else if (index == 0) {
                    for (int j = 0; j < nIndex.length; j++) {
                        int nLenght = numbers.get(j).toCharArray().length;
                        if (nIndex[j] != 0 && nIndex[j] != x.length() && nIndex[j] <= x.length() - (nLenght + 1)) {
                            checkStr = x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                    contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1);
                        } else if (nIndex[j] == 0) {
                            checkStr = x.substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                    contentFile.get(index + 1).substring(nIndex[j], nIndex[j] + nLenght + 1);
                        } else if (nIndex[j] > x.length() - (nLenght + 1)) {
                            checkStr = x.substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                    contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght);
                        }
                        if (isMatch(checkStr, "^(?![.\\d]+$).+$")) {
                            result.addAndGet(Integer.parseInt(numbers.get(j)));
                        }
                    }
                } else if (index == contentFile.size() - 1) {
                    for (int j = 0; j < nIndex.length; j++) {
                        int nLenght = numbers.get(j).toCharArray().length;
                        if (nIndex[j] != 0 && nIndex[j] != x.length() && nIndex[j] <= x.length() - (nLenght + 1)) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                    x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1);
                        } else if (nIndex[j] == 0) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                    x.substring(nIndex[j], nIndex[j] + nLenght + 1);
                        } else if (nIndex[j] > x.length() - (nLenght + 1)) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght) +
                                    x.substring(nIndex[j] - 1, nIndex[j] + nLenght);
                        }
                        if (isMatch(checkStr, "^(?![.\\d]+$).+$")) {
                            result.addAndGet(Integer.parseInt(numbers.get(j)));
                        }
                    }
                }
            });
        System.out.println(result.get());
    }

    private boolean isMatch(String inputStr, String regex) {
        return Pattern.matches(regex, inputStr);
    }

    private List<String> findMatches(String inputStr, String regex) {
        List<String> returnList = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(inputStr);

        while(matcher.find())
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