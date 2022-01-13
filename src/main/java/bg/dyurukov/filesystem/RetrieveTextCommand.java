package bg.dyurukov.filesystem;

public class RetrieveTextCommand implements FileCommand {

  private FileSystem fileSystem;

  private String[] args;

  public RetrieveTextCommand(FileSystem fileSystem, String[] args) {
    this.fileSystem = fileSystem;
    this.args = args;
  }

  @Override
  public File execute() {

    var query = args[1];

    var file = fileSystem.getCurrentDirectory().findFile(query);

    if (file != null) {
      System.out.println(file.getContent());
    }

    return file;
  }
}
