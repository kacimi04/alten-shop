package com.alten.altenshopback.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
	
 
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alten_shop_sequence")
  @SequenceGenerator(name = "alten_shop_sequence", sequenceName = "alten_shop_sequence",allocationSize =1)
  private Integer id;
  @Column(name = "LIBELLE" , nullable = false,unique = true)
  private String libelle;
}
