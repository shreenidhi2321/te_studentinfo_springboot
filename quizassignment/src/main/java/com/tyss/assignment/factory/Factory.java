package com.tyss.assignment.factory;

import java.util.Date;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tyss.quizassignment.dao.LoginDetails;
import com.tyss.quizassignment.dto.QuizQuestions;
import com.tyss.quizassignment.dto.Register;
import com.tyss.quizassignment.dto.Results;

public class Factory {

	ApplicationContext context = new ClassPathXmlApplicationContext("quiz.xml");
	LoginDetails loginDetails = (LoginDetails) context.getBean("loginDetails");
	Scanner scanner = new Scanner(System.in);
	int result = 0;
	int noOfQuestions = 0;

	public void register() {
		Register register = (Register) context.getBean("register");
		System.out.println("Enter UserName");
		String username = scanner.next();
		register.setUsername(username);
		System.out.println("Enter Password");
		String password = scanner.next();
		register.setPassword(password);
		System.out.println("Enter ConfirmPassword");
		String confirmPassword = scanner.next();
		if (password.equals(confirmPassword)) {
			register.setConfirmpassword(confirmPassword);
			loginDetails.register(register);
			System.out.println("Sucessfully Registered");
		}
		else {
			System.out.println("Confirm password is not matched");
		}
		
	}// End of the Method

	public void login() {

		Register login = (Register) context.getBean("register");
		System.out.println("Enter UserName");
		String username = scanner.next();
		System.out.println("Enter Password");
		String password = scanner.next();
		Register login2 = loginDetails.login(username, password);
		String password1 = login2.getPassword();
		if (password.equals(password1)) {
			System.out.println("Successfully Login");
			int iterate2;
			do {
				System.out.println("ENTER 100 TO SELECT----> JAVA");
				System.out.println("ENTER 200 TO SELECT----> HTML");
				System.out.println("ENTER 300 TO SELECT----> SPRING");
				int subject = scanner.nextInt();
				subject(subject);
				questions(subject);
				results(subject);
				System.out.println("Enter 1 for to write one more Quiz");
				System.out.println("Enter 2 for Exit");
				iterate2 = scanner.nextInt();
			} while (iterate2 == 1);
		} else {
			System.out.println("wrong password");
		}
	}// End of the Method

	public void questions(int subject) {
		QuizQuestions questions1 = loginDetails.questions(subject);
		System.out.println("Please answer below questions by choosing the correct option....");
		
		System.out.println("----------------------QUESTION 1---------------------------");
		System.out.println(questions1.getQuestion2());
		String answer2 = scanner.next();
		if (questions1.getAnswer2().charAt(0)==answer2.charAt(0)) {
			result++;
		}
		System.out.println("----------------------QUESTION 2---------------------------");
		System.out.println(questions1.getQuestion3());
		String answer3 = scanner.next();
		if (questions1.getAnswer3().charAt(0)==answer3.charAt(0)) {
			result++;
		}
		System.out.println("----------------------QUESTION 3---------------------------");
		System.out.println(questions1.getQuestion4());
		String answer4 = scanner.next();
		if (questions1.getAnswer4().charAt(0)==answer4.charAt(0)) {
			result++;
		}
		System.out.println("----------------------QUESTION 4---------------------------");
		System.out.println(questions1.getQuestion5());
		String answer5 = scanner.next();
		if (questions1.getAnswer5().charAt(0)==answer5.charAt(0)) {
			result++;
		}
	}// End of the Method

	public void subject(int subject) {
		if (subject == 100) {
			QuizQuestions questions = (QuizQuestions) context.getBean("java");
		} else if (subject == 200) {
			QuizQuestions questions = (QuizQuestions) context.getBean("html");
		} else if (subject == 300) {
			QuizQuestions questions = (QuizQuestions) context.getBean("spring");
		}
	}// End of the Method

	public void results(int subject) {
		noOfQuestions =5;
		Results results = (Results) context.getBean("results");
		results.setId(subject);
		Date date = new Date();
		results.setTime_Date(date);
		results.setMarks(result);
		results.setPercentage((result * 100) / noOfQuestions);
		//results.setUsername(null);
		loginDetails.results(results);
		Results results2 = loginDetails.getResults(date);
		System.out.println("Quiz Completed");
		System.out.println("Total marks obtained: " + results2.getMarks());
		System.out.println("Percentage: " + results2.getPercentage() + "%");
		result = 0;

	}// End of the Method

}// End of the class
