package db;

public class MetaCommand {

  private final String inputCommand;

  public MetaCommand(String inputCommand) {
    this.inputCommand = inputCommand;
  }

  public boolean validateAndExecute() {
    if (inputCommand.equalsIgnoreCase(".exit")) {
      System.exit(0);
      return true;
    }
    return false;
  }
}
