package algorithm;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class test {
//    public static void main(String[] args) throws Exception {
//        Math.sqrt()
//        System.out.println(countFiles("D:\\IdeaProject\\DataStructures\\src\\algorithm"));
//    }

    public static void main(String[] args) {

    }

    public static int countFiles(String pathStr) throws Exception {
        AtomicInteger count = new AtomicInteger(0);
        Path root = Paths.get(pathStr);
        Files.walkFileTree(root,new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(file.toString().endsWith(".java")&&!file.toString().contains("test")){
                    count.incrementAndGet();
                }
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
        });
        return count.get();
    }

    private static void swap(int []nums,int a,int b){
        int tep=nums[a];
        nums[a]=nums[b];
        nums[b]=tep;
    }


    public static void mergeSort(int []nums,int left,int right,int []temp){
        if(left>=right) return;
        int mid=left+((right-left)>>1);
        mergeSort(nums,left,mid,temp);
        mergeSort(nums,mid+1,right,temp);
        if(nums[mid]<=nums[mid+1]) return;
        merge(nums,left,mid,right,temp);
    }

    private static void merge(int []nums,int left,int mid,int right,int []temp){
        System.arraycopy(nums,left,temp,left,right-left+1);
        int r=mid+1,k=left;
        while(left<=mid&&r<=right){
            if(temp[left]<=temp[r]){
                nums[k++]=temp[left++];
            }else{
                nums[k++]=temp[r++];
            }
        }
        while(left<=mid){
            nums[k++]=temp[left++];
        }
        while(r<right){
            nums[k++]=temp[r++];
        }
    }



    private static void heapSort(int []nums){
        int len=nums.length;
        for(int i=len/2-1;i>=0;--i){
            adjustHeap(nums,i,len);
        }
        for(int i=len-1;i>=1;--i){
            swap(nums,0,i);
            adjustHeap(nums,0,i);
        }
    }

    private static void adjustHeap(int []nums,int i,int end){
        int temp=nums[i];
        for(int k=2*i+1;k<end;k=2*k+1){
            if(k+1<end&&nums[k]<nums[k+1]){
                ++k;
            }
            if(nums[k]>temp){
                nums[i]=nums[k];
                i=k;
            }else{
                break;
            }
        }
        nums[i]=temp;
    }
}
