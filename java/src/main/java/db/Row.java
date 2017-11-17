package db;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Optional;

public class Row implements Serializable {

  private Integer id;
  private String username;
  private String email;

  public Row() {

  }

  public Row(Integer id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public static Optional<Row> deserialize(InputStream stream) {
    try {
      ByteBuffer buffer = ByteBuffer.wrap(stream.readAllBytes());
      Integer id = buffer.getInt();
      Integer usernameLength = buffer.getInt();

      byte[] bytesUsername = new byte[usernameLength];
      buffer.get(bytesUsername);

      Integer emailLength = buffer.getInt();

      byte[] bytesEmail = new byte[emailLength];
      buffer.get(bytesEmail);

      String username = new String(bytesUsername);
      String email = new String(bytesEmail);

      return Optional.of(new Row(id, username, email));
    } catch (IOException | BufferUnderflowException e) {
      return Optional.empty();
    }
  }

  public Integer getId() {
    return id;
  }

  public Row setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public Row setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Row setEmail(String email) {
    this.email = email;
    return this;
  }

  public boolean serialize(OutputStream stream) {
    try {
      int lengthUsername = username.getBytes().length;
      int lengthEmail = email.getBytes().length;
      byte[] array = ByteBuffer
        .allocate(4 + 4 + lengthUsername + 4 + lengthEmail)
        .putInt(id)
        .putInt(lengthUsername)
        .put(username.getBytes())
        .putInt(lengthEmail)
        .put(email.getBytes())
        .array();

      stream.write(array);
      return true;
    } catch (IOException | BufferOverflowException e) {
      return false;
    }
  }

}
