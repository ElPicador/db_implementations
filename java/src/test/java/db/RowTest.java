package db;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RowTest {

  @Test
  public void serializationDeserialization() {
    Row row = new Row(1, "username", "email");
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    row.serialize(stream);

    ByteArrayInputStream inputStream = new ByteArrayInputStream(stream.toByteArray());

    Optional<Row> rowOptional = Row.deserialize(inputStream);

    assertThat(rowOptional).isNotEmpty();
    assertThat(rowOptional.get()).isEqualToComparingFieldByField(row);
  }

}