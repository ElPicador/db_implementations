package db;

import java.util.Optional;

public class PrepareStatement {

  private final String line;

  public PrepareStatement(String line) {
    this.line = line;
  }

  public Statement prepare() {
    if (line.startsWith("insert")) {
      SafeScanner scanner = new SafeScanner(line);
      Row row = new Row();
      Optional<Row> optional = scanner
        .next("insert")
        .flatMap(insert /*ignore*/ -> scanner.nextInt())
        .flatMap(id -> {
          row.setId(id);
          return scanner.next();
        })
        .flatMap(username -> {
          row.setUsername(username);
          return scanner.next();
        })
        .map(row::setEmail);

      if (optional.isPresent()) {
        return new Statement(Statement.Type.INSERT).setRowToInsert(row);
      } else {
        return null;
      }
    }

    if (line.startsWith("select")) {
      return new Statement(Statement.Type.SELECT);
    }

    return null;
  }

}
