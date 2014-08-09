Pancake sorting
---------------

You probably recall from 15-251 how to sort by prefix reversal
(also known as pancake flipping).

Here's a problem we want to solve.  Given an arbitrary permutation
of numbers 1...n (a list of numbers) we want to sort them by reversing

prefixes.  For example, say we start with:

5 1 4 2 3

Reverse the whole thing and get:

3 2 4 1 5

Now put 4 at the front:

4 2 3 1 5

Now put 4 in place:

1 3 2 4 5

Now put 3 in front:

3 1 2 4 5

Not put 3 in place:

2 1 3 4 5

finish up:

1 2 3 4 5

Clearly this algorithm takes at most 2(n-1) flips.  But suppose
you wanted to print out the set of prefix lengths that are flipped
in order to sort the permutation.  So in this case we would output

5 3 4 2 3 2

The naive algorithm to compute this will take time O(n^2), because it
has to maintain, say, an array of all the elements, and each prefix
reversal takes O(n) time and there are O(n) of them.  Not to mention
searching for the next thing that needs to be put in place.

We'll show how to use augmented splay trees to do this in O(n log n)
time.

Since the tree is not going to keep the keys in symmetric order (left
to right) we need a way to find a given key in the tree.  To
facilitate this we create an array A where A[i] is a pointer to 
the node in the tree that stores i.  This array does not change over
time.

To allow for prefix reversal, we will keep what I call a reverse
bit r in each node.  The meaning of this bit is the following.
If n.r is true, then the meaning of left and right in the subtree
rooted at n are reversed.  And this applies recursively to all the
reverse bits in the tree.

(Example)

So, if i have a tree that represents the sequence 3 2 1 4, and 
I toggle the reverse bit of the root of the tree, then I am
representing 4 1 2 3.

If I have a node n where n.r=true, and can "discharge" this
reversal by swapping the left and right children of n and
then toggling the reverse bits in the left and right children.

This follow from the following identity for strings.  If A and
B are strings and AB is the concatenation of A and B we have:

  reverse(AB) = reverse(B) reverse(A)

How do we implement splaying in a tree with reverse bits?
The shape of paths is of course important to the splaying
algorithm.  So what we can do is before we splay along 
a path in the tree, we first discharge all the reverse bits
along that path.  Then we can use the standard splaying
algorithm.  Every node to which a rotation is applied has
its reverse bit = false.

We're also going to need to maintain size fields as we did for
the rank/select problem.

Here's how we can compute the prefix reversal sequence in
O(n log n) time.

First we construct an initial tree to represent the initial
sequence.  We also construct the array A[] described above.

Now we find key n in the tree by looking up A[n].  This points to a
node.  We splay this node.  Now n is at the root of the tree.  The
size field of the left subtree of the root tells us the index of n.
We split off n and its left subtree from the rest.  Now we reverse
that part (by toggling its reverse bit), and join them together again.
Now n is at the left.  And we now toggle the reverse bit of the whole
thing (and output the size of the tree).  n is now at the right end.
So we delete n from the tree, and repeat the whole process for n-1.

The process of handling n is a split and a join, which is O(log n).
Thus the whole algorithm is O(n log n).