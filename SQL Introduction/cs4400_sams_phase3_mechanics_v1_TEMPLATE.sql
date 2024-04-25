-- CS4400: Introduction to Database Systems: Tuesday, September 12, 2023
-- Simple Airline Management System Course Project Mechanics [TEMPLATE] (v0)
-- Views, Functions & Stored Procedures

/* This is a standard preamble for most of our scripts.  The intent is to establish
a consistent environment for the database behavior. */
set global transaction isolation level serializable;
set global SQL_MODE = 'ANSI,TRADITIONAL';
set names utf8mb4;
set SQL_SAFE_UPDATES = 0;

set @thisDatabase = 'flight_tracking';
use flight_tracking;
-- -----------------------------------------------------------------------------
-- stored procedures and views
-- -----------------------------------------------------------------------------
/* Standard Procedure: If one or more of the necessary conditions for a procedure to
be executed is false, then simply have the procedure halt execution without changing
the database state. Do NOT display any error messages, etc. */

-- [_] supporting functions, views and stored procedures
-- -----------------------------------------------------------------------------
/* Helpful library capabilities to simplify the implementation of the required
views and procedures. */
-- -----------------------------------------------------------------------------
drop function if exists leg_time;
delimiter //
create function leg_time (ip_distance integer, ip_speed integer)
	returns time reads sql data
begin
	declare total_time decimal(10,2);
    declare hours, minutes integer default 0;
    set total_time = ip_distance / ip_speed;
    set hours = truncate(total_time, 0);
    set minutes = truncate((total_time - hours) * 60, 0);
    return maketime(hours, minutes, 0);
end //
delimiter ;

-- [1] add_airplane()
-- -----------------------------------------------------------------------------
/* This stored procedure creates a new airplane.  A new airplane must be sponsored
by an existing airline, and must have a unique tail number for that airline.
username.  An airplane must also have a non-zero seat capacity and speed. An airplane
might also have other factors depending on it's type, like skids or some number
of engines.  Finally, an airplane must have a new and database-wide unique location
since it will be used to carry passengers. */
-- -----------------------------------------------------------------------------
drop procedure if exists add_airplane;
delimiter //
create procedure add_airplane 
(in ip_airlineID varchar(50), 
in ip_tail_num varchar(50),
in ip_seat_capacity integer, 
in ip_speed integer, 
in ip_locationID varchar(50),
in ip_plane_type varchar(100), 
in ip_skids boolean, 
in ip_propellers integer,
in ip_jet_engines integer)
sp_main: begin
    declare airline_exists int;
    declare tail_num_exists int;
    declare location_exists int;
    
    select count(*) into airline_exists
    from airline
    where airlineID = ip_airlineID;
    
    if airline_exists = 0 then
        leave sp_main;
    end if;

    select count(*) into tail_num_exists
    from airplane
    where airlineID = ip_airlineID and tail_num = ip_tail_num;

    if tail_num_exists > 0 then
        leave sp_main;
    end if;

    select count(*) into location_exists
    from airplane
    where locationID = ip_locationID;

    if location_exists > 0 then
        leave sp_main;
    end if;
	insert into location (locationID)
    Select ip_locationID 
    where not exists (select 1 from location where locationID = ip_locationID);
    insert into airplane (airlineID, tail_num, seat_capacity, speed, locationID, plane_type, skids, propellers, jet_engines)
    values (ip_airlineID, ip_tail_num, ip_seat_capacity, ip_speed, ip_locationID, ip_plane_type, ip_skids, ip_propellers, ip_jet_engines);

end sp_main//
delimiter ;


