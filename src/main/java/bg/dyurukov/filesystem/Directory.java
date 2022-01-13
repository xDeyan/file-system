package bg.dyurukov.filesystem;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Directory implements File {

  private Directory parent;

  private String name;

  private List<File> files;

  private List<Directory> directories;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.files = new LinkedList<>();
    this.directories = new LinkedList<>();
  }

  public boolean addFile(File file) {
    if (file.isDirectory()) {
      return this.directories.add((Directory) file);
    }

    return this.files.add(file);
  }

  public boolean removeFile(File file) {
    if (file.isDirectory()) {
      return this.directories.remove(file);
    }

    return this.files.remove(file);
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  @Override
  public String getPath() {
    return parent != null ? parent.getPath() + "/" + this.name : "/" + this.name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  public boolean deleteFile(File file) {
    if (file.isDirectory()) {
      return this.directories.remove(file);
    }
    return this.files.remove(file);
  }

  public File find(String query) {
    var file = findFile(query);

    if (file != null) {
      return file;
    }

    return findDirectory(query);
  }

  public FileImpl findFile(String query) {
    return query.contains("/") ? findFileByPath(query) : findFileByName(query);
  }

  public Directory findDirectory(String query) {
    return query.contains("/") ? findDirectoryByPath(query) : findDirectoryByName(query);
  }

  private FileImpl findFileByName(String name) {
    return this.findFile(name, File::getName);
  }

  private FileImpl findFileByPath(String path) {
    return this.findFile(path, File::getPath);
  }

  private Directory findDirectoryByName(String name) {
    return this.findDirectory(name, File::getName);
  }

  private Directory findDirectoryByPath(String path) {
    return this.findDirectory(path, File::getPath);
  }

  private FileImpl findFile(String name, Function<File, String> mapper) {
    for (File file : this.files) {
      if (mapper.apply(file).equals(name)) {
        return (FileImpl) file;
      }
    }

    return null;
  }

  private Directory findDirectory(String name, Function<File, String> mapper) {
    for (Directory directory : this.directories) {
      if (mapper.apply(directory).equals(name)) {
        return directory;
      } else {
        var dir = directory.findDirectory(name, mapper);
        if (dir != null) {
          return dir;
        }
      }
    }

    return null;
  }

  @Override
  public boolean delete() {
    return this.parent.deleteFile(this);
  }
}
