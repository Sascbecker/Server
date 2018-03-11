CREATE TABLE User (
	UserID char NOT NULL,
	Passwort char NOT NULL,
	IPAdresse char,
	PRIMARY KEY(UserID) )
	
CREATE TABLE Gruppen (
	GruppenID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	GruppenName char NOT NULL, 
	GruppenAdmin char NOT NULL, 
	FOREIGN KEY (GruppenAdmin) REFERENCES USER(UserID) )
	
CREATE TABLE IstGruppe( 
GruppenID int NOT NULL, 
UserID char NOT NULL, 
Foreign KEY (GruppenID) REFERENCES Gruppen(GruppenID) ON DELETE cascade,
 Foreign KEY (UserId) REFERENCES USER(UserID) )
	
	
CREATE TABLE Nachrichten (
	MessageID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
	Zeit int NOT NULL, 
	Inhalt char NOT NULL, 
	SenderID char NOT NULL, 
	EmpfaengerID char NOT NULL,
	Zugestellt boolean NULL,
	FOREIGN KEY(SenderID) REFERENCES User (USERID), 
	FOREIGN KEY(EmpfaengerID) REFERENCES USER (USERID) )
	
	
CREATE TABLE Kontakte ( 
	KontaktUID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	UserID char NOT NULL,
	KontaktID char,
	Blockieren BOOLEAN,
	FOREIGN KEY(UserID) REFERENCES User(UserID),
	FOREIGN KEY(KontaktID) REFERENCES User(UserID) )