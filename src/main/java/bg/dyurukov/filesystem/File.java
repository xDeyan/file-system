package bg.dyurukov.filesystem;

public interface File {

  String getName();

  boolean isDirectory();

  String getPath();

  boolean delete();

  default boolean isRoot() {
    return getName().isEmpty();
  }
}
