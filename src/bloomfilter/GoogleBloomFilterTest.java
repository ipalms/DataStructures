package bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GoogleBloomFilterTest {
    private static int size = 1000000;

    //size设置布隆过滤器大小，可以设置第三个参数--设置错误率
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    private static BloomFilter<Integer> bloomFilter1 = BloomFilter.create(Funnels.integerFunnel(), size, 0.001);

    @Test
    public void test1() {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        long startTime = System.nanoTime(); // 获取开始时间
        //判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            System.out.println("命中了");
        }
        long endTime = System.nanoTime();   // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");
    }

    @Test
    public void test2() {
        for (int i = 0; i < size; i++) {
            bloomFilter1.put(i);
        }
        List<Integer> list = new ArrayList<Integer>(1000);
        // 故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter1.mightContain(i)) {
                list.add(i);
            }
        }
        double a=(list.size()/10000.0);
        System.out.println("误判的数量：" + list.size()+" 误判率"+a);
    }


}
