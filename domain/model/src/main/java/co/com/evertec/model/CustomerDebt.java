package co.com.evertec.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class CustomerDebt implements Serializable {

    @NotNull
    private long id;
    @NotNull
    private String customerIdentifier;
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String debtIdentifier;
    @NotNull
    private Date expirationDate;
}