-- [2] add_airport()
-- -----------------------------------------------------------------------------
/* This stored procedure creates a new airport.  A new airport must have a unique
identifier along with a new and database-wide unique location if it will be used
to support airplane takeoffs and landings.  An airport may have a longer, more
descriptive name.  An airport must also have a city, state, and country designation. */
-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------
-- This stored procedure adds a new airport to the database if it does not already exist.
-- An airport is identified by a unique airportID or a combination of city, state, and country.
-- -----------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS add_airport;
DELIMITER //
CREATE PROCEDURE add_airport (
  IN ip_airportID CHAR(3),
  IN ip_airport_name VARCHAR(200),
  IN ip_city VARCHAR(100),
  IN ip_state VARCHAR(100),
  IN ip_country CHAR(3),
  IN ip_locationID VARCHAR(50)
)
sp_main: BEGIN
IF (SELECT COUNT(*) FROM airport WHERE airportID = ip_airportID) > 0 THEN
    LEAVE sp_main;
END IF;

IF (SELECT COUNT(*) FROM location WHERE locationID = ip_locationID) = 0 THEN
    INSERT INTO location VALUES (ip_locationID);
    INSERT INTO airport (airportID, airport_name, city, state, country, locationID)
	VALUES (ip_airportID, ip_airport_name, ip_city, ip_state, ip_country, ip_locationID);
ELSE 
	LEAVE sp_main;
END IF;
END //
DELIMITER ;






-- [3] add_person()
-- -----------------------------------------------------------------------------
/* This stored procedure creates a new person.  A new person must reference a unique
identifier along with a database-wide unique location used to determine where the
person is currently located: either at an airport, or on an airplane, at any given
time.  A person must have a first name, and might also have a last name.

A person can hold a pilot role or a passenger role (exclusively).  As a pilot,
a person must have a tax identifier to receive pay, and an experience level.  As a
passenger, a person will have some amount of frequent flyer miles, along with a
certain amount of funds needed to purchase tickets for flights. */
-- -----------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS add_person;
DELIMITER //
CREATE PROCEDURE add_person (
    IN ip_personID VARCHAR(50),
    IN ip_first_name VARCHAR(100),
    IN ip_last_name VARCHAR(100),
    IN ip_locationID VARCHAR(50),
    IN ip_taxID VARCHAR(50),
    IN ip_experience INT,
    IN ip_miles INT,
    IN ip_funds INT
)
sp_main: BEGIN
    DECLARE duplicateCount INT;
    -- Check if person with the same personID already exists
    SELECT COUNT(*) INTO duplicateCount FROM person WHERE personID = ip_personID;
    
    IF duplicateCount > 0 THEN
        LEAVE sp_main;
    END IF;

    -- Insert into person table
    INSERT INTO person (personID, first_name, last_name, locationID)
    VALUES (ip_personID, ip_first_name, ip_last_name, ip_locationID);

    -- Insert into pilot table if taxID is not null
    IF ip_taxID IS NOT NULL THEN
        INSERT INTO pilot (personID, taxID, experience)
        VALUES (ip_personID, ip_taxID, ip_experience);
    END IF;

    -- Insert into passenger table if miles and funds are not null
    IF ip_miles IS NOT NULL AND ip_funds IS NOT NULL THEN
        INSERT INTO passenger (personID, miles, funds)
        VALUES (ip_personID, ip_miles, ip_funds);
    END IF;
    
    -- Add additional error handling if needed for insert statements
    
END //
DELIMITER ;


-- [4] grant_or_revoke_pilot_license()
-- -----------------------------------------------------------------------------
/* This stored procedure inverts the status of a pilot license.  If the license
doesn't exist, it must be created; and, if it laready exists, then it must be removed. */
-- -----------------------------------------------------------------------------
drop procedure if exists grant_or_revoke_pilot_license;
delimiter //
create procedure grant_or_revoke_pilot_license (in ip_personID varchar(50), in
ip_license varchar(100))
sp_main: begin
declare license_exists int;

select count(*) into license_exists
from pilot_licenses
where personID = ip_personID and license = ip_license;

if license_exists > 0 then

	delete from pilot_licenses
	where personID = ip_personID and license = ip_license;
else 
	insert into pilot_licenses (personID, license)
        values (ip_personID, ip_license);
end if;

end //
delimiter ;


