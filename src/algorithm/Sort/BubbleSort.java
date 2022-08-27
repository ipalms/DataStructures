package algorithm.Sort;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = (int) (Math.random() * 20); //生成一个[0, 8000000) 数
        }
        bubbleSort(arr);
        for (int i = 0; i < 20; i++) {
            System.out.print(arr[i]+"\t");
        }
    }

    /**
     * 注意比较的相邻的位置，是稳定的：
     * 冒泡排序--每一轮将最大的值交换到最后，下一轮数据量减一
     * 通过对待排序序列从前向后（从下标较小的元素开始）,依次比较相邻元素的值
     * 若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
     * */
    public static void bubbleSort(int[] arr) {
        int len= arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr,j+1,j);
                }
            }
        }
    }

    /**
     * 优化点--如果有一轮冒泡过程数组数据没有交换证明数组是已排好序的--可break
     * */
    public static void bubbleSort1(int[] arr) {
        int len= arr.length;
        boolean done=true;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr,j+1,j);
                    done=false;
                }
            }
            if(done){
                break;
            }
            done=true;
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
