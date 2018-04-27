# BCNF-Decomposition
A driver program to decompose a relation with violating functional dependencies into BCNF (Boyce Codd Normal Form)

Sample Input: 
A B C D E
A B -> C
C -> D
D -> B E

Output:

Relation : ABCDE<br/>

The list of non trivial dependencies : <br/>
CDE -> B<br/>
AB -> CDE<br/>
AD -> BCE<br/>
CE -> BD<br/>
BCE -> D<br/>
DE -> B<br/>
D -> BE<br/>
BD -> E<br/>
ABCD -> E<br/>
ABDE -> C<br/>
ABD -> CE<br/>
AC -> BDE<br/>
BCD -> E<br/>
CD -> BE<br/>
ABC -> DE<br/>
ADE -> BC<br/>
ACD -> BE<br/>
ABE -> CD<br/>
ACDE -> B<br/>
BC -> DE<br/>
C -> BDE<br/>
ACE -> BD<br/>
ABCE -> D<br/>

The list of bcnf violating functional dependencies : <br/>
CDE -> B<br/>
CE -> BD<br/>
BCE -> D<br/>
DE -> B<br/>
D -> BE<br/>
BD -> E<br/>
BCD -> E<br/>
CD -> BE<br/>
BC -> DE<br/>
C -> BDE<br/>

The output of BCNF Decomposition:<br/>

[DE,CD,BDE,AC]<br/>


