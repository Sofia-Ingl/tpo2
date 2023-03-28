import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

public class FuncTest {

    static double eps = 0.0001;
    static double testEps = 0.2;

    static CosFunc cosMock;
    static SinFunc sinMock;
    static TanFunc tanMock;
    static CotFunc cotMock;
    static CscFunc cscMock;
    static SecFunc secMock;

    static LnFunc lnMock;
    static LogFunc logMock;

    static Reader sinIn;
    static Reader cosIn;
    static Reader tanIn;
    static Reader cotIn;
    static Reader cscIn;
    static Reader secIn;

    static Reader lnIn;
    static Reader log3In;
    static Reader log5In;
    static Reader log10In;


    @BeforeAll
    static void init() {
        cosMock = Mockito.mock(CosFunc.class);
        sinMock = Mockito.mock(SinFunc.class);
        tanMock = Mockito.mock(TanFunc.class);
        cotMock = Mockito.mock(CotFunc.class);
        cscMock = Mockito.mock(CscFunc.class);
        secMock = Mockito.mock(SecFunc.class);
        lnMock = Mockito.mock(LnFunc.class);
        logMock = Mockito.mock(LogFunc.class);
        try {
            cosIn = new FileReader("src/main/resources/in/cosIn.csv");
            sinIn = new FileReader("src/main/resources/in/sinIn.csv");
            tanIn = new FileReader("src/main/resources/in/tanIn.csv");
            cotIn = new FileReader("src/main/resources/in/cotIn.csv");
            cscIn = new FileReader("src/main/resources/in/cscIn.csv");
            secIn = new FileReader("src/main/resources/in/secIn.csv");
            lnIn = new FileReader("src/main/resources/in/lnIn.csv");
            log10In = new FileReader("src/main/resources/in/log10In.csv");
            log3In = new FileReader("src/main/resources/in/log3In.csv");
            log5In = new FileReader("src/main/resources/in/log5In.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.calc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.calc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tanIn);
            for (CSVRecord record : records) {
                Mockito.when(tanMock.calc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cotIn);
            for (CSVRecord record : records) {
                Mockito.when(cotMock.calc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records) {
                Mockito.when(cscMock.calc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.calc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(lnMock.calc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log10In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calcModified(Double.parseDouble(record.get(0)), 10, eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log3In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calcModified(Double.parseDouble(record.get(0)), 3, eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log5In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.calcModified(Double.parseDouble(record.get(0)), 5, eps)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException ex) {
            System.err.println("IO exception");
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testAllMocks(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testSin(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), cosMock, secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testCosInnerMock(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, new CosFunc(sinMock), secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testSinCos(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), new CosFunc(), secMock, cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testSecInnerMock(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, new SecFunc(cosMock), cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testSinCosSec(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), new CosFunc(), new SecFunc(), cscMock, tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testCscInnerMock(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, secMock, new CscFunc(sinMock), tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testSinCsc(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), cosMock, secMock, new CscFunc(), tanMock, cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testTanInnerMock(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, secMock, cscMock, new TanFunc(sinMock, cosMock), cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testSinCosTan(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), new CosFunc(), secMock, cscMock, new TanFunc(), cotMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testCotInnerMock(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, secMock, cscMock, tanMock, new CotFunc(sinMock, cosMock), lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testSinCosCot(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), new CosFunc(), secMock, cscMock, tanMock, new CotFunc(), lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testTrig(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), new CosFunc(), new SecFunc(), new CscFunc(), new TanFunc(), new CotFunc(), lnMock, logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testLn(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, new LnFunc(), logMock);
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testLogInnerMock(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, lnMock, new LogFunc(lnMock));
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testLog(double value, double expected) {
        ResultFunc function = new ResultFunc(sinMock, cosMock, secMock, cscMock, tanMock, cotMock, new LnFunc(), new LogFunc());
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/in/sysIn.csv")
    void testAll(double value, double expected) {
        ResultFunc function = new ResultFunc(new SinFunc(), new CosFunc(), new SecFunc(), new CscFunc(), new TanFunc(), new CotFunc(), new LnFunc(), new LogFunc());
        Assertions.assertEquals(expected, function.calc(value, eps), testEps);
    }

}
