import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private Scanner sc;
    private List<String> contentFile;

    public App(){
        try{
            this.sc = new Scanner(new File("challenge1\\src\\input.txt"));
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
    
                int[] nIndex = getAllIndex(numbers, x);
    
                String checkStr = "";
                if (index != 0 && index != contentFile.size() - 1) {
                    for (int j = 0; j < nIndex.length; j++) {
                        int nLenght = numbers.get(j).toCharArray().length;
                        System.out.println(x.length()-1 + "-"+ index +"-"+ nIndex[j]);
                        if (nIndex[j] != 0 && nIndex[j] != x.length()-1 && nIndex[j] <= x.length() - (nLenght + 1)) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                    x.substring(nIndex[j] - 1, nIndex[j] + nLenght + 1) +
                                    contentFile.get(index + 1).substring(nIndex[j] - 1, nIndex[j] + nLenght + 1);
                                    
                        } else if (nIndex[j] == 0) {
                            checkStr = contentFile.get(index - 1).substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                    x.substring(nIndex[j], nIndex[j] + nLenght + 1) +
                                    contentFile.get(index + 1).substring(nIndex[j], nIndex[j] + nLenght + 1);
                                    
                        }else if(nIndex[j] == x.length()-1){
                            checkStr = contentFile.get(index - 1).substring(nIndex[j]-1, nIndex[j]) +
                                    x.substring(nIndex[j], nIndex[j]-1 + nLenght) +
                                    contentFile.get(index + 1).substring(nIndex[j]-1, nIndex[j] + 1);

                                    System.out.println(contentFile.get(index - 1).substring(nIndex[j]-1, nIndex[j]) + "\n" +
                                    x.substring(nIndex[j], nIndex[j]-1 + nLenght) + "\n" +
                                    contentFile.get(index + 1).substring(nIndex[j]-1, nIndex[j] + 1) + "\n\n");

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
                System.out.println();
            });
        System.out.println(result.get());
    }

    private int[] getAllIndex(List<String> numbers, String x){
        List<Integer> nIndex = new ArrayList<>();
        for (String n : numbers) {
            int cnt = 0;
            int index = x.indexOf(n);
            if(!nIndex.isEmpty()){
                while(index < nIndex.getLast()){
                    cnt++;
                    index = x.indexOf(n, cnt);
                 }
            }
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