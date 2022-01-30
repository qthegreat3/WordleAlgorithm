package wordle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class LetterFrequencyCounter {
    private Map<Character, AtomicInteger> runningCount = new HashMap<Character, AtomicInteger>();

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public LetterFrequencyCounter(){
        for(int alphaIndex = 0 ; alphaIndex < ALPHABET.length(); alphaIndex++){
            runningCount.put(ALPHABET.charAt(alphaIndex), new AtomicInteger(0));
        }
    }

    public Map<Character, Integer> getRunningFrequencyCount() {

        Map<Character, Integer> theCount = new HashMap<>();

        Set<Character> alphabet = runningCount.keySet();

        for(Character letter : alphabet){
            theCount.put(letter, runningCount.get(letter).intValue());
        }

        return theCount;
    }

    public void addToFrequencyCount(String word){
        for(int wordIndex = 0; wordIndex < word.length(); wordIndex++){
                runningCount.get(word.toLowerCase().charAt(wordIndex)).incrementAndGet();
        }
    }
}
