# BCNF-Decomposition
A driver program to decompose a relation with violating functional dependencies into BCNF (Boyce Codd Normal Form)

Sample Input: 
A B C D E
A B -> C
C -> D
D -> B E

Output:

Relation : ABCDE

The list of non trivial dependencies : 
CDE -> B
AB -> CDE
AD -> BCE
CE -> BD
BCE -> D
DE -> B
D -> BE
BD -> E
ABCD -> E
ABDE -> C
ABD -> CE
AC -> BDE
BCD -> E
CD -> BE
ABC -> DE
ADE -> BC
ACD -> BE
ABE -> CD
ACDE -> B
BC -> DE
C -> BDE
ACE -> BD
ABCE -> D

The list of bcnf violating functional dependencies : 
CDE -> B
CE -> BD
BCE -> D
DE -> B
D -> BE
BD -> E
BCD -> E
CD -> BE
BC -> DE
C -> BDE

The output of BCNF Decomposition:

[DE,CD,BDE,AC]


