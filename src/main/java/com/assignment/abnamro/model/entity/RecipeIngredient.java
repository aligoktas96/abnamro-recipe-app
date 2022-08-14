package com.assignment.abnamro.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipeingredient")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "recipe_id", updatable = false, insertable = false)
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "name")
    private String name;
}
