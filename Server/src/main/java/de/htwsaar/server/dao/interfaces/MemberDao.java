package de.htwsaar.server.dao.interfaces;

import de.htwsaar.server.dataclass.Member;

public interface MemberDao {
	public Member getPassword(int memberId);
	public void memberAnlegen(Member member);

}
