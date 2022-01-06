/*

VERSION: SM_V2_3 : added booking Procedure
*/

/*
Procedure to Browse all shows(events)
Return All Events from EventInfo
*/
USE FinalProjectTheatre;
DELIMITER //

DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShows//
CREATE PROCEDURE GetShows()
	BEGIN
		SELECT EventID, Title
		FROM EventInfo;

	END; //

/*
Procedure to Browse all shows(events)
Return All Events from EventInfo where search Query contained withing Title
*/

DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShowsSearch//
CREATE PROCEDURE GetShowsSearch(IN SearchQ VARCHAR(50))
	BEGIN
		SELECT EventID, Title
		FROM EventInfo
        WHERE Title LIKE CONCAT('%',SearchQ,'%'); -- find all Shows containg the search query

	END; //


/*
Procedure to Browse all shows(events)
Return All Events from EventInfo where search Query matches date
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShowsDate//
CREATE PROCEDURE GetShowsDate(IN SearchQ DATE)
	BEGIN
		SELECT EventInfo.EventID, EventInfo.Title
		FROM EventInfo
        LEFT JOIN Performances
        ON EventInfo.EventID = Performances.EventID
        WHERE PerformanceStart LIKE CONCAT(SearchQ,'%'); -- Query must be in format YYYY-MM-DD STRING

	END; //
    
    
/*
Procedure to add a new line to bookings table for ticket purchases
Stalls BOOL True if seat in stalls, FALSE if seat in Circle (log price at time of purchase in case of refund/pricechange
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.SetBooking//
CREATE PROCEDURE SetBooking(IN PerfID INT, IN PurID INT, IN Conc BOOL, IN Stalls BOOL, OUT RetValue BOOL)
	BEGIN
		/*Subtract a seat from available seats*/ -- *? Check table that seats are available
		UPDATE Performances
        SET AvailabilityOfTickets = AvailabilityOfTickets - 1
        WHERE Performances.PerformanceID = PerfID;
        
        /*get the price of the ticket*/
         SET @Price = IF (Stalls, 
			((SELECT PricePenceStall FROM EventInfo -- Stall Seat
				WHERE EventInfo.EventID = 
					(SELECT EventID FROM Performances 
                    WHERE Performances.PerformanceID = PerfID))),
                    
			(SELECT PricePenceCircle FROM EventInfo -- Circle Seat
				WHERE EventInfo.EventID = 
					(SELECT EventID FROM Performances 
                    WHERE Performances.PerformanceID = PerfID)));
        
        /*Add the values to bookings table*/
		INSERT INTO Bookings(PerformanceID, PurchaserID, Concessionary,TicketPricePence)
        VALUES(PerfID, PurID, Conc, @Price);
        
        /*Return TRUE if Successful*/
	END; //