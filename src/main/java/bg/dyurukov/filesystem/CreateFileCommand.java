package bg.dyurukov.filesystem;

public class CreateFileCommand implements FileCommand {

  private FileSystem fileSystem;
  private String[] args;

  public CreateFileCommand(FileSystem fileSystem, String... args) {
    this.fileSystem = fileSystem;
    this.args = args;
  }

  @Override
  public File execute() {
    var currentDirectory = fileSystem.getCurrentDirectory();
    var content = args.length == 3 ? args[2] : "";
    var file = new FileImpl(currentDirectory, args[1], content);
    currentDirectory.addFile(file);

    System.out.println("File " + file.getPath() + " created.");

    return file;
  }
}
