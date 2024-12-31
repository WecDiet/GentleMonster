package com.gentlemonster.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "image", length = 300, nullable = false)
    private String image;

    @Column(name = "status", nullable = false)
    private boolean active;

    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @Column(name = "slug", length=100, nullable = false)
    private String slug;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private Set<ProductType> productType = new HashSet<>();

    public Category(String name, String image, boolean active, Date createdDate,String category,String slug, Set<ProductType> productType) {
        this.name = name;
        this.image = image;
        this.active = active;
        this.createdDate = createdDate;
        this.productType = productType;
        this.category = category;
        this.slug = slug;
    }
}
