package com.gentlemonster.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 45, nullable = false)
    private String middleName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "full_name", length = 150, nullable = false)
    private String fullName;

    @Column(name = "email", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "employeeCode", length = 8, unique = true)
    private String employeeCode;

    @Column(name = "phoneNumber", length = 45,nullable = false, unique = true)
    private String  phoneNumber;

    @Column(name = "sex")
    private boolean sex = true;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "street", length = 200)
    private String street;

    @Column(name = "ward", length = 150)
    private String ward;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "photos", length = 150)
    private String photos;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "cloudinaryImageId")
    private String cloudinaryImageId;

    @Column(name = "facebook_account_id")
    private int facebookAccountId;

    @Column(name = "google_account_id")
    private int googleAccountId;

    // bảng table user sẽ join với bảng role thông qua bảng user_role với user_id và role_id là khóa ngoại của 2 bảng user và role
    // Được quản lý bởi JPA thông qua Set<Role> roles = new HashSet<>();
    // mối quan hệ nhiều nhièu giữa user và role thông qua bảng user_role
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "user_like_product",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_id", nullable = false)
    )
    List<Product> likeProductList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_view_product",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_id", nullable = false)
    )
    Set<Product> viewedProductList = new HashSet<>();

    public User(String firstName,
                String middleName,
                String lastName,
                String fullName,
                String email,
                String employeeCode,
                String phoneNumber,
                boolean sex,
                Date birthday,
                String street,
                String ward,
                String district,
                String city,
                String username,
                String password,
                Date createdDate,
                String photos,
                boolean status,
                String cloudinaryImageId,
                int facebookAccountId,
                int googleAccountId,
                Set<Role> roles,
                List<Product> likeProductList,
                Set<Product> viewedProductList) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.employeeCode = employeeCode;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.birthday = birthday;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.photos = photos;
        this.status = status;
        this.cloudinaryImageId = cloudinaryImageId;
        this.facebookAccountId = facebookAccountId;
        this.googleAccountId = googleAccountId;
        this.roles = roles;
        this.likeProductList = likeProductList;
        this.viewedProductList = viewedProductList;
    }
}
