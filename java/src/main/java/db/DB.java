package db;

import java.io.IOException;
import java.util.Optional;

public class DB {

  public static final Table table = new Table();

  public static void main(String[] args) throws IOException {
    SafeScanner input = new SafeScanner(System.in);

    while (true) {
      printPrompt();
      Optional<String> maybeLine = input.nextLine();

      if (!maybeLine.isPresent()) {
        System.out.println("Error reading input");
        System.exit(1);
      }

      String line = maybeLine.get();
      if (line.startsWith(".")) {
        if (new MetaCommand(line).validateAndExecute()) {
          continue;
        } else {
          System.out.printf("Unrecognized command '%s'.\n", line);
          continue;
        }
      }

      Statement statement = new PrepareStatement(line).prepare();
      if (statement != null) {
        statement.execute(table);
        System.out.println("Executed.");
      } else {
        System.out.printf("Unrecognized keyword at start of '%s'.\n", line);
      }
    }

  }

  private static void printPrompt() {
    System.out.print("db > ");
  }
}

