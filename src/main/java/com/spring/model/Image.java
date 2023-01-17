package com.spring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Lob
    @Column(name="profileImg",length = 1000)
    private byte[] imageData;

    @OneToOne(mappedBy = "image")
    private User user;

}
