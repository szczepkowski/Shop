Shop
====

JSF, Hibernate/JPA, Tomcat, MySQL, Git

Projekt jest serwisem aukcyjnym. Przy jego tworzeniu byłem skupiony na technologii JSF. 

Za pomocą adnotacji postConstruct jest tworzone konto admina : nick : admin, pw : a. Po zalogowaniu jako admin można dodać kategorię, 100 losowych ofert, dodać nowego admina.

Gdy już dodamy kategorie i losowe oferty w kategoriach, linki do kategorii znajdą się na lewym pasku bacznym.

Klikając na ofertę można ją kupić, ale tylko zalogowani użytkownicy mogą dokonać zakupu.

Rejestrujemy nowego użytkownika pod linkiem rejestracja. Mając konto możeny się zalogować.

Zalogowany użytkownik może dokonać zakupu klikając kup teraz na linku z ofertą. Kupiona oferta przestaje być aktywna i przestaje być widoczna w kategoriach, a jest widoczna w panelu użytkownika jednak już bez możliwości zakupu.

Kupując dany produkt tworzona jest nowa Transakcja w bazie danych. Transakcje możemy zrealizować płacąc za produkt w końcie użytkownika.

