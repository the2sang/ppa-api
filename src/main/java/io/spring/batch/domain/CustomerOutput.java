package io.spring.batch.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOutput {

  @Id
  private long id;
  private String firstName;
  private String lastName;

}