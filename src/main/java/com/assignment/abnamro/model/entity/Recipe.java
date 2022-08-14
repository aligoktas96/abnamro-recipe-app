package com.assignment.abnamro.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isVegetarian")
    private Boolean isVegetarian;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<RecipeIngredient> ingredient;

    @Column(name = "servings")
    private int servings;

    @Column(name = "instruction")
    private String instruction;
}
