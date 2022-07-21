CREATE OR ALTER PROCEDURE sp_signToEvent
	@param_carnet VARCHAR(7) = NULL
	,@param_event INT = NULL
AS
	BEGIN

		SET NOCOUNT ON
		Declare @local_message VARCHAR(MAX) = NULL;
		DECLARE @local_quantity INT = 0;
		--check if studenId is correct
		IF EXISTS(SELECT TOP 1 1 FROM tb_Students WHERE id = @param_carnet)
			BEGIN
				--Check if eventId is correct
				IF EXISTS(SELECT TOP 1 1 FROM tb_Events WHERE ID_EVENT = @param_event)
					BEGIN
						--Check if entry already exist
						IF EXISTS(SELECT TOP 1 1 FROM tb_event_student WHERE eventId =  @param_event AND studentId = @param_carnet)
							BEGIN
								SET @local_message = 'reduntant';
							END
						ELSE
							BEGIN
								SET @local_quantity = (SELECT
															e.TAQUILLA - COUNT(se.studentId)
														FROM tb_Events AS e
															FULL OUTER JOIN tb_event_student AS se
																ON e.ID_EVENT = se.eventId
																	WHERE e.ID_EVENT = @param_event
														GROUP BY e.ID_EVENT, e.TAQUILLA)

								IF @local_quantity > 0
									BEGIN
										INSERT INTO tb_event_student
											(studentId
											,eventId)
										VALUES
											(@param_carnet, @param_event);
								
										SET @local_message = 'success';
									END
								ELSE
									BEGIN 
										SET @local_message = 'places';
									END
							END
						
					END
				ELSE
					BEGIN
						SET @local_message = 'event';
					END
			END
		ELSE
			BEGIN
				SET @local_message = 'carnet';
			END

		SELECT @local_message AS 'status';
		RETURN; --necessary for the procedure to return data.
	END




