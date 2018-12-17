
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EduFiles9Tests {

    private final static int COUNT_EXP = 5;

    public static void main(String[] args) {
        List<Long> resultList = new ArrayList();
        int buffSizeStep = 1;
        
        for (int i = -1; i < 4; i++) {            
            int bufferSize = 1024 * (buffSizeStep << i);
            for (int j = 0; j < COUNT_EXP; j++) {
               long res = readWriteFile("in.pdf", "out.pdf", bufferSize);                
               resultList.add(res);
            }
        }

        LongSummaryStatistics result = resultList.stream()
                .collect(Collectors.summarizingLong(d -> d));        
        StringBuilder sbSream = new StringBuilder(50);
        sbSream.append("Result:\n")
                .append("min = ").append(result.getMin() / 1000.0).append(" \n")
                .append("max = ").append(result.getMax() / 1000.0).append(" \n")
                .append("avg = ").append(result.getAverage() / 1000.0).append(" \n");
        System.out.println(sbSream.toString());

    }

    private static long readWriteFile(String inFileName, String outFileName, int bufferSize) {
        if (bufferSize == 0) {
            return readWriteFileWithoutBuffer("in.pdf", "out.pdf");
        } else {
            return readWriteFileBuffered("in.pdf", "out.pdf", bufferSize);
        }
    }

    private static long readWriteFileBuffered(String inFileName, String outFileName, int bufferSize) {
        long startTime = 0;
        long endTime = 0;
        try (InputStream in = new BufferedInputStream(new FileInputStream(inFileName), bufferSize);
                OutputStream out = new BufferedOutputStream(new FileOutputStream(outFileName), bufferSize);) {
            startTime = System.currentTimeMillis();
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            endTime = System.currentTimeMillis();
        } catch (IOException ex) {
            Logger.getLogger(EduFiles3Buffered.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(String.format("Copy time: %7.3f for buffer %d", (endTime - startTime) / 1000.0, bufferSize));
        return endTime - startTime;
    }

    private static long readWriteFileWithoutBuffer(String inFileName, String outFileName) {
        long startTime = 0;
        long endTime = 0;
        try (InputStream in = new FileInputStream(inFileName);
                OutputStream out = new FileOutputStream(outFileName);) {
            startTime = System.currentTimeMillis();
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            endTime = System.currentTimeMillis();
        } catch (IOException ex) {
            Logger.getLogger(EduFiles3Buffered.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(String.format("Copy time: %7.3f for buffer %d", (endTime - startTime) / 1000.0, 0));
        return endTime - startTime;
    }

}
