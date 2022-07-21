CREATE PROCEDURE sp_signOffEvent
	@param_studentId VARCHAR(7) = NULL
	,@param_eventId INT = NULL
AS
	BEGIN
		DELETE FROM tb_event_student
		WHERE studentId = @param_studentId AND eventId = @param_eventId;
	END