-- [5] offer_flight()
-- -----------------------------------------------------------------------------
/* This stored procedure creates a new flight.  The flight can be defined before
an airplane has been assigned for support, but it must have a valid route.  And
the airplane, if designated, must not be in use by another flight.  The flight
can be started at any valid location along the route except for the final stop,
and it will begin on the ground.  You must also include when the flight will
takeoff along with its cost. */
-- -----------------------------------------------------------------------------
drop procedure if exists offer_flight;
delimiter //
create procedure offer_flight (in ip_flightID varchar(50), in ip_routeID varchar(50),
    in ip_support_airline varchar(50), in ip_support_tail varchar(50), in ip_progress integer, in ip_next_time time, in ip_cost integer)
sp_main: begin
	if (select COUNT(*) from flight where flightID = ip_flightID) > 0 then
        signal SQLSTATE '45000'
            set message_text = 'Flight with the same flightID already exists.';
    end if;
    if (select COUNT(*) from airplane where airlineID = ip_support_airline and tail_num = ip_support_tail) = 0 then
        signal SQLSTATE '45000'
            set message_text = 'Airplane with the given airlineID and tail_num does not exist.';
    end if;
    if (select COUNT(*) from flight where support_airline = ip_support_airline and support_tail = ip_support_tail and airplane_status <> 'on_ground') > 0 then
        signal SQLSTATE '45000'
            set message_text = 'Airplane is already assigned to another flight.';
    end if;
    if (select COUNT(*) from route where routeID = ip_routeID) = 0 then
		signal SQLSTATE '45000'
            set message_text = 'Route with the given routeID does not exist.';
    end if;
	if ip_progress > (select COUNT(*) from route_path where routeID = ip_routeID) then
		signal SQLSTATE '45000'
			set message_text = 'Flight progress is greater than the number of legs on the route.';
    end if;
    
    insert into flight (flightID, routeID, support_airline, support_tail, progress, airplane_status, next_time, cost)
    values (ip_flightID, ip_routeID, ip_support_airline, ip_support_tail, ip_progress, 'on_ground', ip_next_time, ip_cost);
end //
delimiter ;

-- [6] flight_landing()
-- -----------------------------------------------------------------------------
/* This stored procedure updates the state for a flight landing at the next airport
along it's route.  The time for the flight should be moved one hour into the future
to allow for the flight to be checked, refueled, restocked, etc. for the next leg
of travel.  Also, the pilots of the flight should receive increased experience, and
the passengers should have their frequent flyer miles updated. */
-- -----------------------------------------------------------------------------
drop procedure if exists flight_landing;
delimiter //
create procedure flight_landing (in ip_flightID varchar(50))
sp_main: begin
	if (select airplane_status from flight where flightID = ip_flightID) = 'on_ground'
		then leave sp_main; end if;

	update flight
    set next_time = next_time + INTERVAL 1 HOUR, airplane_status = 'on_ground'
    where ip_flightID = flightID;
    
	update pilot
    set experience = experience + 1
    where commanding_flight = ip_flightID;
    
	set @addmiles = (select distance
    from leg
    natural join route_path
    join flight on flight.routeID = route_path.routeID and progress = sequence
    where flightID = ip_flightID);
    
    update passenger
    set miles = miles + @addmiles
    where personID in 
    (select personID from person natural join airplane
    join flight on support_airline = airlineID and support_tail = tail_num
    where flightID = ip_flightID);
end //
delimiter ;

