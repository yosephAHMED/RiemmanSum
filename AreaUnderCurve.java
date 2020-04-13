package edu.cuny.csi.csc330.lab4;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.*;
import org.apache.commons.math3.analysis.polynomials.*;

/**
 * @author yosephahmed
 * 
 * A Riemann sum is an approximation of the area under a curve
 * by dividing it into multiple simple shapes, like rectangles, 
 * and then taking the summation of all areas.
 * This code implements an algorithm for Riemann sum by midpoint.
 * 
 * The function implemented is x^2, but any other function would work.
 * It is said that as the number of rectangles increases, the approximation by Riemann sum
 * becomes much more accurate, such that by taking an infinte number of
 * rectangles, the approximation would become very similar to the definite integral.
 * 
 * Furthermore, from an interval of 0 to 8, the definite integral of x^2 is 170.66666...
 * The results of this code show that the Riemman sum is a good approximation as long as n is large.
 */

public class AreaUnderCurve {
	
	//PolynomialFunction to evaluate function at a given x-coordinate
	private PolynomialFunction myPolyFunc;
	
	//UnivariateFunction to offer more functionality like displaying the function and its degree
	private UnivariateFunction myUniFunc;
	
	
	AreaUnderCurve(double[] coefficients){
		this.myPolyFunc = new PolynomialFunction(coefficients);
		this.myUniFunc = (UnivariateFunction) new PolynomialFunction(coefficients);
	}
	
	
	//finds the area of each rectangle using midpoint and height then adds it up
	public double riemmansMidpoint(double startX, double endX, int numberOfRectangles) {
		double totalArea = 0.0;
		double rectWidth = (endX - startX) / numberOfRectangles;
		
		for (int i = 0; i < numberOfRectangles; i++) {
			double midPoint = startX + (rectWidth / 2.0) + i * rectWidth;
			
			//pass midPoint to function evaluator to find corresponding rectHeight
			double rectHeight = rectangleHeight(midPoint);
			
			//area of that rectangle -- [delta x * f(midpoint)]
			double area = rectWidth * rectHeight;
			
			//add area up
			totalArea+=area;
			
			//sum += (midPoint * midPoint - midPoint + 3);
		}
		
		return totalArea;
	}
	
	
	//evaluates function (retrieves corresponding y-value) at the midpoint
	public double rectangleHeight(double midPoint) {
		return myPolyFunc.value(midPoint);
	}
	
	//displays details about the function
	public void displayFunction() {
		System.out.printf("Function: %s\n", myUniFunc.toString());
		System.out.printf("Degree: %d\n", myPolyFunc.degree());
	}
	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 
		NumberFormat formatter = new DecimalFormat("#0.00");     
		// TODO Auto-generated method stub
	
		//coefficients for the function -- x^2
		double[] coefficients = new double [3];
		coefficients[0] = 0;
		coefficients[1] = 0;
		coefficients[2] = 1;
		
		//the interval goes from 0 to 8
		double startX = 0.0;
		double endX = 8.0;
		
		//creating an instance and passing coefficients to our constructor
		AreaUnderCurve proofRiemmans = new AreaUnderCurve(coefficients);
		
		//display our function and its degree
		proofRiemmans.displayFunction();
		System.out.printf("The interval is from %s to %s", formatter.format(startX), formatter.format(endX));
		
		//display definite integral result
		System.out.printf("\nThe definite integral is: %f\n\n", 170.666);
		
		//this loop will increase the number of rectangles and each time it finds area under curve
		for (int i = 1; i < 600; i*=8) {
			int numberRectangles = 4 + i;
			
			System.out.printf("For n = %d\nThe area under the curve is: ", numberRectangles);
			System.out.printf("%f\n", proofRiemmans.riemmansMidpoint(startX, endX, numberRectangles));
			
			
			System.out.print("Hit Enter to continue: \n");
			String dummy = scanner.nextLine(); 
			
		}
		
		System.out.println("\nExiting .. ");
		
		
	}
	

}
