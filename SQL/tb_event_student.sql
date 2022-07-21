USE IF4101_Proyecto_B87581_B85042
GO

CREATE TABLE tb_event_student
	(studentId VARCHAR(7) NOT NULL
	,eventId INT NOT NULL
	,FOREIGN KEY (studentId) REFERENCES tb_students (id)
	,FOREIGN KEY (eventId) REFERENCES tb_events (id_event))