-- [7] flight_takeoff()
-- -----------------------------------------------------------------------------
/* This stored procedure updates the state for a flight taking off from its current
airport towards the next airport along it's route.  The time for the next leg of
the flight must be calculated based on the distance and the speed of the airplane.
And we must also ensure that propeller driven planes have at least one pilot
assigned, while jets must have a minimum of two pilots. If the flight cannot take
off because of a pilot shortage, then the flight must be delayed for 30 minutes. */
-- -----------------------------------------------------------------------------
drop procedure if exists flight_takeoff;
delimiter //
create procedure flight_takeoff (in ip_flightID varchar(50))
sp_main: begin
	update flight set 
		next_time = addTime(next_time, '00:30:00')
        where 
			ip_flightID = flightID 
            and exists 
				(select * from route_path 
					where ip_flightID = flight.flightID
                    and flight.routeID = route_path.routeID 
					and route_path.sequence = flight.progress + 1)
			and not exists
				(select flightID, plane_type, count(*) as pilots from (select * from flight) as newflight 
				  join airplane on tail_num = support_tail
				  join pilot on commanding_flight = newflight.flightID
				  group by newflight.flightID, airplane.plane_type
				  having ip_flightID = newflight.flightID and 
						 ((plane_type = 'jet' and pilots >= 2) or (plane_type = 'prop' and pilots >= 1))
				)
			and airplane_status = 'on_ground';
            
    update flight set
 		next_time = addTime(next_time, leg_time((select distance from route_path
  								 natural join leg
  								 where (flight.routeID, flight.progress + 1) = (route_path.routeID, route_path.sequence)),
  								(select speed from airplane 
                                   where ip_flightID = flight.flightID
                                   and tail_num = support_tail)
  							)),
		progress = progress + 1,
		airplane_status = 'in_flight'
		where 
			ip_flightID = flightID 
            and exists 
				(select * from route_path 
					where ip_flightID = flight.flightID
                    and flight.routeID = route_path.routeID 
					and route_path.sequence = flight.progress + 1)
            and exists
				(select flightID, plane_type, count(*) as pilots from (select * from flight) as newflight 
				  join airplane on tail_num = support_tail
				  join pilot on commanding_flight = newflight.flightID
				  group by newflight.flightID, airplane.plane_type
				  having ip_flightID = newflight.flightID and 
						 ((plane_type = 'jet' and pilots >= 2) or (plane_type = 'prop' and pilots >= 1))
				)
			and airplane_status = 'on_ground';
end //
delimiter ;

-- [8] passengers_board()
-- -----------------------------------------------------------------------------
/* This stored procedure updates the state for passengers getting on a flight at
its current airport.  The passengers must be at the same airport as the flight,
and the flight must be heading towards that passenger's desired destination.
Also, each passenger must have enough funds to cover the flight.  Finally, there
must be enough seats to accommodate all boarding passengers. */
-- -----------------------------------------------------------------------------
drop procedure if exists passengers_board;
delimiter //
create procedure passengers_board (in ip_flightID varchar(50))
sp_main: begin
	if (select airplane_status from flight where flightID = ip_flightID) = 'in_flight'
		then leave sp_main; end if;
        
	set @progress = (select progress from flight where flightID = ip_flightID);
    set @route = (select routeID from flight where flightID = ip_flightID);
	if @progress = (select max(sequence) from route_path where @route = routeID group by routeID)
		then leave sp_main; end if;
        
	set @tailNum = (select support_tail from flight where flightID = ip_flightID);
	set @airline = (select support_airline from flight where flightID = ip_flightID);
    set @nextleg = (select legID from route_path where sequence = @progress + 1 and routeID = @route);
    set @depart = (select departure from leg where legID = @leg);
    set @arrive = (select arrival from leg where legID = @leg);
    set @airportloc = (select locationID from airport where airportID = @depart);
    set @planeloc = (select locationID from airplane where airlineID = @airline and tail_num = @tailNum);
    set @cost = (select cost from flight where flightID = ip_flightID);
    set @seatCap = (select seat_capacity from airplane where airlineID = @airline and tail_num = @tailNum);
    
    set @peopletoboard = (select personID from passenger_vacations
    natural join person
    natural join passenger
    where sequence = 1 and airportID = @arrive and person.locationID = @airportloc and funds >= @cost);
    
	set @numpeopletoboard = (select count(*) from passenger_vacations
    natural join person
    natural join passenger
    where sequence = 1 and airportID = @arrive and person.locationID = @airportloc and funds >= @cost);

	if (@numpeopletoboard > @seatCap)
		then leave sp_main; end if;
    
    update person set locationID = @planeloc 
    where find_in_set(personID, @peopletoboard) <> 0;
    
	update passenger set funds = funds - @cost
    where find_in_set(personID, @peopletoboard) <> 0;
