package com.myproject.javastudy.stringanddate;

import com.myproject.javastudy.Utils.MyException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author lkxl
 */
public class StringAndDateTest {
    private final Logger logger = Logger.getLogger("test.Test");
    private static final String EMPTY_STRING_EXCEPTION = "Do not use Empty String";

    public void testString() throws MyException {
        String testString = "  Hello World  ";
        String testTrim = "test" + testTrim(testString) + "test";
        logger.info(testTrim);
        testString = testTrim.replace(" ", "").replace("test", "");
        String testReverse = indexReverse(testString, 3, 5);
        logger.info(testReverse);
        String testReverseSide = indexReverse(testString, 0, 9);
        logger.info(testReverseSide);
        String testContain = Integer.toString(containTime("llHelloWorlldll", "ll"));
        logger.info(testContain);
        String sameSubString = sameSubString("HelloWorld", "WorldHello").toString();
        logger.info(sameSubString);
        String sortString = sortString(testString);
        logger.info(sortString);

    }

    public String testTrim(String test) throws MyException {
        if (test.isEmpty()) {
            throw new MyException(EMPTY_STRING_EXCEPTION);
        }
        int startSpace = 0;
        int endSpace = 0;
        for (int i = 0; i < test.length(); i++) {
            if ((test.charAt(i)) == 32) {
                startSpace += 1;
            } else {
                break;
            }
        }
        for (int j = test.length() - 1; j >= 0; j--) {
            if ((test.charAt(j)) == 32) {
                endSpace += 1;
            } else {
                break;
            }
        }
        return test.substring(startSpace, test.length() - endSpace);
    }

    public String indexReverse(String test, int startIndex, int endIndex) throws MyException {

        if (endIndex >= test.length() || startIndex < 0 || test.isEmpty()) {
            throw new MyException("Error Input");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder reverstring = new StringBuilder();
            if (startIndex != 0) {
                stringBuilder.append(test.substring(0, startIndex));
            }
            reverstring.append(test.substring(startIndex, endIndex + 1));
            reverstring.reverse();
            stringBuilder.append(reverstring);
            if (endIndex != (test.length() - 1)) {
                stringBuilder.append(test.substring(endIndex + 1, test.length()));
            }
            return stringBuilder.toString();
        }
    }

    public List<String> sameSubString(String string1, String string2) throws MyException {
        if (string1.isEmpty() || string2.isEmpty()) {
            throw new MyException(EMPTY_STRING_EXCEPTION);
        }
        if (string1.length() > string2.length()) {
            String temp = string1;
            string1 = string2;
            string2 = temp;
        }
        List<String> stringList = new ArrayList<>();
        String result = "";
        for (int i = string2.length(); i >= 0; i--) {
            for (int j = 0; j <= string2.length() - i; j++) {
                if (string1.contains(string2.substring(j, j + i))) {
                    result = string2.substring(j, j + i);
                    stringList.add(result);
                }
            }
            if (!(result.isEmpty())) {
                break;
            }
        }
        return stringList;
    }

