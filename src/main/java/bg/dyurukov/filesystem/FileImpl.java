package bg.dyurukov.filesystem;

public class FileImpl implements File {

  private Directory parent;

  private String name;

  private String content;

  public FileImpl(Directory parent, String name, String content) {
    this.name = name;
    this.content = content;
    this.parent = parent;
  }

  public String getContent() {
    return this.content;
  }

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public String getPath() {
    return this.parent.getPath() + "/" + this.name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean delete() {
    return this.parent.deleteFile(this);
  }
}