end //
delimiter ;


-- [9] passengers_disembark()
-- -----------------------------------------------------------------------------
/* This stored procedure updates the state for passengers getting off of a flight
at its current airport.  The passengers must be on that flight, and the flight must
be located at the destination airport as referenced by the ticket. */
-- -----------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS passengers_disembark;
DELIMITER //

CREATE PROCEDURE passengers_disembark (IN ip_flightID VARCHAR(50))
BEGIN
    UPDATE person 
    SET locationID = (
            SELECT locationID 
            FROM flight
            LEFT JOIN route_path ON (flight.routeID, flight.progress) = (route_path.routeID, route_path.sequence)
            NATURAL JOIN leg
            JOIN airport ON leg.arrival = airport.airportID
            WHERE ip_flightID = flight.flightID
        )
    WHERE locationID = (
            SELECT locationID 
            FROM flight
            JOIN airplane ON support_tail = tail_num
            WHERE ip_flightID = flight.flightID
        )
        AND (
            SELECT airportID 
            FROM passenger_vacations
            WHERE passenger_vacations.personID = personID
            AND sequence = 1
        ) = (
            SELECT arrival 
            FROM flight
            LEFT JOIN route_path ON (flight.routeID, flight.progress) = (route_path.routeID, route_path.sequence)
            NATURAL JOIN leg
            WHERE ip_flightID = flight.flightID
        );

    DELETE FROM passenger_vacations 
    WHERE personID IN (
            SELECT personID 
            FROM person
            NATURAL JOIN airport
            WHERE person.personID = personID
            AND airport.airportID = airportID
            AND sequence = 1
        );

    UPDATE passenger_vacations
    SET sequence = sequence - 1
    WHERE personID IN (
            SELECT personID 
            FROM (
                SELECT * 
                FROM passenger_vacations
            ) AS pv
            WHERE pv.sequence = 2 
            AND NOT EXISTS (
                SELECT personID 
                FROM (
                    SELECT * 
                    FROM passenger_vacations
                ) AS pv2 
                WHERE pv.personID = pv2.personID 
                AND sequence = 1
            )
        );
END //

DELIMITER ;


-- [10] assign_pilot()
-- -----------------------------------------------------------------------------
/* This stored procedure assigns a pilot as part of the flight crew for a given
flight.  The pilot being assigned must have a license for that type of airplane,
and must be at the same location as the flight.  Also, a pilot can only support
one flight (i.e. one airplane) at a time.  The pilot must be assigned to the flight
and have their location updated for the appropriate airplane. */
-- -----------------------------------------------------------------------------
drop procedure if exists assign_pilot;
delimiter //
create procedure assign_pilot (in ip_flightID varchar(50), ip_personID varchar(50))
sp_main: begin
 DECLARE planeTailNum varchar(50);
    DECLARE neededLicense varchar(100);
    DECLARE pilotLoc varchar(50);
    DECLARE flightLoc varchar(50);
    DECLARE pilotAirportCode char(3);
    DECLARE flightRouteID varchar(50);
    DECLARE flightLegID varchar(50);
    DECLARE planeAirportCode char(3);
     
     
     
	if 'on_ground' != (select airplane_status from flight where ip_flightID = flightID) then
		leave sp_main;
    end if;
	if exists (
		select commanding_flight
       from pilot
       where personID = ip_personID and commanding_flight != ip_flightID
    ) then
		leave sp_main;
 	end if;    
    select support_tail into planeTailNum
    from flight where flightID = ip_flightID;
     
     select plane_type into neededLicense
     from airplane where tail_num = planeTailNum;
     
       if not exists (
		select personID
        from pilot_licenses
        where license = neededLicense and personID = ip_personID
     ) then
     
		leave sp_main;
	end if;
 
     select locationID into pilotLoc
     from person where personID = ip_personID;
     
 if pilotLoc like '%plane' then
	leave sp_main;
	end if;

    select airportID into pilotAirportCode
    from airport where pilotLoc = locationID;
    
    select routeID into flightRouteID
    from flight where flightID = ip_flightID;
    
    
   select legID into flightLegID
   from route_path where routeID = flightRouteID and sequence = (select progress from flight where flightID = ip_flightID);
    
    
    select arrival into planeAirportCode
    from leg where legID = flightLegID;
  
 if planeAirportCode != pilotAirportCode then
 		leave sp_main;
 	end if; 
    
    update person 
    set locationID = (select locationID from airplane where tail_num = planeTailNum)
    where personID = ip_personID;
    
    update pilot
    set commanding_flight = ip_flightID
    where personID = ip_personID;

