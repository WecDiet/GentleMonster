package com.gentlemonster.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name = "description", length = 5000, nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    private Set<String> images = new HashSet<>();

    @Column(name = "status", nullable = false)
    private boolean isActive;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    // Khung
    @Column(name = "frame", length = 100)
    private String frame;

    // Loại lens
    @Column(name = "lens", length = 100)
    private String lens;

    // Hình dàng
    @Column(name = "shape", length = 100)
    private String shape;

    // Chất liệu
    @Column(name = "material", length = 100)
    private String material;

    // Chiều rộng của lens
    @Column(name = "lens_width")
    private double lens_Width;

    // Chiều cao của lens
    @Column(name = "lens_height")
    private double lens_Height;

    // Vòng cầu của kính
    @Column(name = "bridge")
    private double bridge;

    // Nước sản xuất
    @Column(name = "country", length = 100)
    private String country;

    // Nhà sản xuất
    @Column(name = "manufacturer", length = 200)
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToMany(mappedBy = "likeProductList")
    @JsonIgnore
    private List<User> userLikedList;

    @JsonIgnore
    @ManyToMany(mappedBy = "viewedProductList")
    private Set<User> userViewedList;

}
