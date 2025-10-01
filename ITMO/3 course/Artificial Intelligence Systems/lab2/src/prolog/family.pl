married(marinicheva_anna, ivan, 1946).
married(roza, fesenko_alexander1, 1950).
married(alexey, anna_kalmykova, 1944).
married(alexander_karagodin, alexandra, 1949).
married(vera, nikolai_fesenko, 1980).
married(alexander_fesenko2, elena, 2007).
married(anna_laguta, dmitriy, 2004).

parent(marinicheva_anna, valentina).
parent(marinicheva_anna, vera).
parent(marinicheva_anna, galina).
parent(ivan, valentina).
parent(ivan, vera).
parent(ivan, galina).

parent(roza, nikolai_fesenko).
parent(roza, lybov).
parent(roza, elena).
parent(fesenko_alexander1, nikolai_fesenko).
parent(fesenko_alexander1, lybov).
parent(fesenko_alexander1, elena).

parent(olga, andrey).
parent(olga, anna_laguta).
parent(nikolai_karagodin, andrey).
parent(nikolai_karagodin, anna_laguta).

parent(vera, alexander_fesenko2).
parent(vera, anna_karagodina).
parent(nikolai_fesenko, alexander_fesenko2).
parent(nikolai_fesenko, anna_karagodina).

parent(elena, daria).
parent(alexander_fesenko2, daria).

parent(anna_karagodina, kseniya).
parent(anna_karagodina, marina).
parent(andrey, kseniya).
parent(andrey, marina).

parent(anna_laguta, vlad).
parent(anna_laguta, denis).
parent(anna_laguta, kirill).
parent(anna_laguta, ariana).
parent(dmitriy, vlad).
parent(dmitriy, denis).
parent(dmitriy, kirill).
parent(dmitriy, ariana).

born(kseniya, 2005).
born(marina, 2010).
born(daria, 2007).
born(vlad, 2006).
born(denis, 2008).
born(kirill, 2011).
born(ariana, 2014).

divorced(anna_karagodina, andrey).
divorced(olga, nikolai).

isGrandparent(X, Y):-
    parent(Z, Y),
    parent(X, Z)
    .

isSibling(X, Y):-
    setof(Parent, parent(Parent, X), AllParentsX),
    setof(Parent, parent(Parent, Y), AllParentsY),
    AllParentsX == AllParentsY,
    X \= Y
    .

isCousin(X, Y):-
    parent(ParentX, X),
    parent(ParentY, Y),
    isSibling(ParentX, ParentY),
    \+ isSibling(X, Y),
    X \= Y
    .

hasSibling(X):-
    parent(Y, X),
    parent(Y, Z),
    X \= Z
    .

isTheSameAge(X, Y):-
    born(X, Z),
    born(Y, Z)
    .

isOlderCousin(X, Y):-
    isCousin(X, Y);
    isSibling(X, Y),
    born(X, YearX),
    born(Y, YearY),
    YearX > YearY
    .

wereEverMarried(X, Y):-
    married(X, Y, _);
    married(Y, X, _);
    divorced(X, Y);
    divorced(Y, X)
    .

isMotherInLaw(X, Y):-
    wereEverMarried(Y, Spouse),
    parent(X, Spouse)
    .

marriedBeforeYear(X, Y, Year):-
    married(X, Y, MarriageYear),
    MarriageYear<Year
    .

isAncestor(Ancestor, X):-
    parent(Ancestor, X)
    .
isAncestor(Ancestor, X):-
    parent(Parent, X),
    isAncestor(Ancestor, Parent)
    .


