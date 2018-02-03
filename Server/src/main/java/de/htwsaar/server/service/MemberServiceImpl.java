package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.Member;
import de.htwsaar.server.dao.interfaces.*;


public class MemberServiceImpl {
	
	private MemberDao memberDao;

	public MemberServiceImpl() {}
	
	public void memberAnlegen(Member memberObj)
	{
		
	}
	
	public void memberEdit(Member memberObj)
	{
		
	}
	
	public boolean memberAuthenfication (Member memberObj)
	{	
		int dbId;
		String dbPassword;
		
		int memberId = memberObj.getId();
		String memberPassword = memberObj.getPassword();
		
		 Member member = new Member();
		 
		 member = memberDao.getPassword(memberId);
		 dbPassword = member.getPassword();
		 
		 if(memberPassword == dbPassword)
		 {
			 return true;
		 }
		 else
			 return false;
		
	}
	
}
