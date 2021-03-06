Optimal binary search tree problem: 
===============
Formally, we are given a sequence K <k1; k2; ... ; kn> of n distinct keys in sorted order 
(so that k1 < k2 < ... < kn), and we wish to build a binary search tree from these keys.
For each key ki , we have a probability pi that a search will be for ki. 

Some searches may be for values not in K, and so we also have (n + 1) “dummy keys”
d0; d1; d2; ... ;dn representing values not in K. In particular, d0 represents all values
less than k1, dn represents all values greater than kn, and for i = 1;2; : : : ;n
the dummy key di represents all values between ki and ki+1. For each dummy key di , 
we have a probability qi that a search will correspond to di .

Therefore sum of all pi and qi = 1

For a given set of probabilities, we wish to construct a binary search tree whose
expected search cost is smallest.

Dynamic programming solution:

for a selected root position of sub tree [i,j]
e[i][j] = e[i][r-1] + e[r+1][j] + w[i][j] 

and

w[i][j] = w[i][r-1] + p[r] + w[r+1][j]