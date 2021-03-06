package com.sda.onlineAuction.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer startingPrice;
    private Category category;
    private LocalDateTime endDateTime;
    @Lob
    private byte[] image;

}
