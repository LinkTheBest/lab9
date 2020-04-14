import commandsRealization.AddCommandMethods;
import commandsRealization.FindMaxElement;
import commandsRealization.FindMinElement;
import commandsRealization.LowerRemover;
import server.JsonDataHandler;
import spaceMarineProperties.SpaceMarine;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class UserInputHandler {
    List<File> tempList;
    JsonDataHandler jsonDataHandler = new JsonDataHandler();
    Deque<SpaceMarine> spaceDeque;
    private int arraySize = 0;

    {
        tempList = new LinkedList<>();
    }

    public UserInputHandler() {
        arraySize = jsonDataHandler.getJsonCollectionSize();
        spaceDeque = addStartElement();
    }

    public Deque<SpaceMarine> addStartElement() {
        spaceDeque = new ArrayDeque<>(createAStartObject());
        return spaceDeque;
    }

    public void addElement(SpaceMarine spc_mrn) {
        spaceDeque.add(spc_mrn);
    }

    public void infoCommand() {
        System.out.println("Size: " + spaceDeque.size());
        System.out.println("isEmpty: " + spaceDeque.isEmpty());
        System.out.println("Type: " + spaceDeque.getClass());
    }

    public void showCommand() {
        for (SpaceMarine s : spaceDeque) {
            System.out.println("-----------------");
            System.out.println("name: " + s.getName());
            System.out.println("id: " + s.getId());
            System.out.println("Astartes Category: " + s.getCategory());
            System.out.print("Coordinate X: " + s.getCoordinates().getX() + " ");
            System.out.println("Coordinate Y: " + s.getCoordinates().getY());
            System.out.println("Health: " + s.getHealth());
            System.out.println("Weapon Type: " + s.getWeaponType());
            System.out.println("MeleeWeapon Type: " + s.getMeleeWeapon());
            System.out.println("Chapter: " + s.getChapter().getName());
            System.out.println("-----------------");
        }
    }

    public void addCommand(String name) {
        List<SpaceMarine> tempList = new ArrayList<>(spaceDeque);
        AddCommandMethods addCommandMethods = new AddCommandMethods();
        SpaceMarine addedSpcMrn = new SpaceMarine();
        addedSpcMrn.setName(name);
        addedSpcMrn.setId(addCommandMethods.readId());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setCoordinates(addCommandMethods.readCoordinates());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setHealth(addCommandMethods.readHealth());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setChapter(addCommandMethods.readChapterName());
// --------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setWeaponType(addCommandMethods.readWeaponType());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setMeleeWeapon(addCommandMethods.readMeleeWeaponCategory());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setCategory(addCommandMethods.readAstartesCategory());
// ---------------------------------------------------------------------------------------------------------------------
        tempList.add(addedSpcMrn);
        spaceDeque = new ArrayDeque<>(tempList);
        System.out.println("Элемент успешно добавлен!");
    }

    public void removeByIdCommand(int index) {
        List<SpaceMarine> tempList = new ArrayList<>(spaceDeque);
        Iterator<SpaceMarine> iterator = tempList.iterator();
        try {
            while (iterator.hasNext()) {
                SpaceMarine scp = iterator.next();
                if (scp.getId() == index) {
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        spaceDeque = new ArrayDeque<>(tempList);
    }

    public void clearCommand() {
        spaceDeque.clear();
    }

    public void saveCommand() {
        JSONArray toFileArray = new JSONArray();
        for (SpaceMarine spcMrn : spaceDeque) {
            JSONObject toJsonArrayObj = new JSONObject();
            try {
                toJsonArrayObj.put("name", spcMrn.getName());
                toJsonArrayObj.put("id", spcMrn.getId());
                toJsonArrayObj.put("coordinate_x", spcMrn.getCoordinates().getX());
                toJsonArrayObj.put("coordinate_y", spcMrn.getCoordinates().getY());
                toJsonArrayObj.put("health", spcMrn.getHealth());
                toJsonArrayObj.put("category", spcMrn.getCategory().name());
                toJsonArrayObj.put("weapon", spcMrn.getWeaponType().name());
                toJsonArrayObj.put("melee_weapon", spcMrn.getMeleeWeapon().name());
                toJsonArrayObj.put("chapter", spcMrn.getChapter().getName());
                toFileArray.add(toJsonArrayObj);
            } catch (Exception e) {
                System.out.println("Какое-то из полей не заполнено!");
            }
        }
        try {
            File finalFile = new File(System.getenv("JSON"));
            PrintWriter writer = new PrintWriter(finalFile);
            writer.print("");
            writer.close();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(finalFile));
            outputStream.write(toFileArray.toJSONString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (java.io.FileNotFoundException err) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void executeSctiptCommand(String user_input) {
        File file = new File(user_input);
        if (tempList.lastIndexOf(file) == -1) {
            tempList.add(file);
            try {
                if (!file.exists() || !file.isFile()) {
                    System.out.println(("Файл не существует."));
                    return;
                }
                if (!file.canRead()) {
                    System.out.println("Файл защищён от чтения!");
                    return;
                }
                if (file.length() == 0) {
                    System.out.println("Команды отсутствуют!");
                    return;
                }
                Scanner tempScn = new Scanner(file);
                while (tempScn.hasNext()) {
                    try {
                        int i = 0;
                        String temp = tempScn.nextLine();
                        String[] tempSec = temp.split("\\s");
                        String command = tempSec[0];
                        switch (command) {
                            case "help":
                                commandList();
                                break;
                            case "info":
                                infoCommand();
                                break;
                            case "show":
                                showCommand();
                                break;
                            case "add":
                                if (tempSec[tempSec.length - 1].equals("add")) {
                                    System.out.println("Введите имя после команды!");
                                    break;
                                }
                                addCommand(tempSec[tempSec.length - 1]);
                                break;
                            case "remove_by_id":
                                try {
                                    i = Integer.parseInt(tempSec[tempSec.length - 1]);
                                } catch (Exception e) {
                                    System.out.println("Введите число!");
                                }
                                removeByIdCommand(i);
                                break;
                            case "clear":
                                clearCommand();
                                break;
                            case "save":
                                saveCommand();
                                break;
                            case "execute_script":
                                executeSctiptCommand(tempSec[tempSec.length - 1]);
                                break;
                            case "exit":
                                System.exit(0);
                                break;
                            case "add_if_max":
                                addIfMaxCommand(tempSec[tempSec.length - 1]);
                                break;
                            case "add_if_min":
                                addIfMinCommand(tempSec[tempSec.length - 1]);
                                break;
                            default:
                                System.out.println("Неизвестная команда");
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                tempList.remove(0);
            } catch (Exception e) {
                System.out.println("Файл не найден!");
            }
        } else System.out.println("Обнаружен цикл!");
    }

    public void addIfMaxCommand(String name) {
        FindMaxElement findMax = new FindMaxElement();
        addCommand(name);
        List<SpaceMarine> temp_list = new ArrayList<>(spaceDeque);
        spaceDeque = findMax.makeDecision(temp_list);
    }

    public void addIfMinCommand(String name) {
        FindMinElement findMin = new FindMinElement();
        addCommand(name);
        List<SpaceMarine> temp_list = new ArrayList<>(spaceDeque);
        spaceDeque = findMin.makeDecision(temp_list);
    }

    public void removeLowerCommand(String name) {
        LowerRemover lowerRemover = new LowerRemover();
        addCommand(name);
        List<SpaceMarine> tempList = new ArrayList<>(spaceDeque);
        spaceDeque = lowerRemover.removeLowerElement(tempList);

    }

    public void sumOfHealthCommand() {
        int sum = 0;
        for (SpaceMarine spc : spaceDeque) {
            sum = sum + spc.getHealth();
        }
        System.out.println("Сумма полей health:" + sum);
    }

    public void printDescendingCommand() {
        List<SpaceMarine> temp = new ArrayList<>(spaceDeque);
        Collections.sort(temp);
        spaceDeque = new ArrayDeque<>(temp);
        showCommand();
    }

    public void printFieldDescendingHealthCommmand() {
        List<SpaceMarine> temp = new ArrayList<>(spaceDeque);
        Collections.sort(temp);
        spaceDeque = new ArrayDeque<>(temp);
        for (SpaceMarine spc : spaceDeque) {
            System.out.println(spc.getHealth());
        }

    }


    public List<SpaceMarine> createAStartObject() {
        List<SpaceMarine> startObjList = new LinkedList<SpaceMarine>();
        for (int counter = 0; counter < arraySize; counter++) {
            SpaceMarine tempSpc = new SpaceMarine();
            tempSpc.setName(jsonDataHandler.getName(counter));
            if (jsonDataHandler.getId(counter) != 0) {
                tempSpc.setId(jsonDataHandler.getId(counter));
            }
            tempSpc.setHealth(jsonDataHandler.getHealth(counter));
            tempSpc.setCoordinates(jsonDataHandler.getCoordinates(counter));
            tempSpc.setCategory(jsonDataHandler.getAstartesCategory(counter));
            tempSpc.setWeaponType(jsonDataHandler.getWeapon(counter));
            tempSpc.setMeleeWeapon(jsonDataHandler.getMeleeWeapon(counter));
            tempSpc.setChapter(jsonDataHandler.getChapter(counter));
            startObjList.add(tempSpc);
        }
        return startObjList;
    }

    public void commandList() {
        Map<String, String> commandList = new HashMap<>();
        commandList.put("help", "вывести справку по доступным командам");
        commandList.put("info", "вывести в стандартный поток вывода информацию о коллекции");
        commandList.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandList.put("add {element}", "добавить новый элемент в коллекцию");
        commandList.put("update_id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        commandList.put("remove_by_id id", "удалить элемент из коллекции по его id");
        commandList.put("clear", "очистить коллекцию");
        commandList.put("save", "сохранить коллекцию в файл");
        commandList.put("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandList.put("exit", "завершить программу (без сохранения в файл)");
        commandList.put("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        commandList.put("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        commandList.put("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        commandList.put("sum_of_health", "вывести сумму значений поля health для всех элементов коллекции");
        commandList.put("print_descending", "вывести элементы коллекции в порядке убывания");
        commandList.put("print_field_descending_health {health}", "вывести значения поля health в порядке убывания");

        Iterator<Map.Entry<String, String>> iterator = commandList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());

        }
    }
}
