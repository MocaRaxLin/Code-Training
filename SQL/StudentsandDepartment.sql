select DEPT_NAME, ifnull(studentCount, 0) as studentCount
from Departments a left join (
select DEPT_ID, count(*) as studentCount
from Students group by DEPT_ID
) b on a.DEPT_ID = b.DEPT_ID
order by studentCount desc, DEPT_NAME asc;