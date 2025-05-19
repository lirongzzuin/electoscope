package com.electoscope.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pledges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidate;

    private String category;

    @Column(length = 1000)
    private String content;

    @Column(length = 20)
    private String sentiment;

}
