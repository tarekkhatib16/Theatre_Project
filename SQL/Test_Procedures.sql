/*
Test Stored Procedures

VERSION: SM_V2_4: added test for bool search show
				
*/

/*
Test Return list of all Events from EventInfo
*/
-- CALL GetShows();

/*
Test Return list of all Events from EventInfo with Search term in Title
*/
-- CALL GetShowsSearch('a');

/*
Test Return BOOL of all Events from EventInfo with Search term in Title
*/
-- CALL GetShowsSearchBool('a', @exist);
-- SELECT @Exist;

/*
Test Return list of all Events with search term dates
*/
-- CALL GetShowsDate('2022-06-01');

/*
Test Book a ticket to event, should add ticket to Bookings, Reduce available seats from performance, log ticketprice and concession
*/
-- CALL SetBooking(1, 1, FALSE, TRUE, @Ret); -- Stall Seat
-- CALL SetBooking(1, 1, FALSE, FALSE, @Ret); -- Circle Seat

-- SELECT * FROM Bookings;
-- SELECT * FROM Performances WHERE Performances.PerformanceID = 1;