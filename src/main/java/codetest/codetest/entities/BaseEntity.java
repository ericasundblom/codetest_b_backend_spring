package codetest.codetest.entities;

import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Base Entity that can be used for several entities
 */
@MappedSuperclass
@Getter @Setter @ToString
public abstract class BaseEntity {

    @Id
	// MySQL/MariaDB requires some specials, see https://tinyurl.com/y8wfmxca
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")	
	@GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return this.id != null && Objects.equals(this.id, that.id);
    }

}