CREATE TABLE tb_Students
	(id VARCHAR(7) NOT NULL PRIMARY KEY
	,student_name VARCHAR(40) NOT NULL 
	,password VARCHAR(30) NOT NULL
	,recinto INT NOT NULL
	,FOREIGN KEY (recinto) REFERENCES tb_Recintos (ID_RECINTO))	