package de.htwsaar.server.service.interfaces;

import de.htwsaar.server.dataclass.*;
public interface MemberService {
	public void memberAnlegen(Member memberObj);
	public void memberEdit (Member memberObj);
	public void memberAuthentification (Member memberObj);

}
