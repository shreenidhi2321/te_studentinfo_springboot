package com.tyss.quizassignment.dao;

import java.util.Date;

import com.tyss.quizassignment.dto.QuizQuestions;
import com.tyss.quizassignment.dto.Register;
import com.tyss.quizassignment.dto.Results;

interface Quiz {
	
	abstract  Register login(String username,String Password);
	
	abstract  Register register(Register register);
	
	abstract QuizQuestions questions(int id);
	
	abstract void results(Results results);
	
	abstract Results getResults(Date date);

}
