package bio.overture.ego.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import bio.overture.ego.view.Views;

import java.util.Set;

@AllArgsConstructor
@Getter
@JsonView(Views.REST.class)
public class TokenScopeResponse {
  private String user_name;
  private String client_id;
  private Long exp;
  private Set<String> scope;
}