package codetest.codetest.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity to store calculations for loans
 */
@Entity
@Getter @Setter @ToString(callSuper=true) 
@Table(name = "calculations") 
public class Calculation extends BaseEntity {
    
    //id from base entity
    
    @Column(nullable = false)
    public String name; 

    @Column(nullable = false)
    public Double totalLoan; 

    @Column(nullable = false)
    public Double interest;

    @Column(nullable = false)
    public Integer years;

    @Column
    public Double monthlyPayment;

    @Column
    private Date date; 
}