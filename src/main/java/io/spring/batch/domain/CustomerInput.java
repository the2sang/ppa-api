package io.spring.batch.domain;

import lombok.*;
import javax.persistence.*;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInput {

  @Id
  private long id;
  private String firstName;
  private String lastName;

}