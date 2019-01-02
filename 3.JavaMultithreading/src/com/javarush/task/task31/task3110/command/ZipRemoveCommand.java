package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Удаление архива или файла в архиве.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите путь файла, которого будем удалять:");
        Path destinationPath = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(destinationPath);

        ConsoleHelper.writeMessage("Файл удалён.");
    }
}
