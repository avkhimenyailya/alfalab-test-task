package ru.alphalab.testtask.concurrency;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Ilya Avkhimenya
 */
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        // create prime numbers
        // https://www.baeldung.com/java-generate-prime-numbers
        List<Integer> primeNumbers = primeNumbersTill(1_000_000);
        Iterator<Integer> primeNumbersIterator = primeNumbers.iterator();

        // create files
        File fileResultTxt = new File("Result.txt");
        if (!fileResultTxt.exists()) fileResultTxt.createNewFile();

        File fileThread1Txt = new File("Thread1.txt");
        if (!fileThread1Txt.exists()) fileThread1Txt.createNewFile();

        File fileThread2Txt = new File("Thread2.txt");
        if (!fileThread2Txt.exists()) fileThread2Txt.createNewFile();

        Thread thread1 = new Thread(() -> {
            while (synchronizedHasNext(primeNumbersIterator)) {
                Integer next = primeNumbersIterator.next();
                writeIntegerInFile(fileResultTxt, next);
                writeIntegerInFile(fileThread1Txt, next);
            }
        });

        Thread thread2 = new Thread(() -> {
            while (synchronizedHasNext(primeNumbersIterator)) {
                Integer next = primeNumbersIterator.next();
                writeIntegerInFile(fileResultTxt, next);
                writeIntegerInFile(fileThread2Txt, next);
            }
        });

        thread1.start();
        thread2.start();
    }

    public static List<Integer> primeNumbersTill(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(Main::isPrime).boxed()
                .collect(Collectors.toList());
    }

    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }

    private static synchronized boolean synchronizedHasNext(Iterator<Integer> iterator) {
        return iterator.hasNext();
    }

    @SneakyThrows
    private static synchronized void writeIntegerInFile(File file, Integer integer) {
        FileWriter writer = new FileWriter(file, true);
        writer.write(" " + integer);
        writer.close();
    }
}
