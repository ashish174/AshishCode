1. I have created static functions and global static variable etc. Instead always use class objects, method variables etc. Then init object and call methods.  Static is not thread safe.
2. At many places, i wanted to pass a mutable object reference for counters, that is being used throughout the recursion stack. Since i cant use int/Integer as they are immutable, and is passed by value. So i have used AtomicInteger at certain places. This is not correct. AtomicInteger should only be used in concurrency context. Here, good approach is to create a placeholder object
`
private class CountHolder{
   int  count;
}
`
other approach can be to use int[];
`
   int[] counter;
   counter[0]++; 
`
can use class variable as counter as well in case, concurrency is not a concern.
   `
   class TreeCounter {
   int counter = 0;

   void recursive(Node node) {
   if (node == null) return;
   counter++;
   recursive(node.left);
   recursive(node.right);
   }
   }
   `



