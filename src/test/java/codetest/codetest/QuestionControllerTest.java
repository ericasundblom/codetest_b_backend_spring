package codetest.codetest;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.AfterAll; //could be useful but only one test method here
// import org.junit.jupiter.api.AfterEach; //could be useful but only one test method here
// import org.junit.jupiter.api.BeforeAll; //could be useful but only one test method here
// import org.junit.jupiter.api.BeforeEach; //could be useful but only one test method here
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import codetest.codetest.controllers.QuestionController;
import codetest.codetest.entities.Calculation;
import codetest.codetest.repositories.CalcualtionRepository;


@SpringBootTest
class QuestionControllerTest {

    @Autowired
    private QuestionController questionController;
    
    @Autowired
    private CalcualtionRepository calcualtionRepository;

    /**
     * Test of calculateMonthlyPayment method, of class Calculate
     */
    @Test
    public void testCalculateMonthlyPayment() {

        System.out.println("Testing calculate monthly payment");

        // prepare testing material
        Calculation customer1 = new Calculation();
        customer1.setName("Juha");
        customer1.setTotalLoan(1000.0);
        customer1.setInterest(5.0);
        customer1.setYears(2);
        calcualtionRepository.save(customer1);

        Calculation customer2 = new Calculation();
        customer2.setName("Karvinen");
        customer2.setTotalLoan(4356.0);
        customer2.setInterest(1.27);
        customer2.setYears(6);
        calcualtionRepository.save(customer2);

        Calculation customer3 = new Calculation();
        customer3.setName("Claes Månsson");
        customer3.setTotalLoan(1300.55);
        customer3.setInterest(8.67);
        customer3.setYears(2);
        calcualtionRepository.save(customer3);

        // make calculation
        questionController.calculateMonthlyPayment(customer1);
        questionController.calculateMonthlyPayment(customer2);
        questionController.calculateMonthlyPayment(customer3);

        // test that results are the expected
        assertEquals(43.8713897340686, customer1.getMonthlyPayment());
        assertEquals(62.86631476623255, customer2.getMonthlyPayment());
        assertEquals(59.21856882868132, customer3.getMonthlyPayment());

        // remove unnecessary mtrl form db
        calcualtionRepository.delete(customer1);
        calcualtionRepository.delete(customer2);
        calcualtionRepository.delete(customer3);

        System.out.println("End of test for calculate monthly payment");
    } 
}