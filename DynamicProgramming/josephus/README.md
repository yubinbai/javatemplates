Josephus Problem and Josephus permutation
==========================
There are people standing in a circle waiting to be executed. The counting out begins at some point in the circle and proceeds around the circle in a fixed direction. In each step, a certain number of people are skipped and the next person is executed. The elimination proceeds around the circle (which is becoming smaller and smaller as the executed people are removed), until only the last person remains, who is given freedom.
The task is to choose the place in the initial circle so that you are the last one remaining and so survive.

Solutions for Josephus Permutation:

1. Use arraylist and remove each person when met. O(n^2), since the 
array list allows random access and rearrange the index, however it 
needs to change 1/2 array indices (amortized) each time.

2. Use order static tree and remove the k_th element each time 
O(n * log k) 


Solution for Josephus Survivor: 

Dynamic programming, O(n)

f(n, k) = ((f(n-1, k) + k - 1) mod n) + 1

n : total number of people
k : k - th person is killed 