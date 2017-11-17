package db;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Table {

  private byte[][] content;

  public boolean insert(Row r) {
    try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){
      boolean result = r.serialize(stream);
      byte[] bytes = stream.toByteArray();
      content.add(bytes);
      return result;
    } catch (IOException ignored) {
      return false;
    }
  }

  public List<byte[]> getContent() {
    return content;
  }
}
