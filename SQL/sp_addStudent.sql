CREATE OR ALTER PROCEDURE sp_addStudent
	@param_username VARCHAR(40) = NULL
	,@param_password VARCHAR(30) = NULL
	,@param_carnet VARCHAR(7) = NULL
	,@param_recinto INT = NULL
AS
	BEGIN 
		IF EXISTS (SELECT TOP 1 1 FROM tb_Students WHERE id = @param_carnet)
			BEGIN 
				SELECT 'error' AS 'status', 'carnet' AS 'error';
			END 
		ELSE 
			BEGIN 
				IF EXISTS (SELECT TOP 1 1 FROM tb_Students WHERE student_name = @param_username)
					BEGIN 
						SELECT 'error' AS 'status', 'username' AS 'error';
					END 
				ELSE 
					BEGIN 
						INSERT INTO tb_Students
							(id
							,student_name
							,password
							,recinto)
						VALUES
							(@param_carnet
							,@param_username
							,@param_password
							,@param_recinto);
				
					END 
			END 
	END
