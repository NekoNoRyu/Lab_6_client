package support.validators;

import support.enums.DateComponent;
import support.enums.NumberDataType;
import java.time.LocalDate;
import java.time.ZoneId;

public class FieldValidator {
    public static boolean name(String string) {
        return !string.isBlank();
    }
    public static boolean xCord(String string) {
        return canBeConverted(string, NumberDataType.DOUBLE) && Double.parseDouble(string) > -682;
    }
    public static boolean yCord(String string) {
        return canBeConverted(string, NumberDataType.DOUBLE);
    }
    public static boolean studentsCount(String string) {
        return canBeConverted(string, NumberDataType.INTEGER) && Integer.parseInt(string) > 0;
    }
    public static boolean formOfEducation(String string) {
        switch (string) {
            case "DISTANCE_EDUCATION", "FULL_TIME_EDUCATION", "EVENING_CLASSES", "":
                return true;
            default:
                return false;
        }
    }
    public static boolean semesterEnum(String string) {
        switch (string) {
            case "FIRST", "SECOND", "FOURTH", "SIXTH", "EIGHTH":
                return true;
            default:
                return false;
        }
    }
    public static boolean weight(String string) {
        return canBeConverted(string, NumberDataType.INTEGER) && Integer.parseInt(string) > 0;
    }
    public static boolean dateComponent(String strDateComponent, DateComponent dateComponent) { //проверяет на очевидные ошибки
        if (canBeConverted(strDateComponent, NumberDataType.INTEGER) || dateComponent.equals(DateComponent.ZONE)) {
            switch (dateComponent) {
                case YEAR:
                    if ((Integer.parseInt(strDateComponent) < 0) | (Integer.parseInt(strDateComponent) > LocalDate.now().getYear())) {
                        return false;
                    } else {return true;}
                case MONTH:
                    if ((Integer.parseInt(strDateComponent) < 1) | (Integer.parseInt(strDateComponent) > 12)) {
                        return false;
                    } else {return true;}
                case DAY:
                    if ((Integer.parseInt(strDateComponent) < 1) | (Integer.parseInt(strDateComponent) > 31)) {
                        return false;
                    } else {return true;}
                case MINUTE:
                    if ((Integer.parseInt(strDateComponent) < 0) | (Integer.parseInt(strDateComponent) > 60)) {
                        return false;
                    } else {return true;}
                case HOUR:
                    if ((Integer.parseInt(strDateComponent) < 0) | (Integer.parseInt(strDateComponent) > 23)) {
                        return false;
                    } else {return true;}
                case ZONE:
                    if (ZoneId.getAvailableZoneIds().contains(strDateComponent)) {
                        return true;
                    } else {return false;}
                default: return false;
            }
        } else {return false;}
    }
    private static boolean canBeConverted(String string, NumberDataType numberDataType) {
        try {
            switch (numberDataType) {
                case INTEGER:
                    Integer.parseInt(string);
                    break;
                case LONG:
                    Long.parseLong(string);
                    break;
                case DOUBLE:
                    Double.parseDouble(string);
                    break;
                case FLOAT:
                    Float.parseFloat(string);
                    break;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
