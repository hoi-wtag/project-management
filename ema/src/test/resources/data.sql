-- INSERT EMPLOYEES
insert into employee (first_name, last_name, email_id) values ('John', 'Warton', 'warton@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Mike', 'Lanister', 'lanister@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Steve', 'Reeves', 'Reeves@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Ronald', 'Connor', 'connor@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Jim', 'Salvator', 'Sal@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Peter', 'Henley', 'henley@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Richard', 'Carson', 'carson@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Honor', 'Miles', 'miles@gmail.com');
insert into employee (first_name, last_name, email_id) values ('Tony', 'Roggers', 'roggers@gmail.com');

-- INSERT PROJECTS			
insert into project (name, stage, description) values ('Large Production Deploy', 'NOTSTARTED', 'This requires all hands on deck for the final deployment of the software into production');
insert into project (name, stage, description) values ('New Employee Budget',  'COMPLETED', 'Decide on a new employee bonus budget for the year and figureout who will be promoted');
insert into project (name, stage, description) values ('Office Reconstruction', 'INPROGRESS', 'The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed');
insert into project (name, stage, description) values ('Improve Intranet Security', 'INPROGRESS', 'With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation');

-- INSERT PROJECT_EMPLOYEE_RELATION
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'Large Production Deploy');
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'New Employee Budget');
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'Office Reconstruction');
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Reeves' AND p.name = 'Large Production Deploy');
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'New Employee Budget');
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'Improve Intranet Security');
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Henley' AND p.name = 'Office Reconstruction');
--insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Henley' AND p.name = 'Improve Intranet Security');