end //
delimiter ;

-- [11] recycle_crew()
-- -----------------------------------------------------------------------------
/* This stored procedure releases the assignments for a given flight crew.  The
flight must have ended, and all passengers must have disembarked. */
-- -----------------------------------------------------------------------------
drop procedure if exists recycle_crew;
delimiter //
drop procedure if exists recycle_crew;
delimiter //
Create procedure recycle_crew (IN ip_flightID VARCHAR(50))
sp_main: BEGIN
    DECLARE flight_status VARCHAR(50);
    DECLARE total_legs INT;
    DECLARE completed_legs INT;

    SELECT airplane_status
    INTO flight_status
    FROM flight
    WHERE flightID = ip_flightID;

    IF flight_status <> 'on_ground' THEN
        leave sp_main;
    END IF;

    SELECT COUNT(DISTINCT legID) INTO total_legs
    FROM route_path
    WHERE routeID = (SELECT routeID FROM flight WHERE flightID = ip_flightID);

    SELECT COUNT(DISTINCT legID) INTO completed_legs
    FROM route_path
    WHERE routeID = (SELECT routeID FROM flight WHERE flightID = ip_flightID)
      AND sequence = (SELECT MAX(sequence) FROM route_path WHERE routeID = (SELECT routeID FROM flight WHERE flightID = ip_flightID));

    IF completed_legs < total_legs THEN
        leave sp_main;
    END IF;

    UPDATE pilot
    SET commanding_flight = NULL
    WHERE commanding_flight = ip_flightID;
END //
DELIMITER ;


-- [12] retire_flight()
-- -----------------------------------------------------------------------------
/* This stored procedure removes a flight that has ended from the system.  The
flight must be on the ground, and either be at the start its route, or at the
end of its route.  And the flight must be empty - no pilots or passengers. */
-- -----------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS retire_flight;
DELIMITER //

CREATE PROCEDURE retire_flight (IN ip_flightID VARCHAR(50))
sp_main: BEGIN
    -- Check if parameters are null
    IF (ip_flightID IS NULL) THEN
        LEAVE sp_main;
    END IF;

    -- Check if flight is at the start or end of the route
    IF ((SELECT airplane_status FROM flight WHERE flightID = ip_flightID) != 'on_ground') THEN
        LEAVE sp_main;
    END IF;

    IF (
        (SELECT progress FROM flight WHERE flightID = ip_flightID) != 0 
        AND 
        (SELECT COUNT(*) FROM route_path WHERE routeID IN (SELECT routeID FROM flight WHERE flightID = ip_flightID)) != (SELECT progress FROM flight WHERE flightID = ip_flightID)
    ) THEN
        LEAVE sp_main;
    END IF;

    -- Check if flight still has passengers
    IF (
        SELECT COUNT(*) 
        FROM person 
        WHERE locationID IN (
            SELECT locationID 
            FROM airplane 
            WHERE tail_num IN (SELECT support_tail FROM flight WHERE flightID = ip_flightID)
        )
    ) > 0 THEN
        LEAVE sp_main;
    END IF;

    -- Check if flight still has pilots
    IF ((SELECT COUNT(*) FROM pilot WHERE commanding_flight = ip_flightID) > 0) THEN
        LEAVE sp_main;
    END IF;

    -- Delete the flight
    DELETE FROM flight WHERE flightID = ip_flightID;
