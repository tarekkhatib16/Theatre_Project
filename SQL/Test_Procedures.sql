/*
Test Stored Procedures

VERSION: SM_V2_0
*/

/*
Test Return list of all Events from EventInfo
*/
CALL GetShows();

/*
Test Return list of all Events from EventInfo with Search term in Title
*/
CALL GetShowsSearch('a');

/*
Test Return list of all Events with search term dates
*/
CALL GetShowsDate('2022-06-01');