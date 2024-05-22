filename resp1 temp;
proc http
    url='http://localhost:8080/zamowieniaWymiar'
    method="get"
    out=resp1;
run;
libname rss1 json fileref=resp1;
libname stud BASE "C:\Users\anial\Documents\Semestr 4\Integracja danych w sys. analitycznych";
data stud.zamowieniaWymiar;
    set rss1.root;
run;
filename resp2 temp;
proc http
    url='http://localhost:8080/projektanci'
    method="get"
    out=resp2;
run;

libname rss2 json fileref=resp2;
data stud.projektanci;
    set rss2.root;
run;
ods _all_ close;
ods listing gpath=".\src\main\resources";
ods graphics on / reset=imagefmt=png imagename="wykres3";
proc sql;
    create table stud.combined_data as
    select a.idProjektant, b.imie, b.nazwisko
    from stud.zamowieniaWymiar as a
    left join stud.projektanci as b
    on a.idProjektant = b.id;
quit;

data final;
    set stud.combined_data;
    Projektant = catx(' ', imie, nazwisko);
run;
proc gchart data=final;
    pie Projektant / type=freq
                    discrete
                    value=inside
                    percent=inside
                    slice=outside;
run;

ods graphics off;
ods listing;
