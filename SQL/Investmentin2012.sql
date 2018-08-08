select sum(v.TIV_2012)
from(
select distinct a.PID, a.TIV_2012
from TwitterInvestment2012 a, TwitterInvestment2012 b
where a.PID != b.PID and a.TIV_2012 = b.TIV_2011 and
			(a.LAT != B.LAT or a.LON != b.LON)
) v;