    public String sortString(String testString) throws MyException {
        if (testString.isEmpty()) {
            throw new MyException(EMPTY_STRING_EXCEPTION);
        }
        char[] charArray = new char[testString.length() - 1];
        testString.getChars(0, testString.length() - 1, charArray, 0);
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public int getTime(byte[] testByte, byte[] containByte) {
        int times = 0;
        for (int i = 0; i < testByte.length; i++) {
            if (testByte[i] == containByte[0]) {
                for (int j = 1; j < containByte.length; j++) {
                    if (i + j >= testByte.length || containByte[j] != testByte[i + j]) {
                        break;
                    }
                    if (j == containByte.length - 1) {
                        times += 1;
                    }
                }
            }
        }
        return times;
    }

    public int containTime(String testString, String containString) throws MyException {
        if (testString.isEmpty() || containString.isEmpty()) {
            throw new MyException(EMPTY_STRING_EXCEPTION);
        }
        byte[] testByte = testString.getBytes();
        byte[] containByte = containString.getBytes();
        return getTime(testByte, containByte);
    }

    public void testDate() {
        //1970.1.1
        long currentTime = System.currentTimeMillis();
        String time = Long.toString(currentTime);
        Date date = new Date(currentTime);
        String dateTime = date.toString();
        Calendar calendar = Calendar.getInstance();
        String calendarTime = calendar.toString();
        java.sql.Date sqlDate = new java.sql.Date(currentTime);
        String sqlDateStr = sqlDate.toString();
        logger.info(time);
        logger.info(dateTime);
        logger.info(calendarTime);
        logger.info(sqlDateStr);
    }

    public void testDateFormate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String formateDate = simpleDateFormat.format(date);
        String orDateString = date.toString();
        logger.info(orDateString);
        logger.info(formateDate);
        Date orDate = simpleDateFormat.parse(formateDate);
        orDateString = orDate.toString();
        logger.info(orDateString);
        java.sql.Date sqlDate = new java.sql.Date(orDate.getTime());
        String sqlDateString = sqlDate.toString();
        logger.info(sqlDateString);
        orDate = new Date(57599999L);//起始时间的1970-1-1 0:00为utc协调世界时 转为北京时间为8:00
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(orDate.getTime() - simpleDateFormat.parse("1970-01-01").getTime()));
        int number = bigDecimal.divide(new BigDecimal(1000 * 3600 * 24), 0, RoundingMode.UP).intValue();
        logger.info(() -> "一共" + number + "天");
        if (number % 5 == 4 || number % 5 == 0) {
            logger.info("晒网");
        } else {
            logger.info("打渔");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        localDate = LocalDate.of(1995, 6, 7);//Date默认会按初始日期偏移量计算
        Instant instant = Instant.now();
        logger.info(instant.toString());//打印UTC瞬时，北京时间需要+8小时
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        String mills = Long.toString(instant.toEpochMilli());
        logger.info(mills);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2019-04-17T17:38:57.062");
        logger.info(temporalAccessor.toString());
        dateTimeFormatter=DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE hh:mm:ss:fff");
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime=ZonedDateTime.now(zoneId);
         String zoneSet= ZoneId.getAvailableZoneIds().toString();
        logger.info(zoneSet);
        Duration duration=Duration.between(LocalTime.now(),LocalTime.now());
        String diff=Long.toString(duration.toNanos());
        logger.info(diff);
        Period period=Period.between(LocalDate.now(),LocalDate.MIN);
        String diffP =Integer.toString(period.getMonths());
        logger.info(diffP);
        String diffD=Long.toString(LocalDate.now().toEpochDay()-LocalDate.MIN.toEpochDay());
        TemporalAdjuster temporalAdjusters=TemporalAdjusters.firstDayOfNextMonth();
        LocalDate adjustDate = localDate.with(temporalAdjusters);
        Clock clock = Clock.offset(Clock.systemDefaultZone(),duration);
        /*
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")): 2019-04-17 17:38:57
        now.format(DateTimeFormatter.ISO_LOCAL_DATE):     2019-04-17
        now.format(DateTimeFormatter.ISO_DATE):           2019-04-17
        now.format(DateTimeFormatter.ISO_LOCAL_TIME):     17:38:57.062
        now.format(DateTimeFormatter.ISO_TIME):           17:38:57.062
        now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME):2019-04-17T17:38:57.062
        now.format(DateTimeFormatter.ISO_DATE_TIME):      2019-04-17T17:38:57.062
        now.format(DateTimeFormatter.ISO_ORDINAL_DATE):   2019-107
        now.format(DateTimeFormatter.ISO_WEEK_DATE):      2019-W16-3
        now.format(DateTimeFormatter.BASIC_ISO_DATE):     20190417
        --- FormatStyle.FULL ---
        zh CN -> 2018年7月23日 星期一
        --- FormatStyle.LONG ---
        zh CN -> 2018年7月23日
        --- FormatStyle.MEDIUM ---
        zh CN -> 2018-7-23
        --- FormatStyle.SHORT ---
        zh CN -> 18-7-23
         */
    }

    public java.sql.Date test1(){
        String time="2017-08-16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        LocalDate date=LocalDate.parse(time,formatter);
        return new java.sql.Date(date.toEpochDay());
    }
}
