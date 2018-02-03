package de.htwsaar.server.dao.interfaces;

import java.util.List;
import de.htwsaar.server.dataclass.*;

public interface MessageDao {
	public void saveGroupMessage(Message messageObj);
	public void SaveSingleMessage(Message messageObj);
	public void readMessage();

}
