package com.zakaria.ecom.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false) @NotEmpty @Size(min = 2)
    private  String name;
    @Column(nullable = false) @NotEmpty @Size(min = 10)
    private String description;
    @Column(nullable = false) @NotNull
    private Double price;
    @Column(nullable = false) @NotNull
    private Integer quantity;
    @Column(nullable = false)
    private Integer sold = 0;
    @Column(nullable = false) @NotEmpty
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
