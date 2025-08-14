package com.sparklecow.pandawok.order.entity;

import com.sparklecow.pandawok.order.model.ProductType;
import com.sparklecow.pandawok.order.model.Status;
import com.sparklecow.pandawok.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Auditing
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false, nullable = true)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private Long amount;

    // Reference to the user client who placed the order through the application.
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private User client;

    // Reference to the user who goes to send the order
    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private User delivery;;

    private String address;

    private String clientName;

    private String additionalInformation;
}