/*
VERSION: SM_V2_6 : added search procedure, return performaceID based on DATETIME
					added add purchaser to table procedure
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
		SELECT EventID, Title, EventType, PerformerInfo, Descrip
		FROM EventInfo;

	END; //

/*
Procedure to Browse all shows(events)
Return All Events from EventInfo where search Query contained withing Title
*/

DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShowsSearch//
CREATE PROCEDURE GetShowsSearch(IN SearchQ VARCHAR(50))
	BEGIN
		SELECT EventID, Title, EventType, PerformerInfo, Descrip
		FROM EventInfo
        WHERE Title LIKE CONCAT('%',SearchQ,'%'); -- find all Shows containg the search query

	END; //

/*
Procedure to return BOOL if show exists
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShowsSearchBool//
CREATE PROCEDURE GetShowsSearchBool(IN SearchQ VARCHAR(50), OUT Exist BOOLEAN)
	BEGIN
		IF EXISTS (SELECT Title
		FROM EventInfo
        WHERE Title LIKE CONCAT('%',SearchQ,'%')) -- find all Shows containg the search query
        THEN SET Exist = TRUE;
        ELSE SET Exist = FALSE;
        END IF;

	END; //

/*
Procedure to Browse all shows(events)
Return All Events from EventInfo where search Query matches date
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetShowsDate//
CREATE PROCEDURE GetShowsDate(IN SearchQ VARCHAR(10))
	BEGIN
		SELECT EventInfo.EventID, EventInfo.Title, Performances.PerformanceStart
		FROM EventInfo
        LEFT JOIN Performances
        ON EventInfo.EventID = Performances.EventID
        WHERE Performances.PerformanceStart LIKE CONCAT('%',SearchQ,'%'); -- Query must be in format YYYY-MM-DD STRING

	END; //
    
/*
Procedure to return An PerformanceID based on DateTime
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetPerformanceID_DT//
CREATE PROCEDURE GetPerformanceID_DT(IN SearchQ DATETIME)
    BEGIN
		SELECT Performances.PerformanceID
		FROM Performances
        WHERE Performances.PerformanceStart = SearchQ; -- Query must be in format YYYY-MM-DD HH:MM:SS STRING

	END; //

/*
Procedure to add a new line to bookings table for ticket purchases
Stalls BOOL True if seat in stalls, FALSE if seat in Circle (log price at time of purchase in case of refund/pricechange
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.SetBooking//
CREATE PROCEDURE SetBooking(IN PerfID INT, IN PurID INT, IN Conc BOOL, IN Stalls BOOL, IN SeatNumber INT)
	BEGIN
		/*Subtract a seat from available seats*/ -- *? Check table that seats are available
        UPDATE Performances
        SET Performances.AvailabilityOfStallsTickets = Performances.AvailabilityOfStallsTickets - 1 
        WHERE Stalls = True AND Performances.PerformanceID = PerfID;
        
        UPDATE Performances
        SET Performances.AvailabilityOfCircleTickets = Performances.AvailabilityOfCircleTickets - 1 
        WHERE Stalls = False AND Performances.PerformanceID = PerfID;
        
        /*get the price of the ticket*/
         SET @Price = IF (Stalls, 
			(SELECT PricePenceStall FROM EventInfo -- Stall Seat
				WHERE EventInfo.EventID = 
					(SELECT EventID FROM Performances 
                    WHERE Performances.PerformanceID = PerfID)),
                    
			(SELECT PricePenceCircle FROM EventInfo -- Circle Seat
				WHERE EventInfo.EventID = 
					(SELECT EventID FROM Performances 
                    WHERE Performances.PerformanceID = PerfID)));
        
        /*Add the values to bookings table*/
		INSERT INTO Bookings(PerformanceID, PurchaserID, Concessionary, SeatNumber, TicketPricePence)
        VALUES(PerfID, PurID, Conc, SeatNumber, @Price);

	END; //
    
