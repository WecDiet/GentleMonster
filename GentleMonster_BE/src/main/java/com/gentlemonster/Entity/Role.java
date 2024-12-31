package com.gentlemonster.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name="name", length = 40, nullable = false)
    private String name;
    @Column(name="description", length = 250, nullable = false)
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
