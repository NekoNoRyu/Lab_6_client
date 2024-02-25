package support;

import data.*;
import lombok.Getter;
import support.validators.FieldValidator;
import support.enums.Coordinate;
import support.enums.DateComponent;

import java.time.*;
import java.util.*;

public class ElementReceiver {
    private final StudyGroup element;
    private final Scanner scanner;
    @Getter
    private int exit;

    public ElementReceiver() {
        this.element = StudyGroup.builder().build();
        this.scanner = new Scanner(System.in);
        this.exit = 0;
    }

    public StudyGroup receive() {
        System.out.println("Type \"exit\" to exit from element input");
        System.out.println("To enter \"null\" values press \"Enter\"");
        receiveName();
        if (exit == 1) {
            return element;
        }
        receiveCords();
        if (exit == 1) {
            return element;
        }
        receiveStudentsCount();
        if (exit == 1) {
            return element;
        }
        receiveFormOfEducation();
        if (exit == 1) {
            return element;
        }
        receiveSemesterEnum();
        if (exit == 1) {
            return element;
        }
        receiveGroupAdmin();
        return element;
    }

    private void receiveName() {
        while (true) {
            System.out.println("Enter name: ");
            String name = scan();
            if (name.equals("exit")) {
                exit = 1;
                break;
            } else if (FieldValidator.name(name)) {
                element.setName(name);
                break;
            } else {
                System.out.println("Invalid name input, please try again");
            }
        }
    }

    private void receiveCords() {
        Coordinates coordinates = Coordinates.builder().build();
        System.out.println("Enter coordinates: ");
        receiveCord(coordinates, Coordinate.X);
        if (exit == 1) {
            return;
        }
        receiveCord(coordinates, Coordinate.Y);
        if (exit == 1) {
            return;
        }
        element.setCoordinates(coordinates);
    }

    private void receiveCord(Coordinates coordinates, Coordinate cord) {
        while (true) {
            System.out.println("Enter " + cord.toString().toLowerCase() + ": ");
            String strCord = scan();
            if (strCord.equals("exit")) {
                exit = 1;
            } else if (cord.equals(Coordinate.X)) {
                if (FieldValidator.xCord(strCord)) {
                    coordinates.setX(Double.parseDouble(strCord));
                } else {
                    System.out.println("Invalid coordinates input, please try again");
                    continue;
                }
            } else {
                if (FieldValidator.yCord(strCord)) {
                    coordinates.setY(Double.parseDouble(strCord));
                } else {
                    System.out.println("Invalid coordinates input, please try again");
                    continue;
                }
            }
            break;
        }
    }

    private void receiveStudentsCount() {
        while (true) {
            System.out.println("Enter studentsCount: ");
            String studentsCount = scan();
            if (studentsCount.equals("exit")) {
                exit = 1;
            } else if (FieldValidator.studentsCount(studentsCount)) {
                element.setStudentsCount(Integer.parseInt(studentsCount));
            } else {
                System.out.println("Invalid studentsCount input, please try again");
                continue;
            }
            break;
        }
    }

    private void receiveFormOfEducation() {
        while (true) {
            System.out.println("Enter formOfEducation (DISTANCE_EDUCATION | FULL_TIME_EDUCATION | EVENING_CLASSES): ");
            String formOfEducation = scan().toUpperCase();
            if (formOfEducation.equals("exit".toUpperCase())) {
                exit = 1;
            } else if (FieldValidator.formOfEducation(formOfEducation)) {
                if (!formOfEducation.isBlank()) {
                    element.setFormOfEducation(FormOfEducation.valueOf(formOfEducation));
                }
            } else {
                System.out.println("Invalid formOfEducation input, please try again");
                continue;
            }
            break;
        }
    }

    private void receiveSemesterEnum() {
        while (true) {
            System.out.println("Enter semesterEnum (FIRST | SECOND | FOURTH | SIXTH | EIGHTH): ");
            String semesterEnum = scan().toUpperCase();
            if (semesterEnum.equals("exit".toUpperCase())) {
                exit = 1;
            } else if (FieldValidator.semesterEnum(semesterEnum)) {
                element.setSemesterEnum(Semester.valueOf(semesterEnum));
            } else {
                System.out.println("Invalid semesterEnum input, please try again");
                continue;
            }
            break;
        }
    }

