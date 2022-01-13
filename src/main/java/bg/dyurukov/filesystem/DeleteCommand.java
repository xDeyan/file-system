package bg.dyurukov.filesystem;

public class DeleteCommand implements FileCommand {

  private FileSystem fileSystem;
  private String[] args;

  public DeleteCommand(FileSystem fileSystem, String... args) {
    this.fileSystem = fileSystem;
    this.args = args;
  }

  @Override
  public File execute() {
    var fileName = args[1];

    var directory = fileSystem.getCurrentDirectory().findDirectory(fileName);

    if (directory != null) {
      directory.delete();
      return directory;
    }

    var file = fileSystem.getCurrentDirectory().findFile(fileName);

    if (file != null) {
      file.delete();
      return file;
    }

    return null;
  }
}
