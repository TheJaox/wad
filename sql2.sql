CREATE DATABASE proyectoWad;

USE proyectoWad;

CREATE TABLE userType(idUserType INT NOT NULL AUTO_INCREMENT, 
	userTypeName VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(idUserType));

CREATE TABLE users(idUser INT NOT NULL AUTO_INCREMENT, 
	userName VARCHAR(50) UNIQUE NOT NULL, 
	userPass VARCHAR(50) NOT NULL, 
	idUserType INT NOT NULL,
	PRIMARY KEY(idUser),
	CONSTRAINT FK_idUserType FOREIGN KEY(idUserType) REFERENCES userType(idUserType));

CREATE TABLE multiType(idMultiType INT NOT NULL AUTO_INCREMENT,
	multiTypeName VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(idMultiType));

CREATE TABLE multimedia(idMultimedia INT NOT NULL AUTO_INCREMENT,
	multimediaName VARCHAR(50) UNIQUE NOT NULL,
	multiFile BLOB,
	multiText VARCHAR(50),
	idUser INT NOT NULL,
	PRIMARY KEY(idMultimedia),
	CONSTRAINT FK_idUser FOREIGN KEY(idUser) REFERENCES users(idUser));

CREATE TABLE excercises(idExcercise INT NOT NULL AUTO_INCREMENT,
	excerciseName VARCHAR(50) NOT NULL,
	idUser INT NOT NULL,
	PRIMARY KEY(idExcercise),
	CONSTRAINT FK_idUser FOREIGN KEY(idUser) REFERENCES users(idUser));

CREATE TABLE multiExcercise(idExcercise INT NOT NULL,
	idMultimedia INT NOT NULL,
	CONSTRAINT FK_idExcercise FOREIGN KEY(idExcercise) REFERENCES excercises(idExcercise)
	CONSTRAINT FK_idMultimedia FOREIGN KEY(idMultimedia) REFERENCES multimedia(idMultimedia));

CREATE TABLE result(idResult INT NOT NULL AUTO_INCREMENT,
	idExcercise INT NOT NULL,
	resultText VARCHAR(20) NOT NULL,
	PRIMARY KEY(idResult),
	CONSTRAINT FK_idExcercise FOREIGN KEY(idExcercise) REFERENCES excercises(idExcercise));

/* Catalogo userType */
INSERT INTO userType(userTypeName) VALUES('Administrador');
INSERT INTO userType(userTypeName) VALUES('Profesor');
INSERT INTO userType(userTypeName) VALUES('Alumno');

/* Catalogo multiType */
INSERT INTO multiType(multiTypeName) VALUES('text');
INSERT INTO multiType(multiTypeName) VALUES('image');
INSERT INTO multiType(multiTypeName) VALUES('audio');

/* Catalogo users */
INSERT INTO users(userName,userPass,idUserType) VALUES('ADMIN','1234',1);
INSERT INTO users(userName,userPass,idUserType) VALUES('PROFESOR','1234',2);
INSERT INTO users(userName,userPass,idUserType) VALUES('ALUMNO','1234',3);