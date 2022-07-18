CREATE OR ALTER PROCEDURE sp_addStudent
	@param_username VARCHAR(40) = NULL
	,@param_password VARCHAR(30) = NULL
AS
	BEGIN 
		IF EXISTS (SELECT TOP 1 1 FROM tb_Students WHERE NAME_STUDENT = @param_username)
			BEGIN 
				SELECT 'error' AS 'status', 'username' AS 'error';
			END 
		ELSE 
			BEGIN 
				INSERT INTO tb_Students
					(NAME_STUDENT
					,PASSWORD)
				VALUES
					(@param_username
					,@param_password);
			END 
	END
