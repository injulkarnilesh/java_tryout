Algorithms
===================
Implemention of **Coursera Algorithms** course.

----------

Dynamic Connectivity / Union-Find
-------------
Given set of N objects,
 - *Union command*: connects two objects.
 - *Find Query*: is there path connecting the two objects.
 > **While programming**
	 - Name these objects 0 to (N -1) and use these as array indices.
	 -	*Find* : check if objects are in same component
	 - *Union* : replace components containing two objects with their union.


####  Quick Find
 - Integer array [N]
 - Two elements (array indexes) are connected iff they have the same id.
 - Find : i and j are connected is array[i] == array[j].
 - Union : merge components (set of array elements) having i and j so that all elements in components containing i should have value array[j].
 - N<sup>2</sup> array accesses to process sequence of N union commands.

#### Quick Union
 - Integer array [N]
 - array[i] is parent of i
 - root of i is parent of parent of and so on until a[p] == p.
 - Find : i and j are connected if root(i) == root(j)
 - Union : set id of root(i) to root(j) so root(i) becomes child of root(j)

#### Weighted Quick Union
 - Quick union with effort to avoid tall trees.
 - Keep track of size of each tree (weight)
 - Connect root to smaller tree to root of larger tree.
 - Find: i and j are connected if root(i) == root(j)
  - Union : connect root(i) to root(j) such that size(i)<=size(j) and update size array of larger tree increase by size of smaller tree attached.

#### Weighted Quick Union with Path Compression
 - While finding root(i) set all elements visited to point to root(i) as parent.
 - Keeps tree almost flat
 - One Pass :   make every element on path to root to point to grandparent array[array[i]]
 - Two Pass: Keep track of visited elements and then on finding root set all their parents to root(i)
 - Practically almost linear.

| Algorithm            | Initialisation  | Union     |  Fin     |
| -------------        |:-------------:  | -----:    | -----:   |
| QuickFind            | N               |  N        |  1       |
| Quick Union          | N               |  N        |  N       |
| Weighted Quick Union | N               |  log N    |  long N  |


Analysis of algorithms
-------------
![Common Order-of-growth classification](https://raw.githubusercontent.com/injulkarnilesh/java_tryout/master/Algorithm/src/main/resources/OrderOfGrowth.png)

#### Running Time
 - Best Case : Big Omega
 - Worst Case : Big Oh
 - Average Case : Big Theta

#### Memory
 - 64 Bit machine —> 8 byte pointer.

| Type           | Bytes                         |
| -------------  |:-------------:                |
| boolean        |  1                            |
| byte           |  1                            |
| char           |  2                            |
| int            |  4                            |
| float          |  4                            |
| long           |  8                            |
| double         |  8                            |
| Array[N]       |  N (Cost of type) + 24  <br/> Eg long[n] = 8N + 24 |
| Object         |  Object Overhead : 16 bytes <br/> Reference field: 8 bytes each <br/> Padding : extra bytes used to make it total multiple of 8|


Stack and Queue
-------------
 - Stack - LIFO
 - Queue - FIFO
> Stack or queue implemented with array can be made resizable.
When get’s full, use underlying array of twice the size.
When on removing item, no of items become quarter of array size, use underlying array of half the size.
