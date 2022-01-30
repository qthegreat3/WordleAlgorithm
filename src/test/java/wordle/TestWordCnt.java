package wordle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestWordCnt {

    //privy expected count 1 for privy and 0 for everything else
    //PriVypRIvY expected count 2 for privy
    //caat expected count 2 for a 1 for c ,t 0 for everything else
    //caat, aaa, privy count for each 4 for a, 1 for ctprivy 0 for everything else


    String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Test
    public void  simpleTest(){
        String word = "privy";

        LetterFrequencyCounter frequencyCounter = new LetterFrequencyCounter();

        frequencyCounter.addToFrequencyCount(word);

        Map<Character, Integer> frequencyCntMap = frequencyCounter.getRunningFrequencyCount();

        for(int alphabetIndex = 0; alphabetIndex < ALPHABET.length(); alphabetIndex++){
            char letter = ALPHABET.charAt(alphabetIndex);
            if(letter == 'p'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'r'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'i'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'v'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'y'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else {
                Assertions.assertEquals(0, frequencyCntMap.get(letter));
            }
        }
    }

    @Test
    public void  simpleTest2(){
        String word = "privyPRIVY";

        LetterFrequencyCounter frequencyCounter = new LetterFrequencyCounter();

        frequencyCounter.addToFrequencyCount(word);

        Map<Character, Integer> frequencyCntMap = frequencyCounter.getRunningFrequencyCount();

        for(int alphabetIndex = 0; alphabetIndex < ALPHABET.length(); alphabetIndex++){
            char letter = ALPHABET.charAt(alphabetIndex);
            if(letter == 'p'){
                Assertions.assertEquals(2, frequencyCntMap.get(letter));
            } else if(letter == 'r'){
                Assertions.assertEquals(2, frequencyCntMap.get(letter));
            } else if(letter == 'i'){
                Assertions.assertEquals(2, frequencyCntMap.get(letter));
            } else if(letter == 'v'){
                Assertions.assertEquals(2, frequencyCntMap.get(letter));
            } else if(letter == 'y'){
                Assertions.assertEquals(2, frequencyCntMap.get(letter));
            } else {
                Assertions.assertEquals(0, frequencyCntMap.get(letter));
            }
        }
    }

    @Test
    public void  multipleWordTest(){
        List<String> wordList = new ArrayList<>();
        wordList.add("privy");
        wordList.add("caat");
        wordList.add("aa");

        LetterFrequencyCounter frequencyCounter = new LetterFrequencyCounter();

        for(String word : wordList) {
            frequencyCounter.addToFrequencyCount(word);
        }

        Map<Character, Integer> frequencyCntMap = frequencyCounter.getRunningFrequencyCount();

        for(int alphabetIndex = 0; alphabetIndex < ALPHABET.length(); alphabetIndex++){
            char letter = ALPHABET.charAt(alphabetIndex);
            if(letter == 'p'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'r'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'i'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'v'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'y'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'c'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else if(letter == 'a'){
                Assertions.assertEquals(4, frequencyCntMap.get(letter));
            } else if(letter == 't'){
                Assertions.assertEquals(1, frequencyCntMap.get(letter));
            } else {
                Assertions.assertEquals(0, frequencyCntMap.get(letter));
            }
        }
    }
}
