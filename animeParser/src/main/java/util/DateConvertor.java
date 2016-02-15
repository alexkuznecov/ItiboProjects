package util;

public class DateConvertor {

    public static int getMonthNumberByName(String name) {
        int numb = -1;
        switch (name) {
            case "январь" : numb = 1; break;
            case "февраль" : numb = 2; break;
            case "март" : numb = 3; break;
            case "апрель" : numb = 4; break;
            case "май" : numb = 5; break;
            case "июнь" : numb = 6; break;
            case "июль" : numb = 7; break;
            case "август" : numb = 8; break;
            case "сентябрь" : numb = 9; break;
            case "октябрь" : numb = 10; break;
            case "ноябрь" : numb = 11; break;
            case "декабрь" : numb = 12; break;
        }
        return numb;
    }

}
