package support;
public class DeleteSpaces {
    public static String delete(String string) {
        if (!string.isBlank()) {
            String[] splittedString = string.split("");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < splittedString.length; i++) {
                if ((!splittedString[i].equals(" ")) && (!splittedString[i].isBlank())) {
                    result.append(splittedString[i]);
                } else {
                    if (i != (splittedString.length - 1)) {
                        if ((!splittedString[i+1].equals(" ")) && (!splittedString[i+1].isBlank()) && (result.length() != 0)) {
                            result.append(" ");
                        }
                    }
                }
            }
            return result.toString();
        } else {return "";}
    }
}
