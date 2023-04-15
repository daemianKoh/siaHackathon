--INSERT FlightBooking
INSERT INTO flightBooking (bookingRefNo) VALUES ('TRABC1');
INSERT INTO flightBooking (bookingRefNo) VALUES ('TRABC2');

--INSERT FlightPersonInfo tan ah kong
INSERT INTO flightPersonInfo (bookingRefNo, personName, email, nationality, passport) 
VALUES ('TRABC1', 'CK', 'ck@hotmail.com', 'Singaporean', 'Y');

--INSERT FlightPersonInfo ah hua family
INSERT INTO flightPersonInfo (bookingRefNo, personName, email, nationality, visa) 
VALUES ('TRABC2', 'CK', 'ck@hotmail.com', 'Singaporean', 'Y');
INSERT INTO flightPersonInfo (bookingRefNo, personName, email, nationality, seat, passport, pcr) 
VALUES ('TRABC2', 'Balaji', 'balaji@hotmail.com', 'Singaporean', 'Y', 'Y', 'Y');
INSERT INTO flightPersonInfo (bookingRefNo, personName, email, nationality, baggage, seat, meal, visa) 
VALUES ('TRABC2', 'Mike', 'mike@hotmail.com', 'Singaporean', 'Y', 'Y', 'Y', 'Y' );
INSERT INTO flightPersonInfo (bookingRefNo, personName, email, nationality, pcr, vaccination, visa) 
VALUES ('TRABC2', 'Paul', 'paul@hotmail.com', 'Singaporean', 'Y', 'Y', 'Y');

--INSERT FlightTravelInfo tan ah kong
INSERT INTO flightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC1', 'SIN', 'ADL', parseDateTime('10-05-2023', 'dd-MM-yyyy'), '2300', parseDateTime('11-05-2023', 'dd-MM-yyyy'), '0700');
INSERT INTO flightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC1', 'ADL', 'SIN', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1000', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1800');

INSERT INTO flightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC2', 'SIN', 'TPE', parseDateTime('11-05-2023', 'dd-MM-yyyy'), '2300', parseDateTime('11-05-2023', 'dd-MM-yyyy'), '0400');
INSERT INTO flightTravelInfo (bookingRefNo, origin, destination, departDate, departTime, arrivalDate, arrivalTime) 
VALUES ('TRABC2', 'TPE', 'SIN', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1000', parseDateTime('16-05-2023', 'dd-MM-yyyy'), '1400');


--INSERT Country
INSERT INTO country (country, cityCode, city) VALUES ('Singapore', 'SIN', 'Singapore');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','ADL','Adelaide');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','AYQ','Ayers Rock/Uluru');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','BNE','Brisbane');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','CBR','Canberra');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','CNS','Cairns');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','HBA','Hobart');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','LST','Launceston');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','MEL','Melbourne');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','OOL','Gold Coast');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','PER','Perth');
INSERT INTO country (country, cityCode, city) VALUES ('Australia','SYD','Sydney');
INSERT INTO country (country, cityCode, city) VALUES ('Japan','CTS','Sapporo');
INSERT INTO country (country, cityCode, city) VALUES ('Japan','KIX','Osaka');
INSERT INTO country (country, cityCode, city) VALUES ('Japan','NRT','Tokyo');
INSERT INTO country (country, cityCode, city) VALUES ('Taiwan','KHH','Kaohsiung');
INSERT INTO country (country, cityCode, city) VALUES ('Taiwan','TPE','Taipei');