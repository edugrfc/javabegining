
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EduFiles9Tests {

    public static void main(String[] args) {

        // Массив со значениями размера буффера
        int[] buffSize = {1024, 2048, 4096, 8192};

        // Количество замеров 
        int amount = 2;

        // Файлы для чтения и записи
        String inFile = "src/TestTxtFiles/in.txt";
        String outFile = "src/TestTxtFiles/out.txt";

        // Словари для результатов 
        Map<Integer, Double[]> readWriteResults = null;
        Map<Integer, Double[]> readResults = null;

        // Определение времени чтения/записи файла c буфферизацией и вывод на консоль
        readWriteResults = getTimeReadWriteBuffered(inFile, outFile, buffSize, amount);

        // Определение времени чтения файла c буфферизацией и вывод на консоль
        readResults = getTimeReadBuffered(inFile, outFile, buffSize, amount);

        // Определение времени записи файла c буфферизацией и вывод на консоль
        getTimeWriteBuffered(readWriteResults, readResults);

        // Определение времени чтения/записи файла без буфферизации и вывод на консоль
        getTimeReadWrite(inFile, outFile, amount);
    }

    // Метод возвращает результаты измерения времени чтения/записи файла с буффером и выводит на консоль
    public static Map<Integer, Double[]> getTimeReadWriteBuffered(String inFile, String outFile, int[] buffSize, int amount) {
        Double[] res = new Double[amount];
        long startTime = 0;
        long endTime = 0;
        Map<Integer, Double[]> results = new HashMap<>();
        for (int i = 0; i < buffSize.length; i++) {
            for (int j = 0; j < amount; j++) {
                try ( BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile), buffSize[i]);  BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile), buffSize[i])) {
                    int c;
                    startTime = System.currentTimeMillis();
                    while ((c = in.read()) != -1) {
                        out.write(c);
                    }
                    endTime = System.currentTimeMillis();
                } catch (Exception e) {
                }
                res[j] = (endTime - startTime) / 1000.0;
            }
            Arrays.sort(res);
            double sumRes = 0;
            for (double result : res) {
                sumRes = sumRes + result;
            }
            results.put(buffSize[i], Arrays.copyOf(res, res.length));
            System.out.println(String.format("Минимальное время чтения/записи с буффером %d: %7.3f", buffSize[i], res[0]));
            System.out.println(String.format("Среднее время чтения/записи с буффером %d: %7.3f", buffSize[i], (sumRes / ((double) (amount)))));
            System.out.println(String.format("Максимальное время чтения/записи с буффером %d: %7.3f", buffSize[i], res[amount - 1]));
            System.out.println();
        }
        return results;
    }

    // Метод возвращает результаты измерения времени чтения файла с буффером и выводит на консоль
    public static Map<Integer, Double[]> getTimeReadBuffered(String inFile, String outFile, int[] buffSize, int amount) {
        Double[] res = new Double[amount];
        long startTime = 0;
        long endTime = 0;
        Map<Integer, Double[]> results = new HashMap<>();
        for (int i = 0; i < buffSize.length; i++) {
            for (int j = 0; j < amount; j++) {
                try ( BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile), buffSize[i]);  BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile), buffSize[i])) {
                    int c;
                    startTime = System.currentTimeMillis();
                    while ((c = in.read()) != -1) {
                    }
                    endTime = System.currentTimeMillis();
                } catch (Exception e) {
                }
                res[j] = (endTime - startTime) / 1000.0;
            }
            Arrays.sort(res);
            double sumRes = 0;
            for (double result : res) {
                sumRes = sumRes + result;
            }
            results.put(buffSize[i], Arrays.copyOf(res, res.length));
            System.out.println(String.format("Минимальное время чтения с буффером %d: %7.3f", buffSize[i], res[0]));
            System.out.println(String.format("Среднее время чтения с буффером %d: %7.3f", buffSize[i], (sumRes / ((double) (amount)))));
            System.out.println(String.format("Максимальное время чтения с буффером %d: %7.3f", buffSize[i], res[amount - 1]));
            System.out.println();
        }
        return results;
    }

    // Метод возвращает результаты измерения времени записи файла с буффером и выводит на консоль
    public static Map<Integer, Double[]> getTimeWriteBuffered(Map<Integer, Double[]> readWriteResults, Map<Integer, Double[]> readResults) {
        Map<Integer, Double[]> results = new HashMap<>();
        Object[] keys = (readWriteResults.keySet()).toArray();
        Arrays.sort(keys);
        Double[] resReadWrite = null;
        Double[] resRead = null;
        for (Object key : keys) {
            for (Map.Entry entry : readWriteResults.entrySet()) {
                if (key.equals(entry.getKey())) {
                    resReadWrite = (Double[]) entry.getValue();
                    break;
                }
            }
            for (Map.Entry entry : readResults.entrySet()) {
                if (key.equals(entry.getKey())) {
                    resRead = (Double[]) entry.getValue();
                    break;
                }
            }
            Double[] res = new Double[resRead.length];
            for (int i = 0; i < resRead.length; i++) {
                res[i] = resReadWrite[i] - resRead[i];
            }
            Arrays.sort(res);
            double sumRes = 0;
            for (double result : res) {
                sumRes = sumRes + result;
            }
            results.put((Integer) key, Arrays.copyOf(res, res.length));
            System.out.println(String.format("Минимальное время записи с буффером %d: %7.3f", key, res[0]));
            System.out.println(String.format("Среднее время записи с буффером %d: %7.3f", key, (sumRes / ((double) (resRead.length)))));
            System.out.println(String.format("Максимальное время записи с буффером %d: %7.3f", key, res[resRead.length - 1]));
            System.out.println();
        }
        return results;
    }

    // Метод возвращает результаты измерения времени чтения/записи файла без буффера и выводит на консоль
    public static double[] getTimeReadWrite(String inFile, String outFile, int amount) {
        Double[] res = new Double[amount];
        long startTime = 0;
        long endTime = 0;
        double[] results = new double[3];
        for (int j = 0; j < amount; j++) {
            try ( FileInputStream in = new FileInputStream(inFile);  FileOutputStream out = new FileOutputStream(outFile)) {
                int c;
                startTime = System.currentTimeMillis();
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                endTime = System.currentTimeMillis();
            } catch (Exception e) {
            }

            res[j] = (endTime - startTime) / 1000.0;
        }
        Arrays.sort(res);
        double sumRes = 0;
        for (double result : res) {
            sumRes = sumRes + result;
        }
        results[0] = res[0];
        results[1] = (sumRes / ((double) (amount)));
        results[2] = res[amount - 1];
        System.out.println(String.format("Минимальное время чтения без буффера %7.3f", results[0]));
        System.out.println(String.format("Среднее время чтения без буффера  %7.3f", results[1]));
        System.out.println(String.format("Максимальное время чтения без буффера %7.3f", results[2]));
        System.out.println();
        return results;
    }
}
