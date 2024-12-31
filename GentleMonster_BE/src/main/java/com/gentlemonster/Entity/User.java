package com.gentlemonster.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

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

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @Column(name = "street", length = 200)
    private String street;

    @Column(name = "ward", length = 150)
    private String ward;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "city", length = 100)
    private String city;

//    @Column(name = "username")
//    private String username;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "country", length = 100)
    private String country;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "photos", length = 150)
    private String photos;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "userType")
    private int userType = 1;

    @Column(name = "cloudinaryImageId")
    private String cloudinaryImageId;

    @Column(name = "facebook_account_id")
    private String facebookAccountId;

    @Column(name = "google_account_id")
    private String googleAccountId;

    @Column(name = "resetToken")
    private String resetToken;

    @Column(name = "resetTokenExpiration")
    private Date resetTokenExpiration;

    @Column(name = "deviceToken")
    private String deviceToken;

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
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


    // Lấy danh sách quyền của user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorityList;
    }

    @ManyToMany
    @JoinTable(
            name = "wishlist",
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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<RefreshToken> refreshTokenList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedBackList = new ArrayList<>();


}
