
TO RUN

	SQL
		1. RUN CreateDatabase.sql
		2. RUN CreateProcedures.sql
		3. RUN CreateProcedures_DataAnalytics.sql
		4. FOR TEST: RUN Test_PopulateTables.sql

	JAVA
		5. RUN TheatreProject


On initial Boot of JAVA, User will be presented with a welcome screen and set of instructions
	SEARCH OPTIONS
		show all shows
			displays a list of all shows
			
		search name
			user will be promted to type in all or part of a shows name
			a list of all shows containing the search term will be displayed
			
		search date
			user will be prompted to type in all or part of a date in format YYYY-MM-DD
			a list of all shows performed on the date search term will be displayed
		
		OTHER OPTIONS
			see order
				will call a previously made booking
				user must enter booking reference given when booking was made
			quit
				quits the application
				
				
	If user browses all shows, or searchs for specific show or date, they will be prompted if they wish to see more info on a specific show
	by entering more info, then the EventID of the show, details of the show will be displayed
	Show name, type, description and any musical accompanyments, prices and a list of performance date/times
	
	From here the user will be prompted if they wish to add tickets to a specific performance
	by entering add tickets followed by the PerformanceID
	
	A series of questions follows for amount of tickets and how many are concessionary
	Followed by the options to 
		See basket
			see what is currently in the basket
		
		main menu
			return to the main menu to search for more shows

		checkout
			go to checkout to purchase tickets in basket
			
	at the checkout the user will be asked the details pertaining to a booking, followed by a prompt for confirmation
	Upon successful checkout, user will be provided with a BookingID, which can be used to call the details of the booking in future