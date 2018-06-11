CREATE DATABASE proyectoWad;

USE proyectoWad;

CREATE TABLE userType(idUserType INT NOT NULL AUTO_INCREMENT, 
	userTypeName VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(idUserType));

CREATE TABLE grupo(idGrupo INT NOT NULL AUTO_INCREMENT,
	idProfesor INT UNIQUE NOT NULL,
	groupName VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(idGrupo));
	
CREATE TABLE users(idUser INT NOT NULL AUTO_INCREMENT, 
	userName VARCHAR(50) UNIQUE NOT NULL, 
	fullName VARCHAR(50) NOT NULL, 
	userPass VARCHAR(50) NOT NULL, 
	idUserType INT NOT NULL,
	idGrupo INT,
	PRIMARY KEY(idUser),
	CONSTRAINT FK_idUserType FOREIGN KEY(idUserType) REFERENCES userType(idUserType),
	CONSTRAINT FK_idUserGrupo FOREIGN KEY(idGrupo) REFERENCES grupo(idGrupo));

ALTER TABLE grupo ADD CONSTRAINT FK_idProfesor FOREIGN KEY(idProfesor) REFERENCES users(idUser);

CREATE TABLE excercises(idExcercise INT NOT NULL AUTO_INCREMENT,
	excerciseName VARCHAR(50) NOT NULL,
	idUser INT NOT NULL,
	PRIMARY KEY(idExcercise),
	CONSTRAINT FK_idUserExcercise FOREIGN KEY(idUser) REFERENCES users(idUser));

CREATE TABLE answer(idAnswer INT NOT NULL AUTO_INCREMENT,
	idExcercise INT NOT NULL,
	resultText VARCHAR(20) NOT NULL,
	PRIMARY KEY(idAnswer),
	CONSTRAINT FK_idExcerciseAnswer FOREIGN KEY(idExcercise) REFERENCES excercises(idExcercise));

CREATE TABLE multiType(idMultiType INT NOT NULL AUTO_INCREMENT,
	multiTypeName VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(idMultiType));

CREATE TABLE multimedia(idMultimedia INT NOT NULL AUTO_INCREMENT,
	multimediaName VARCHAR(50) UNIQUE NOT NULL,
	multiFile BLOB,
	multiText VARCHAR(50),
	idUser INT NOT NULL,
	PRIMARY KEY(idMultimedia),
	CONSTRAINT FK_idUserMultimedia FOREIGN KEY(idUser) REFERENCES users(idUser));

CREATE TABLE multiExcercise(idExcercise INT NOT NULL,
	idMultimedia INT NOT NULL,
	CONSTRAINT FK_idExcercise FOREIGN KEY(idExcercise) REFERENCES excercises(idExcercise),
	CONSTRAINT FK_idMultimedia FOREIGN KEY(idMultimedia) REFERENCES multimedia(idMultimedia));


/* Catalogo userType */
INSERT INTO userType(userTypeName) VALUES('Administrador');
INSERT INTO userType(userTypeName) VALUES('Profesor');
INSERT INTO userType(userTypeName) VALUES('Alumno');

/* Catalogo multiType */
INSERT INTO multiType(multiTypeName) VALUES('text');
INSERT INTO multiType(multiTypeName) VALUES('image');
INSERT INTO multiType(multiTypeName) VALUES('audio');

/* Catalogo users */
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('ADMIN','Administrador','1234',1);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('Ruben','Ruben Peredo Valderrama','1234',2);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('rubl3nd','Ruben Salcedo Baron','1234',3);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('rafita','Rafael Navarro Perez','1234',3);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('bryanAsco','Bryan Dominguez de la Rosa','1234',3);
