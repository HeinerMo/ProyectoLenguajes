CREATE OR ALTER PROCEDURE sp_AddComment
	@param_comment VARCHAR(300) = NULL
	,@param_artistId INT = NULL
	,@param_studentId VARCHAR(7) = NULL
AS
	BEGIN
		SET NOCOUNT ON;
		IF @param_comment IS NOT NULL AND @param_artistId IS NOT NULL AND @param_studentId IS NOT NULL
			BEGIN
				INSERT INTO tb_Comment_Artist_Student
					(ID_ARTIST
					,ID_STUDENT
					,COMMENT)
				VALUES
					(@param_artistId, @param_studentId, @param_comment)
				SELECT 'success' AS 'status';
			END
		ELSE
			BEGIN
				SELECT 'error' AS 'status'
			END

		RETURN;
	END