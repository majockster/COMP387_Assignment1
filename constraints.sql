-- Can't have a class on a certain day end before it starts.
ALTER TABLE coursetimes 
	ADD CONSTRAINT coursetimes_dateCheck CHECK(startTime<endTime);

-- Can't have a course end before it starts.
ALTER TABLE courses 
	ADD CONSTRAINT courses_dateCheck CHECK(startDate<endDate);

-- We only have Adults in our system, either students or administrators.
-- Adults defined by being 18 years of age or older.
ALTER TABLE person 
	ADD CONSTRAINT person_isAnAdult CHECK(dateOfBirth < '2004-01-01');

-- We accept phone numbers under the format: XXX XXX XXXX , no spaces. 
-- Or with an up to two digit regional indicator, XX XXX XXX XXXX , no spaces.
ALTER TABLE person 
	ADD CONSTRAINT person_validPhoneNumberLength CHECK(LENGTH(phoneNumber) >= 10);