END //
DELIMITER ;




-- [13] simulation_cycle()
-- -----------------------------------------------------------------------------
/* This stored procedure executes the next step in the simulation cycle.  The flight
with the smallest next time in chronological order must be identified and selected.
If multiple flights have the same time, then flights that are landing should be
preferred over flights that are taking off.  Similarly, flights with the lowest
identifier in alphabetical order should also be preferred.

If an airplane is in flight and waiting to land, then the flight should be allowed
to land, passengers allowed to disembark, and the time advanced by one hour until
the next takeoff to allow for preparations.

If an airplane is on the ground and waiting to takeoff, then the passengers should
be allowed to board, and the time should be advanced to represent when the airplane
will land at its next location based on the leg distance and airplane speed.

If an airplane is on the ground and has reached the end of its route, then the
flight crew should be recycled to allow rest, and the flight itself should be
retired from the system. */
-- -----------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS simulation_cycle;
DELIMITER //

CREATE PROCEDURE simulation_cycle ()
sp_main: BEGIN
    DECLARE selected_flight VARCHAR(50) DEFAULT NULL;
    SET selected_flight = (
        SELECT flightID 
        FROM flight 
        WHERE next_time = (
            SELECT MIN(next_time) 
            FROM flight
        ) 
        ORDER BY airplane_status, flightID 
        LIMIT 1
    );

    IF (
        (SELECT airplane_status FROM flight WHERE flightID = selected_flight) = 'in_flight'
    ) THEN
        CALL flight_landing(selected_flight);
        CALL passengers_disembark(selected_flight);
        LEAVE sp_main;
    END IF;

    IF (
        (SELECT progress FROM flight WHERE flightID = selected_flight) = (
            SELECT MAX(sequence)
            FROM route_path 
            WHERE routeID = (
                SELECT routeID 
                FROM flight 
                WHERE flightID = selected_flight
            )
        )
    ) THEN
        CALL recycle_crew(selected_flight);
        CALL retire_flight(selected_flight);
        LEAVE sp_main;
    END IF;

    IF (
        (SELECT airplane_status FROM flight WHERE flightID = selected_flight) = 'on_ground'
    ) THEN
        CALL passengers_board(selected_flight);
        CALL flight_takeoff(selected_flight);
        LEAVE sp_main;
    END IF;
END //

DELIMITER ;


-- [14] flights_in_the_air()
-- -----------------------------------------------------------------------------
/* This view describes where flights that are currently airborne are located. */
-- -----------------------------------------------------------------------------
create or replace view flights_in_the_air (departing_from, arriving_at,
num_flights,
flight_list, earliest_arrival, latest_arrival, airplane_list) as
select departure as 'departing_from', arrival as 'arriving_at', count(*) as 'num_flights', 
group_concat(flightID) as 'flight_list', min(next_time) as 'earliest_arrival', max(next_time) as 'latest_arrival', group_concat(locationID) as 'airplane_list'
from airplane
join flight on 
(flight.support_airline = airplane.airlineID and flight.support_tail = airplane.tail_num)
join route_path on 
(route_path.routeID = flight.routeID and progress = sequence)
natural join leg
where airplane_status = 'in_flight'
group by departure, arrival;


-- [15] flights_on_the_ground()
-- -----------------------------------------------------------------------------
/* This view describes where flights that are currently on the ground are located. */
-- -----------------------------------------------------------------------------
create or replace view flights_on_the_ground as
select departure as 'departing_from', count(*) as 'num_flights',
group_concat(flightID) as 'flight_list', min(next_time) as 'earliest_arrival', max(next_time) as 'latest_arrival',
 group_concat(locationID) as 'airplane_list'
