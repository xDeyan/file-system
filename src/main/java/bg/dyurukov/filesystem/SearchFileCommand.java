package bg.dyurukov.filesystem;

public class SearchFileCommand implements FileCommand {

  private FileSystem fileSystem;

  private String[] args;

  public SearchFileCommand(FileSystem fileSystem, String[] args) {
    this.fileSystem = fileSystem;
    this.args = args;
  }

  @Override
  public File execute() {
    var fileName = args[1];
    var file = fileSystem.getCurrentDirectory().find(fileName);

    if (file != null) {
      System.out.println("Found file: " + file.getPath());
    }

    return file;
  }
}
