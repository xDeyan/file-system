package bg.dyurukov.filesystem;

public class CreateDirectoryCommand implements FileCommand {

  private String[] args;

  private FileSystem fileSystem;

  public CreateDirectoryCommand(FileSystem fileSystem, String[] args) {
    this.fileSystem = fileSystem;
    this.args = args;
  }

  @Override
  public File execute() {
    var currentDirectory = fileSystem.getCurrentDirectory();
    var file = new Directory(args[1], currentDirectory);

    System.out.println("Directory " + file.getPath() + " created.");

    currentDirectory.addFile(file);
    return file;
  }
}
