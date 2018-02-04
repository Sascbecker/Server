package de.htwsaar.server.service;

import de.htwsaar.server.dataclass.Member;
import de.htwsaar.server.dao.interfaces.*;


public class MemberServiceImpl {
	
	private MemberDao memberDao;

	public MemberServiceImpl() {}
	
	public void memberAnlegen(Member memberObj)
	{
		memberDao.memberAnlegen(memberObj);
	}
	
	public void memberEdit(Member memberObj)
	{
		
	}
	
	public boolean memberAuthenfication (Member memberObj)
	{	
		 Member member = new Member(); 
		 member = memberDao.getPassword(memberObj.getId());
		
		 
		 if(memberObj.getPassword() == member.getPassword())
		 {
			 return true;
		 }
		 else
			 return false;
		
	}
	
}
