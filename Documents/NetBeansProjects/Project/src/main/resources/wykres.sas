ods _all_ close;
ods listing gpath = ".\src\main\resources";
ods graphics on /reset imagefmt = png reset imagename = "wykres";
 
GOPTIONS reset all hsize=8in vsize=4in;
title 'Wykres liczebności klientów względem pici';
axisl label=('pleć');
axis2 label=(a=90 'Ilość') ;

PROC GCHART DATA=stud.klienci;
VBAR plec / type=freq
maxis=axisl raxis=axis2;
RUN;
 
ods graphics off;
ods listing ;