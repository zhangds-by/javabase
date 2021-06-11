package com.zhangds.java8.demo.smallcase;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorDemo {

    private static String SENTENCE =
            " Nel mezzo del cammin di nostra vita " +
                    "mi ritrovai in una selva oscura" +
                    " ché la dritta via era smarrita ";

    public static void main(String[] args) {
        final Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
//        System.out.println( "单词数" + countWords(stream));
//        System.out.println( "单词数" + countWords(stream.parallel())); // 拆分流的错误
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> spliterStream = StreamSupport.stream(spliterator, true);
        System.out.println( "单词数" + countWords(spliterStream.parallel()));
    }

    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

    static class WordCounter{
        private final int counter; // 记录单词数
        private final boolean lastSpace; // 上一个char是否为空格

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        // accumulate方法遍历到新的Character时，WordCounter的状态转换
        public WordCounter accumulate(Character c) { // 遍历每个char
            if (Character.isWhitespace(c)) {
                return lastSpace ?
                        this :
                        new WordCounter(counter, true);
            } else { // 上一个字符是空格，当前字符不是空格，单词+1
                return lastSpace ?
                        new WordCounter(counter + 1, false) :
                        this;
            }
        }

        //合并计数器并统计计数器结果总和
        //作用于Character 流的两个不同子部分的两个WordCounter的部分结果进行汇总，
        // 也就是把两个WordCounter内部的计数器加起来。
        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }

    }

    static class WordCounterSpliterator implements Spliterator<Character> {

        private final String string;
        private int currentChar = 0;
        public WordCounterSpliterator(String string) {
            this.string = string;
        }

        // 处理当前字符 并 返回是否还有字符处理
        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if (currentSize < 10) { // 分解的string足够小，顺序处理
                return null;
            }
            // 拆分位置由string中间位置向前定位到空格位置
            for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    // 创建一个新WordCounterSpliterator来解析String从开始到拆分位置的部分
                    Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos; //将这个WordCounterSpliterator 的起始位置设为拆分位置
                    return spliterator;
                }
            }
            return null;
        }

        //Spliterator解析的String的总长度和当前遍历的位置的差
        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        // ORDERED（顺序就是String中各个Character的次序）
        // SIZED（estimatedSize方法的返回值是精确的）
        // SUBSIZED（trySplit方法创建的其他Spliterator也有确切大小）
        // NONNULL（String中不能有为null 的Character ）
        // IMMUTABLE（在解析String 时不能再添加Character，因为String本身是一个不可变类）
        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
}
