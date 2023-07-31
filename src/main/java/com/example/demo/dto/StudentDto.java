package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public record StudentDto(
      String firstName,
      String lastName,
      String email,
      String gender,
      String contry,
      String city,
      String postalCode,
      List<String> favoriteSubjects,
      BigDecimal totalSpentInBooks
) {
}
