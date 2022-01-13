package bg.dyurukov.filesystem;

public class ChangeDirectoryCommand implements FileCommand {

  private static final String ROOT_DIRECTORY = "~";

  private String[] args;

  private FileSystem fileSystem;

  public ChangeDirectoryCommand(FileSystem fileSystem, String[] args) {
    this.fileSystem = fileSystem;
    this.args = args;
  }

  @Override
  public File execute() {

    var currentDir = fileSystem.getCurrentDirectory();

    if (ROOT_DIRECTORY.equals(args[1])) {
      fileSystem.changeToRoot();
      return fileSystem.getCurrentDirectory();
    }

    var directory = currentDir.findDirectory(args[1]);

    if (directory != null) {
      fileSystem.changeDirectory(directory);
    }

    return directory;
  }
}
