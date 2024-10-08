package com.alten.altenshopback.errorsmodel;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
 private String message;
 private String status;
 private int code;
 private List<Violation> violations;
}
