package com.gentlemonster.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "rating", length = 500, nullable = false)
    private int rating; // Điểm đánh giá (1-5)

    @Column(name = "photo", length = 5000, nullable = false)
    private String photo; // Hình ảnh đính kèm

    @Column(name = "status", nullable = false)
    private boolean active; // Trạng thái

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate; // Ngày tạo

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate; // Ngày sửa

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
