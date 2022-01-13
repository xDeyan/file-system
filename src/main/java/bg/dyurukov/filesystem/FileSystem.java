package bg.dyurukov.filesystem;

import java.util.Optional;
import java.util.Scanner;

public class FileSystem {

  private static final String EXIT_CMD_NAME = "exit";

  private Directory root;

  private Directory currentDirectory;

  public Directory getCurrentDirectory() {
    return this.currentDirectory;
  }

  public static void create() {
    new FileSystem().init();
  }

  public void init() {
    this.currentDirectory = new Directory("", null);
    this.root = this.currentDirectory;
    receiveInput();
  }

  private void receiveInput() {
    try (var scanner = new Scanner(System.in)) {
      String commandName = null;

      do {
        System.out.println("Current directory: " + this.currentDirectory.getPath());

        var commandLine = scanner.nextLine();

        var parts = commandLine.split("\\s+");

        commandName = commandLine.split("\\s+")[0];

        var args = parts;

        getCommand(commandName, args)
            .ifPresentOrElse(FileCommand::execute, unknownCommand(commandName));

      } while (!EXIT_CMD_NAME.equals(commandName));
    }
  }

  private Runnable unknownCommand(String name) {
    return () -> System.out.println("Unknown command [" + name + "]");
  }

  private Optional<FileCommand> getCommand(String commandName, String[] args) {
    FileCommand command = null;
    switch (commandName) {
      case "mkdir":
        {
          command = new CreateDirectoryCommand(this, args);
          break;
        }
      case "touch":
        {
          command = new CreateFileCommand(this, args);
          break;
        }
      case "cat":
        {
          command = new RetrieveTextCommand(this, args);
          break;
        }

      case "cd":
        {
          command = new ChangeDirectoryCommand(this, args);
          break;
        }
      case "rm":
        {
          command = new DeleteCommand(this, args);
          break;
        }
      case "find":
        {
          command = new SearchFileCommand(this, args);
          break;
        }
    }
    return Optional.ofNullable(command);
  }

  public void changeDirectory(Directory directory) {
    this.currentDirectory = directory;
  }

  public void changeToRoot() {
    this.changeDirectory(this.root);
  }
}
