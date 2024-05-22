filename resp1 temp;
proc http
    url='http://localhost:8080/produkty'
    method="get"
    out=resp1;
run;

libname rss1 json fileref=resp1;
libname stud BASE "C:\Users\anial\Documents\Semestr 4\Integracja danych w sys. analitycznych";

data stud.produkty;
    set rss1.root;
run;

filename resp2 temp;
proc http
    url='http://localhost:8080/szczegolyZamowienia'
    method="get"
    out=resp2;
run;

libname rss2 json fileref=resp2;

data stud.szczegolyZamowienia;
    set rss2.root;
run;

ods _all_ close;
ods listing gpath=".\src\main\resources";
ods graphics on / reset=imagefmt=png imagename="wykres2";

proc sql;
    create table stud.combined_data as
    select a.nazwa, b.id, b.ilosc
    from stud.produkty as a
    left join stud.szczegolyZamowienia as b
    on a.id = b.idProdukt;
quit;

proc gchart data=stud.combined_data;
    vbar nazwa / sumvar=ilosc
    maxis=axis1 raxis=axis2;
run;

ods graphics off;
ods listing;
