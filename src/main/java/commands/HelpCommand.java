package commands;
public class HelpCommand {
    private String output;
    public HelpCommand() {
        setOutput();
    }
    public void execute() {
        System.out.println(output);
    }
    private void setOutput(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("help - вывести справку по доступным командам").append("\n");
        stringBuilder.append("info - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)").append("\n");
        stringBuilder.append("show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении").append("\n");
        stringBuilder.append("insert key (Integer) {element} - добавить новый элемент с заданным ключом").append("\n");
        stringBuilder.append("update id (Integer) {element} - обновить значение элемента коллекции, id которого равен заданному").append("\n");
        stringBuilder.append("remove_key key (Integer) - удалить элемент из коллекции по его ключу").append("\n");
        stringBuilder.append("clear - очистить коллекцию").append("\n");
        stringBuilder.append("execute_script file_name - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.").append("\n");
        stringBuilder.append("exit - завершить программу (без сохранения в файл)").append("\n");
        stringBuilder.append("remove_lower {element} - удалить из коллекции все элементы, меньшие, чем заданный").append("\n");
        stringBuilder.append("replace_if_lower key (Integer) {element} - заменить значение по ключу, если новое значение меньше старого").append("\n");
        stringBuilder.append("remove_greater_key key (Integer) - удалить из коллекции все элементы, ключ которых превышает заданный").append("\n");
        stringBuilder.append("remove_any_by_form_of_education formOfEducation (\"DISTANCE_EDUCATION\", \"FULL_TIME_EDUCATION\", \"EVENING_CLASSES\") - удалить из коллекции один элемент, значение поля formOfEducation которого эквивалентно заданному").append("\n");
        stringBuilder.append("count_by_form_of_education formOfEducation (\"DISTANCE_EDUCATION\", \"FULL_TIME_EDUCATION\", \"EVENING_CLASSES\") - вывести количество элементов, значение поля formOfEducation которых равно заданному").append("\n");
        stringBuilder.append("filter_greater_than_students_count studentsCount (integer) - вывести элементы, значение поля studentsCount которых больше заданного");
        output = stringBuilder.toString();
    }
    public String getOutput() {
        return output;
    }
}
