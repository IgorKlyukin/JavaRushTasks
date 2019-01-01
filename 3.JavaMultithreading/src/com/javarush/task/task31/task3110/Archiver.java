package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String pathZip, pathFile;
        Operation operation = null;
//        do {
//            System.out.print("Full pathZip: ");
//            pathZip = reader.readLine();
//        }while (!pathZip.matches(".:\\\\[^\\\\]+.*(\\.zip)$"));
//        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(pathZip));


//        do {
//            System.out.print("Full pathFile: ");
//            pathFile = reader.readLine();
//        }while (!pathFile.matches(".:\\\\[^\\\\]+.*"));
//        try {
//            zipFileManager.createZip(Paths.get(pathFile));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            }
            catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            }
            catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        }while (!Operation.EXIT.equals(operation));


    }

    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(Operation.CREATE.ordinal() + " - упаковать файлы в архив");
        ConsoleHelper.writeMessage(Operation.ADD.ordinal() + " - добавить файл в архив");
        ConsoleHelper.writeMessage(Operation.REMOVE.ordinal() + " - удалить файл из архива");
        ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " - распаковать архив");
        ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " - просмотреть содержимое архива");
        ConsoleHelper.writeMessage(Operation.EXIT.ordinal() + " - выход");
        return Operation.values()[ConsoleHelper.readInt()];
    }

}
