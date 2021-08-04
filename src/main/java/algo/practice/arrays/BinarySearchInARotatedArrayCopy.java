package algo.practice.arrays;



public class BinarySearchInARotatedArrayCopy {

  public static void main(String[] args) {
    int[] arr = {6,7,1,2,4,5};
    System.out.println(binarySearchInARotatedTree(arr, 2));
  }

  public static int binarySearchInARotatedTree(int[] arr, int key){
    int size  =arr.length;
    if(key==arr[0])
      return 0;
    int pivot = findPivot(arr);
    if(pivot==-1)
      return -1;
    else if(key < arr[0])
      return binarySearch(arr, pivot+1, size-1 , key);
    else
      return binarySearch(arr, 1, pivot, key);
  }

  public static int binarySearch(int[] arr, int i, int j, int key){
    if(i<=j){
      int mid = i + (j-i)/2;
      if(key==arr[mid])
        return mid;
      else if(key < arr[mid])
        return binarySearch(arr, i, mid-1, key);
      else{
        return binarySearch(arr, mid+1, j, key);
      }
    }
    return -1;
  }

  public static int findPivot(int[] arr){
    for(int i=0; i<arr.length-1; i++ ){
      if(arr[i]>arr[i+1]){
        return i;
      }
    }
    return -1;
  }

}
