package wordle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GetFrequencyCountFor5LetterWordsRunner {

    public static void main(String[] args) throws FileNotFoundException {
        //read and print all the words from the dictionary
        File dictionaryFile = new File("/usr/share/dict/words");
        FileReader dictionaryReader = new FileReader(dictionaryFile);
        BufferedReader reader = new BufferedReader(dictionaryReader);
        AtomicInteger numberOf5letterWords = new AtomicInteger();
        List<String> fiveLetterWordList = new ArrayList<>();
        reader.lines().forEach(word -> {
            if(word.length() == 5){
                fiveLetterWordList.add(word);
                numberOf5letterWords.getAndIncrement();
            }
        });
        System.out.println("Number of 5 Letter Words: " + numberOf5letterWords.intValue());
        LetterFrequencyCounter frequencyCounter = new LetterFrequencyCounter();
        for(String word : fiveLetterWordList){
            frequencyCounter.addToFrequencyCount(word);
        }

        Map<Character, Integer> frequencyCount = frequencyCounter.getRunningFrequencyCount();

        Set<Map.Entry<Character, Integer>> entries = frequencyCount.entrySet();
        List<Map.Entry<Character, Integer>> letterFrequencySorted = entries.stream().sorted(Map.Entry.comparingByValue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.intValue() == o2.intValue()) {
                    return 0;
                }else if(o1.intValue() > o2.intValue()){
                    return -1;
                } else {
                    return 1;
                }
            }
        })).collect(Collectors.toList());

        int letterCnt = 1;
        for(Map.Entry<Character, Integer> entry : letterFrequencySorted){
            System.out.println(letterCnt + ". " + entry.getKey() + ": " + entry.getValue());
            letterCnt++;
        }

        String desiredLetters = "lou";

        List<String> suggestedWords = wordsThatContainDesiredLetters(fiveLetterWordList, desiredLetters);

        printSuggestedWords(suggestedWords, desiredLetters);

        String usedLetters = "arsenti";

        List<String> cleanedSuggestedWordList = wordsThatDontContainDesiredLetters(suggestedWords, usedLetters);

        printSuggestedWords(cleanedSuggestedWordList, usedLetters);

        LetterFrequencyCounter newCount = new LetterFrequencyCounter();
        for(String word : suggestedWords){
            newCount.addToFrequencyCount(word);
        }

        frequencyCount = newCount.getRunningFrequencyCount();

        entries = frequencyCount.entrySet();
        letterFrequencySorted = entries.stream().sorted(Map.Entry.comparingByValue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.intValue() == o2.intValue()) {
                    return 0;
                }else if(o1.intValue() > o2.intValue()){
                    return -1;
                } else {
                    return 1;
                }
            }
        })).collect(Collectors.toList());

        letterCnt = 1;
        for(Map.Entry<Character, Integer> entry : letterFrequencySorted){
            System.out.println(letterCnt + ". " + entry.getKey() + ": " + entry.getValue());
            letterCnt++;
        }

       desiredLetters = "ould";

        suggestedWords = wordsThatContainDesiredLetters(fiveLetterWordList, desiredLetters);

        printSuggestedWords(suggestedWords, desiredLetters);

        usedLetters = "arsenti";

        cleanedSuggestedWordList = wordsThatDontContainDesiredLetters(suggestedWords, usedLetters);

        printSuggestedWords(cleanedSuggestedWordList, usedLetters);

    }

    public static void printSuggestedWords(List<String> wordList, String desiredLetters){
        if(wordList.size() == 0){
            System.out.println("No Suggested Words For " + desiredLetters);
        } else {
            System.out.println("Suggested Words For " + desiredLetters);
            for(String word : wordList){
                System.out.println(word);
            }
        }
    }

    public static List<String> wordsThatContainDesiredLetters(List<String> wordList, String desiredLetters){
        List<String> suggestedWords = new ArrayList<>();

        for(String word : wordList){
            for(int desiredLetterIndex = 0; desiredLetterIndex < desiredLetters.length(); desiredLetterIndex++){
                String desiredLetter = desiredLetters.toLowerCase().substring(desiredLetterIndex,desiredLetterIndex + 1);
                System.out.println(desiredLetter);
                if(!word.toLowerCase().contains(desiredLetter)){
                    break;
                }
                if(desiredLetterIndex == desiredLetters.length() -1){
                    suggestedWords.add(word);
                }
            }
        }

        return suggestedWords;
    }

    public static List<String> wordsThatDontContainDesiredLetters(List<String> wordList, String unddesiredLetters){
        List<String> suggestedWords = new ArrayList<>();

        for(String word : wordList){
            for(int desiredLetterIndex = 0; desiredLetterIndex < unddesiredLetters.length(); desiredLetterIndex++){
                String undesiredLetter = unddesiredLetters.toLowerCase().substring(desiredLetterIndex,desiredLetterIndex + 1);
                System.out.println(undesiredLetter);
                if(word.toLowerCase().contains(undesiredLetter)){
                    break;
                }
                if(desiredLetterIndex == unddesiredLetters.length() -1){
                    suggestedWords.add(word);
                }
            }
        }

        return suggestedWords;
    }
}
