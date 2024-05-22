filename resp temp;
proc http
url='http://localhost:8080/klienci'
method="get"
out=resp;
run;
libname rss json fileref=resp;
LIBNAME stud BASE "C:\Users\anial\Documents\Semestr 4\Integracja danych w sys. analitycznych";
data Stud.klienci;
set rss.root;
run;


ods _all_ close;
ods listing gpath = ".\src\main\resources";
ods graphics on /reset imagefmt = png reset imagename = "wykres1";


PROC GCHART DATA=stud.klienci;
VBAR plec / type=freq
maxis=axisl raxis=axis2;
RUN;
 
ods graphics off;
ods listing ;