    private void receiveGroupAdmin() {
        Person groupAdmin = Person.builder().build();
        System.out.println("Enter groupAdmin? y/n");
        while (true) {
            String answer = scan();
            if (answer.equals("exit")) {
                exit = 1;
                break;
            } else if (answer.equals("y")) {
                receiveGroupAdminsName(groupAdmin);
                if (exit == 1) {
                    break;
                }
                receiveGroupAdminsBirthday(groupAdmin);
                if (exit == 1) {
                    break;
                }
                receiveGroupAdminsWeight(groupAdmin);
                element.setGroupAdmin(groupAdmin);
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Invalid input, please try again");
            }
        }
    }

    private void receiveGroupAdminsName(Person groupAdmin) {
        while (true) {
            System.out.println("Enter name: ");
            String name = scan();
            if (name.equals("exit")) {
                exit = 1;
            } else if (FieldValidator.name(name)) {
                groupAdmin.setName(name);
            } else {
                System.out.println("Invalid name input, please try again");
                continue;
            }
            break;
        }
    }

    private void receiveGroupAdminsBirthday(Person groupAdmin) {
        int year, month, day, minute, second, hour;
        ZoneId zone;
        while (true) {
            System.out.println("Enter birthday: ");
            year = Integer.parseInt(receiveDateComponent(DateComponent.YEAR));
            if (exit == 1) {
                break;
            }
            month = Integer.parseInt(receiveDateComponent(DateComponent.MONTH));
            if (exit == 1) {
                break;
            }
            day = Integer.parseInt(receiveDateComponent(DateComponent.DAY));
            if (exit == 1) {
                break;
            }
            hour = Integer.parseInt(receiveDateComponent(DateComponent.HOUR));
            if (exit == 1) {
                break;
            }
            minute = Integer.parseInt(receiveDateComponent(DateComponent.MINUTE));
            if (exit == 1) {
                break;
            }
            zone = ZoneId.of(receiveDateComponent(DateComponent.ZONE));
            if (exit == 1) {break;}
            try {
                groupAdmin.setBirthday(ZonedDateTime.of(LocalDateTime.of(year, month, day, hour, minute), zone));
                break;
            } catch (DateTimeException e) {
                System.out.println("Invalid date input, please try again");
            }
        }
    }

    private void receiveGroupAdminsWeight(Person groupAdmin) {
        while (true) {
            System.out.println("Enter weight: ");
            String weight = scan();
            if (weight.equals("exit")) {
                exit = 1;
            } else if (FieldValidator.weight(weight)) {
                groupAdmin.setWeight(Integer.parseInt(weight));
            } else {
                System.out.println("Invalid weight input, please try again");
                continue;
            }
            break;
        }
    }

    private String receiveDateComponent(DateComponent dateComponent) {
        while (true) {
            System.out.println("Enter " + dateComponent.toString().toLowerCase() + ":");
            String strDateComponent = scan();
            if (strDateComponent.equals("exit")) {
                exit = 1;
                break;
            } else if (FieldValidator.dateComponent(strDateComponent, dateComponent)) {
                return strDateComponent;
            } else {
                System.out.println("Invalid " + dateComponent.toString().toLowerCase() + " input, please try again");
                if (dateComponent.equals(DateComponent.ZONE)) {
                    System.out.println("Would you like to see available zones? y/n");
                    while (true) {
                        String answer = scan();
                        if (answer.equals("exit")) {
                            exit = 1;
                            break;
                        } else if (answer.equals("y")) {
                            System.out.println("Zones available: ");
                            for (String s : ZoneId.getAvailableZoneIds()) {
                                System.out.println(s);
                            }
                            break;
                        } else if (answer.equals("n")) {
                            break;
                        } else {
                            System.out.println("Invalid input, please try again");
                        }
                    }
                }
            }
        }
        return "";
    }

    private String scan() {
        if (scanner.hasNextLine()) {
            return DeleteSpaces.delete(scanner.nextLine());
        } else {
            return "exit";
        }
    }
}