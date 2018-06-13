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

CREATE TABLE excerciseType(idExcerciseType INT NOT NULL AUTO_INCREMENT,
	excerciseTypeName VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(idExcerciseType));

CREATE TABLE excercises(idExcercise INT NOT NULL AUTO_INCREMENT,
	excerciseName VARCHAR(50) NOT NULL,
	instruction VARCHAR(200) NOT NULL,
	excerciseText VARCHAR(500),
	idUser INT NOT NULL,
	idGrupo INT NOT NULL,
	idExcerciseType INT NOT NULL,
	PRIMARY KEY(idExcercise),
	CONSTRAINT FK_idUserExcercise FOREIGN KEY(idUser) REFERENCES users(idUser),
	CONSTRAINT FK_idExcerciseGrupo FOREIGN KEY(idGrupo) REFERENCES grupo(idGrupo),
	CONSTRAINT FK_idExcerciseType FOREIGN KEY(idExcerciseType) REFERENCES excerciseType(idExcerciseType));

CREATE TABLE options(idOption INT NOT NULL AUTO_INCREMENT,
	idExcercise INT NOT NULL,
	stCorrect INT NOT NULL,
	optionText VARCHAR(50) NOT NULL,
	PRIMARY KEY(idOption),
	CONSTRAINT FK_idOptionExcercise FOREIGN KEY(idExcercise) REFERENCES excercises(idExcercise));

CREATE TABLE answer(idAnswer INT NOT NULL AUTO_INCREMENT,
	idExcercise INT NOT NULL,
	resultText VARCHAR(20) NOT NULL,
	PRIMARY KEY(idAnswer),
	CONSTRAINT FK_idExcerciseAnswer FOREIGN KEY(idExcercise) REFERENCES excercises(idExcercise));

CREATE TABLE multiType(idMultiType INT NOT NULL AUTO_INCREMENT,
	multiTypeName VARCHAR(50) UNIQUE NOT NULL,
	PRIMARY KEY(idMultiType));

CREATE TABLE multimedia(idMultimedia INT NOT NULL AUTO_INCREMENT,
	idMultiType INT NOT NULL,
	multimediaName VARCHAR(50) UNIQUE NOT NULL,
	multiFile BLOB,
	multiText VARCHAR(50),
	idUser INT NOT NULL,
	PRIMARY KEY(idMultimedia),
	CONSTRAINT FK_idUserMultimedia FOREIGN KEY(idUser) REFERENCES users(idUser),
	CONSTRAINT FK_idTypeMultimedia FOREIGN KEY(idMultiType) REFERENCES multiType(idMultiType));

CREATE TABLE multiExcercise(idExcercise INT NOT NULL,
	idMultimedia INT NOT NULL,
	CONSTRAINT FK_idExcercise FOREIGN KEY(idExcercise) REFERENCES excercises(idExcercise),
	CONSTRAINT FK_idMultimedia FOREIGN KEY(idMultimedia) REFERENCES multimedia(idMultimedia));


/* Catalogo userType */
INSERT INTO userType(userTypeName) VALUES('Administrador');
INSERT INTO userType(userTypeName) VALUES('Profesor');
INSERT INTO userType(userTypeName) VALUES('Alumno');

/* Catalogo excerciseType */
INSERT INTO excerciseType(excerciseTypeName) VALUES('Complete sentences');
INSERT INTO excerciseType(excerciseTypeName) VALUES('Select verb');
INSERT INTO excerciseType(excerciseTypeName) VALUES('Select option');

/* Catalogo multiType */
INSERT INTO multiType(multiTypeName) VALUES('image');
INSERT INTO multiType(multiTypeName) VALUES('audio');

/* Catalogo users */
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('ADMIN','Administrador','1234',1);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('Ruben','Ruben Peredo Valderrama','1234',2);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('Jair','Jair Pacheco Diaz','1234',2);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('rubl3nd','Ruben Salcedo Baron','1234',3);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('rafita','Rafael Navarro Perez','1234',3);
INSERT INTO users(userName,fullName,userPass,idUserType) VALUES('bryanAsco','Bryan Dominguez de la Rosa','1234',3);
