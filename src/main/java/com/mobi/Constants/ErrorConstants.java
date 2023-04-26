package com.mobi.Constants;

public final class ErrorConstants {
	
	
	public static final String NOT_INSTANTIABLE = "This class cannot be instantiated";
	
	// TEST SERVICE
	
	public static final String TEST_NOT_EXIST = "Test Does Not Exist";
	
	public static final String TEST_NOT_PUBLISHED = "Test is not published";
	
	public static final String TEST_DELETED = "Test deleted successfully";
	
	public static final String TEST_DELETE_FAILURE = "Test already assigned. Please, de-allocate first before deleting";
	
	public static final String TEST_UPDATED = "Test Updated Successfully";
	
	public static final String TEST_NOT_UPDATED = "Connot update test";
	
	public static final String TEST_ALREADY_ASSIGNED = "Test already assigned";
	
	// USER SERVICE
	
	public static final String USER_ALREADY_EXIST = "Username already exist";
	
	public static final String USER_NOT_FOUND = "User Not Found";
	
	public static final String USER_DELETED = "User Deleted Successfully";
	
	// QUESTION SERVICE
	
	public static final String QUESTION_ALREADY_EXIST = "Question already exist";
	
	public static final String QUESTION_NOT_EXIST = "Question does not exist";
	
	public static final String QUESTION_DELETED = "Question Deleted Successfully";
	
	
	private ErrorConstants() {
		throw new AssertionError(NOT_INSTANTIABLE);
	}

}
