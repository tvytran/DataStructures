1) The binary search tree took an average of 4.8792 seconds to run.
   The red black tree took an average of 1.6932 seconds to run.
   The hash map took an average of 1.0836 seconds to run.
2) I expected the hash map to be the fastest while the binary search tree to be the slowest. 
   Yes, the timing results matched my expectations. The three main methods used in the program for each
   data structure is the get, insertion, and remove method. So focusing on the different time complexities
   of each of the data structures will help identify the reason one is faster than the other. The average time complexity for a hash map
   for each of these methods is O(1) because of its hash function which will access the position for 
   these methods to be the same. The only way it would get longer is if there is a long list of linked list
   attached to the position, but that rarely happens because the hash map constantly checks the load factor
   to make sure the entries in the map are separated enough so that those three methods are efficient. The red
   black tree comes in second because its average time complexity for each of these methods is O(lgn) because of its
   self balancing methods that makes sure that all five components of a red black tree are held. Therefore, the red black 
   tree is usually in a balanced form of a binary search tree which allows those three methods to be held to that time complexity.
   Keeping it balanced makes sure that the red black tree does not hit its worse time complexity of O(n) for those methods which would be the case
   for the regular binary search tree. The binary search tree does not have that same balancing act so it is really easy for it 
   to be very unbalanced and preform at the worst time complexity of O(n) for each of those three methods. So it is expected that the 
   the hash map is fastest, the red black tree is second fastest, and binary search tree is the slowest. 