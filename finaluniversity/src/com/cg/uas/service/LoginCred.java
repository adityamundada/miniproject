package com.cg.uas.service;

import com.cg.uas.dao.ApplicantDaoImp;
import com.cg.uas.dao.IApplicantDao;
import com.cg.uas.exception.UniversityException;

public class LoginCred {
IApplicantDao dao=new ApplicantDaoImp();

		// TODO Auto-generated constructor stub
		public String isValidCred(String loginId,String password) throws UniversityException{
	
			String role=null;
			role=dao.isValidCred(loginId, password);
			return role;
			
		
	}

}
