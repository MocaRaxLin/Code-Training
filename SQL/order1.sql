select customerNumber
from (
select customerNumber, count(customerNumber) as feq
from ORDER1
group by customerNumber
order by feq desc
)
limit 1