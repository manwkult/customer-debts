package co.com.evertec.postgres.entity;

import co.com.evertec.postgres.entity.audit.Audit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "public",
        name = "customerdebt",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"customerIdentifier", "debtIdentifier"})
        })
public class CustomerDebtEntity extends Audit {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false)
    private String customerIdentifier;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "numeric", length = 20)
    private BigDecimal amount;

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false)
    private String debtIdentifier;

    @Column(nullable = false)
    private Date expirationDate;
}