from airplane
join flight on
(flight.support_airline = airplane.airlineID and flight.support_tail = airplane.tail_num)
join route_path on
(route_path.routeID = flight.routeID and  progress+1= sequence  )
 natural join leg
where airplane_status = 'on_ground'
group by departure
UNION
select arrival as 'departing_from', count(*) as 'num_flights',
group_concat(flightID) as 'flight_list', min(next_time) as 'earliest_arrival', max(next_time) as 'latest_arrival',
group_concat(locationID) as 'airplane_list'
from airplane
join flight on
(flight.support_airline = airplane.airlineID and flight.support_tail = airplane.tail_num)
join route_path on
(route_path.routeID = flight.routeID and progress = sequence)
 natural join leg
where airplane_status = 'on_ground'
group by arrival;


-- [16] people_in_the_air()
-- -----------------------------------------------------------------------------
/* This view describes where people who are currently airborne are located. */
-- -----------------------------------------------------------------------------
create or replace view people_in_the_air (departing_from, arriving_at,
num_airplanes,
airplane_list, flight_list, earliest_arrival, latest_arrival, num_pilots,
num_passengers, joint_pilots_passengers, person_list) as
select departure as 'departing_from', arrival as 'arriving_at', 
count(distinct tail_num) as 'num_airplanes', group_concat(distinct airplane.locationID) as 'airplane_list',
group_concat(distinct flightID) as 'flight_list', min(next_time) as 'earliest_arrival', max(next_time) as 'latest_arrival',
count(taxID) as 'num_pilots', count(*) - count(taxID) as 'num_passengers', count(*) as 'joint_pilots_passengers', group_concat(person.personID) as 'person_list' from person
natural join airplane
join flight on 
(flight.support_airline = airplane.airlineID and flight.support_tail = airplane.tail_num)
join route_path on 
(route_path.routeID = flight.routeID and progress = sequence)
natural join leg
left join pilot on pilot.personID = person.personID
left join passenger on passenger.personID = person.personID
where airplane_status = 'in_flight'
group by departure, arrival;


-- [17] people_on_the_ground()
-- -----------------------------------------------------------------------------
/* This view describes where people who are currently on the ground are located. */
-- -----------------------------------------------------------------------------
create or replace view people_on_the_ground (departing_from, airport, airport_name,
city, state, country, num_pilots, num_passengers, joint_pilots_passengers,
person_list) as
SELECT
	airportID as departing_from,
    locationID as airport,
    airport_name,
    city,
    state,
    country,
    COUNT(DISTINCT pilot.personID) as num_pilots,
	COUNT(DISTINCT persons.personID) - COUNT(DISTINCT pilot.personID) as num_passangers,
	COUNT(DISTINCT persons.personID) as joint_pilots_passangers,
    GROUP_CONCAT(DISTINCT persons.personID ORDER BY persons.personID) as person_list
FROM
	airport
NATURAL JOIN
	(select * from person where locationID like 'port_%') persons
LEFT JOIN
    pilot ON persons.personID = pilot.personID
GROUP BY
	airportID
ORDER BY
	airportID;



-- [19] alternative_airports()
-- -----------------------------------------------------------------------------
/* This view displays airports that share the same city and state. */
-- -----------------------------------------------------------------------------
create or replace view alternative_airports (city, state, country, num_airports,
airport_code_list, airport_name_list) as
select '_', '_', '_', '_', '_', '_';
CREATE OR REPLACE VIEW alternative_airports AS
SELECT
  a.city,
  a.state,
  a.country,
  COUNT(DISTINCT a.airportID) AS num_airports,
  GROUP_CONCAT(a.airportID) AS airport_code_list,
  GROUP_CONCAT(a.airport_name) AS airport_name_list
FROM
  airport AS a
GROUP BY
  a.city, a.state, a.country
HAVING
  COUNT(DISTINCT a.airportID) > 1;