/*
Procedure to return next available seat
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetNextSeat//
CREATE PROCEDURE GetNextSeat(IN PerfID INT, IN Stalls BOOL)
	BEGIN
		SELECT IF (Stalls=TRUE,
			(SELECT Performances.AvailabilityOfStallsTickets FROM Performances
				WHERE Performances.PerformanceID = PerfID),
			(SELECT Performances.AvailabilityOfCircleTickets FROM Performances
				WHERE Performances.PerformanceID = PerfID));
    END; //

/*
Procedure to add person to purchaser table
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.InsertPurchaser//
CREATE PROCEDURE InsertPurchaser(
IN PurchaserName VARCHAR(100),
IN DoB DATE, -- Format 'YYYY-MM-DD' AS STRING
IN AddressHouseNumber VARCHAR(50), -- VARCHAR(50) to cover instances where house has name, flat B, 21A etc
IN AddressStreet VARCHAR(60), -- 
IN AddressCity VARCHAR (60), -- 
IN AddressCounty VARCHAR(60), --
IN AddressPostcode VARCHAR(7), -- Postcodes between 5 and 7 AlphaNumeric, remove spaces before putting into column
IN CreditCard VARCHAR(19) -- remove all spaces, credit card either 16 or 19 digit long
)
	BEGIN
		INSERT INTO Purchasers(PurchaserName, DoB, AddressHouseNumber, AddressStreet, AddressCity,AddressCounty,AddressPostcode, CreditCard)
		VALUES(PurchaserName, DoB, AddressHouseNumber, AddressStreet, AddressCity,AddressCounty,AddressPostcode, CreditCard);
        
        SELECT LAST_INSERT_ID() FROM Purchasers LIMIT 1;
	END; //

/*
Procedure to return booking info given a booking reference.
*/    
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetPurchase//
CREATE PROCEDURE GetPurchase(IN PurID INT)
	BEGIN
		SELECT Purchasers.PurchaserName, Purchasers.DOB, Purchasers.AddressHouseNumber, Purchasers.AddressStreet, Purchasers.AddressCity, Purchasers.AddressCounty, Purchasers.AddressPostCode
        FROM Purchasers
        WHERE Purchasers.PurchaserID = PurID;
	END; //

/*
Procedure to return info on specific event
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetEventInfo//
CREATE PROCEDURE GetEventInfo(IN EvtID INT)
	BEGIN
		SELECT EventID, Title, EventType, PerformerInfo, Descrip, Lang, CONCAT(EventDurationMM,'mins'), CONCAT('Stall: £', ROUND(PricePenceStall/100,2)), CONCAT('Circle: £', ROUND(PricePenceCircle/100, 2))
		FROM EventInfo
        WHERE EventInfo.EventID = EvtID; -- find all Shows containg the search query
	END; //
    
/*
Procedure to return info on specific performance
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetPerfInfo//
CREATE PROCEDURE GetPerfInfo(IN PerfID INT)
	BEGIN
		SELECT Performances.PerformanceStart
		FROM Performances
		WHERE Performances.PerformanceID = PerfID;
	END; //
    
/*
Procedure to return info on specific performance
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetPerfPrice//
CREATE PROCEDURE GetPerfPrice(IN EventID INT, IN Stalls BOOL)
	BEGIN
		SELECT IF (Stalls=TRUE,
			(SELECT EventInfo.PricePenceStall FROM EventInfo
				WHERE EventInfo.EventID = EventID),
			(SELECT EventInfo.PricePenceCircle FROM EventInfo
				WHERE EventInfo.EventID = EventID));
	END; //

DROP PROCEDURE IF EXISTS finalprojecttheatre.GetEventIDFromPerf//
CREATE PROCEDURE GetEventIDFromPerf(IN PerfID INT)
	BEGIN
		SELECT EventInfo.Title
        FROM EventInfo
        WHERE EventInfo.EventID IN (
			SELECT Performances.EventID
            FROM Performances
            WHERE Performances.PerformanceID = PerfID
		);
	END; //
    
/*
Procedure to get all performances of a specific eventID
*/
DROP PROCEDURE IF EXISTS finalprojecttheatre.GetPerformances//
CREATE PROCEDURE GetPerformances(IN EventID INT)
	BEGIN
		SELECT PerformanceID, PerformanceStart, AvailabilityOfStallsTickets, AvailabilityOfCircleTickets
		FROM Performances
        WHERE Performances.EventID = EventID; -- find all Shows containg the search query

	END; //