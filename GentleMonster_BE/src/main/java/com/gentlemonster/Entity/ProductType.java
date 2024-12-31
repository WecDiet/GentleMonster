package com.gentlemonster.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Table(name = "product_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 5000, nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private boolean active;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "productType")
    @JsonIgnoreProperties("productType")
    private List<Product> products = new ArrayList<>();

}
