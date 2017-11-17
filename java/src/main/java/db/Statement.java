package db;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Statement {

  private final Type type;
  private Row rowToInsert;

  public Statement(Type type) {
    this.type = type;
  }

  public void execute(Table table) {
    switch (type) {
      case INSERT:
        table.insert(rowToInsert);
        break;
      case SELECT:
        for (byte[] bytes : table.getContent()) {
          try (ByteArrayInputStream stream = new ByteArrayInputStream(bytes)) {
            Row.deserialize(stream).ifPresent(System.out::println);
          } catch (IOException ignored) {
          }
        }
        System.out.println("This is where we would do a select.");
        break;
    }
  }

  public Statement setRowToInsert(Row rowToInsert) {
    this.rowToInsert = rowToInsert;
    return this;
  }

  public enum Type {
    INSERT,
    SELECT,
  }

}

