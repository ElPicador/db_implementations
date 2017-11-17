package db;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class SafeScanner {

  private final Scanner inner;

  public SafeScanner(String input) {
    this.inner = new Scanner(input);
  }

  public SafeScanner(InputStream source) {
    this.inner = new Scanner(source);
  }

  public Optional<String> nextLine() {
    try {
      return Optional.ofNullable(inner.nextLine());
    } catch (Exception ignored) {
      return Optional.empty();
    }
  }

  public Optional<String> next(String pattern) {
    try {
      return Optional.ofNullable(inner.next(pattern));
    } catch (Exception ignored) {
      return Optional.empty();
    }
  }

  public Optional<String> next() {
    try {
      return Optional.ofNullable(inner.next());
    } catch (Exception ignored) {
      return Optional.empty();
    }
  }

  public Optional<Integer> nextInt() {
    try {
      return Optional.of(inner.nextInt());
    } catch (Exception ignored) {
      return Optional.empty();
    }
  }
}
