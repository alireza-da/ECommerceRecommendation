package main.java.models.database.annotations;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Purchase",
        uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class PurchaseMapper {
}
