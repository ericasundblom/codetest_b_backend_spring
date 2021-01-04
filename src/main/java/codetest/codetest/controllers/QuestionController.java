package codetest.codetest.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codetest.codetest.entities.Calculation;
import codetest.codetest.repositories.CalcualtionRepository;


@CrossOrigin(origins = {"${app.cors.accept.1}"})
@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
	CalcualtionRepository calculationRepository;

	/**
	 * Calculate and save calculation to database
	 * @param customerInfo
	 * @return
	 */
    @PostMapping("/calculate")
    public ResponseEntity<String> addCalculation(@RequestBody Calculation customerInfo) {

		Date date = new Date();

		try {
			// grab the input from website to calculate with
			Calculation newCalculation = new Calculation();

			newCalculation.setName(customerInfo.getName()); 
			newCalculation.setTotalLoan(Double.parseDouble(customerInfo.getTotalLoan().toString()));
			newCalculation.setInterest(Double.parseDouble(customerInfo.getInterest().toString()));
			newCalculation.setYears(customerInfo.getYears());
			newCalculation.setDate(date); 		
			calculationRepository.save(newCalculation);

			// do calculation
			// set monthlypayment in calculation and repo save
			calculateMonthlyPayment(newCalculation);

			// casting around no math dependencies for a more readable reply, just cuts of numbers though
			double value1 = newCalculation.getMonthlyPayment();
			int value2 = (int) value1;
			Integer value3 = value2;
			String reply = value3.toString();

			return new ResponseEntity<>(reply, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}    

	/**
	 * Method to calculate a monthly payment for a loan
	 * @param calculation
	 */
	public void calculateMonthlyPayment(Calculation calculation) {

		// Given these instructions:

		// E = Fixed monthly payment
		// b = Interest on a monthly basis
		// U = Total loan
		// p = Number of payments

		// E = U[b(1 + b)^p]/[(1 + b)^p - 1];

		// Usage of java.math or similar math dependencies are not allowed
		

		Double b = (calculation.getInterest()/100)/12; // convert percent and change interest to monthly
		Double U = calculation.getTotalLoan();
		Integer p = calculation.getYears()*12; // paying monthly so years * 12
			
		// handle power of manually first
		Double uped = 1+b;
		for(int n=2; n<=p; n++) {
			uped = uped * (1+b);
		}
		// do the rest of the calculation
		Double E = U*(b*uped)/(uped-1);

		// set and save
		calculation.setMonthlyPayment(E);
		calculationRepository.save(calculation);
    }
}
