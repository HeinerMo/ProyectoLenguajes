CREATE OR ALTER PROCEDURE sp_studentLogin
	@param_username VARCHAR(40), @para_password VARCHAR(30)
AS
	BEGIN
		IF EXISTS(SELECT TOP 1 1 FROM tb_Students WHERE NAME_STUDENT = @param_username)
			BEGIN
				IF EXISTS(SELECT TOP 1 1 FROM tb_Students WHERE PASSWORD = @para_password)
					BEGIN
						SELECT 'success' AS 'status';
					END
				ELSE
					BEGIN
						SELECT 'password' AS 'status';
					END
			END
		ELSE
			BEGIN
				SELECT 'username' AS 'status';
			END
	END
GO