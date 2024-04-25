/*
Query 0
*/
select bdate, address 
from employee 
where fname = 'John' and lname = 'Smith';
/*
Query 1
*/
select fname, lname, address
from employee
where dno in (select dnumber from department where dname = 'Research');
/*
Query 2
*/
select pnumber, dnum, lname, address, bdate
from project, employee, department
where plocation = 'Stafford' and dnum = dnumber and mgrssn = ssn;
/*
Query 3
*/
select fname, lname
from project, employee, department
where pnumber = 5;
/*
Query 4
*/
select pnumber
from project, employee, department
where lname = 'Smith' and dnum = dnumber and mgrssn = ssn union
(select pno from works_on, employee where essn = ssn and lname = 'Smith');
/*
Query 5
*/
select distinct fname, lname
from employee
where ssn in (select essn from dependent group by essn having count(*) >= 2);
/*
Query 6
*/
select distinct fname, lname
from employee
where ssn not in (select essn from dependent);
/*
Query 7
*/
select fname, lname
from employee
where ssn in (select essn from dependent)
and ssn in (select mgrssn from department);
/*
Query 80
*/
select fname, lname, salary * 1.1000 as raise
from employee
where dno = 5;
/*
Query 81
*/
select sum(salary) as aggregate_salary
from employee
where dno = 5;
/*
Query 81
*/
select count(distinct salary)
from employee;
/*
Query 85
*/
select count(superssn)
from employee
where superssn = '333445555';
/*
Query 86
*/
select count(superssn)
from employee
where superssn != '333445555';
/*
Query 87
*/
select count(ssn)
from employee;
/*
Query 88
*/
select dno, min(salary), avg(salary), max(salary)
from employee
group by dno;
/*
Query 89
*/
select dno, min(salary), avg(salary), max(salary)
from employee
group by dno
having count(*) < 4 ;
/*
Query 40
*/
select 'Hello World!';
/*
Query 41
*/
select 'Hello World!' as greeting;
/*
Query 42
*/
select 4 + 6 as sum;
/*
Query 43
*/
select 4 + 6 as sum, 3 * 7 as product, 9 - 5 as difference;
/*
Query 44
*/
select *
from employee;
/*
Query 50
*/
select fname, lname, address
from employee;
/*
Query 51
*/
select lname as 'Last Name', fname as 'First Name', address as 'Residence Location'
from employee;
/*
Query 52
*/
select concat(fname, ' ', lname) as 'Whole Name', address
from employee;
/*
Query 53
*/
select dno
from employee;
/*
Query 54
*/
select distinct dno
from employee;
/*
Query 55
*/
select salary, dno
from employee;
/*
Query 56
*/
select distinct salary, dno
from employee;
/*
Query 57
*/
select fname, lname, address
from employee
where dno <> 5;
/*
Query 62
*/
select fname, lname, address
from employee
where salary > 30000;
/*
Query 63
*/
select fname, lname, address
from employee
where dno = 5 or salary > 30000;
/*
Query 64
*/
select fname, lname, address
from employee
where dno = 5 and salary > 30000;
/*
Query 65
*/
select fname, lname, address, bdate
from employee
where bdate > '1968-01-01';
/*
Query 66
*/
select fname, lname, address, bdate
from employee
where bdate > '1965-01-08' and bdate < '1970-08-10';
/*
Query 67
*/
select fname, lname, address
from employee
order by fname;
/*
Query 68
*/
select fname, lname, address
from employee
order by lname desc;
/*
Query 69
*/
select fname, address
from employee
order by lname desc;
/*
Query 70
*/
select fname, lname, address
from employee
order by lname, fname;
/*
Query 71
*/
select fname, lname, address
from employee
where address like '%Houston TX';
/*
Query 72
*/
select fname, lname, address
from employee
where address like '%Dallas%';
/*
Query 73
*/
select fname, lname, address
from employee
where fname like 'J%';
/*
Query 74
*/
select fname, lname, address
from employee
where lname like '%aya%';
/*
Query 75
*/
/*
select fname, lname, address
from employee
where lname like '%aya%' and lname not like '%aya';
*/
#Class Query
select concat(fname, ' ', lname) as 'whole_name', ssn, bdate as 'birth_date', salary
from employee
where bdate < '1960-1-1' and salary > 30000 or bdate > '1960-1-1' and salary < 30000;