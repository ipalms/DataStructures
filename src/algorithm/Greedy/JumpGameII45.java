package algorithm.Greedy;

/**
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * 提示:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 */
public class JumpGameII45 {

    /**
     * 贪心做法
     * 贪心的进行正向查找，每步都查找当前能够达到的最大位置
     * 例如，对于数组 [2,3,1,2,4,2,3]，初始位置是下标 0，从下标0出发，最远可到达下标 2。
     * 下标0可到达的位置中，下标1的值是 3，从下标1出发可以达到更远的位置，因此第一步到达下标1。
     * 从下标1出发，最远可到达下标4。下标1可到达的位置中，下标4的值是4 ，从下标4出发可以达到更远的位置，因此第二步到达下标4。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)。
     */
    public static void main(String[] args) {
        System.out.println(jump(new int[]{1,1,1,1}));
    }

    public int jump3(int[] nums) {
        int step=0,start=0,maxReach=nums[0],len=nums.length;
        if(len==1) return 0;
        //i<len --较代码1不同的点
        for(int i=0;i<len;++i){
            maxReach=Math.max(i+nums[i],maxReach);
            //较代码1不同的点
            if(maxReach>=len-1) return step+1;
            if(i==start){
                ++step;
                start=maxReach;
            }
        }
        return step;
    }

    public static int jump(int[] nums) {
        int length = nums.length;
        //上次跳跃可达范围右边界（下次的最右起跳点）
        int end = 0;
        //目前能跳到的最远位置
        int maxPosition = 0;
        //跳跃次数
        int steps = 0;
        //循环的范围为最后一个元素前一个元素，因为题目能保证在访问最后一个元素之前，我们的边界一定大于等于最后一个位置
        //如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素。
        for (int i = 0; i < length - 1; i++) {
            //维护当前这一跳范围内下一跳可到达的最大位置
            //更新，当前跳的最大范围要在后面判断i==end操作之前，这样才能确保end能被正确赋值
            maxPosition = Math.max(maxPosition, i + nums[i]);
            //到达这次跳跃能到达的右边界了
            //如果i==n-2时能进入这个分支，说明前面n-3步最大只能跳至n-2这个位置，那么自然结果还需要+1
            //如果进入不了这个分支（即i已经等于n-1外循环停下），说明step步能跳至最后n-1这个位置
            if (i == end) {
                //目前能跳到的最远位置变成了下次起跳位置的有边界
                end = maxPosition;
                //步数加一
                steps++;
            }
        }
        return steps;
    }


    /**
     * 反向查找出发位置：
     * 我们的目标是到达数组的最后一个位置，因此我们可以考虑最后一步跳跃前所在的位置
     * 该位置通过跳跃能够到达最后一个位置。如果有多个位置通过跳跃都能够到达最后一个位置，
     * 我们可以「贪心」地选择距离最后一个位置最远的那个位置，也就是对应下标最小的那个位置。
     * 因此，我们可以从左到右遍历数组，选择第一个满足要求的位置。
     * 找到最后一步跳跃前所在的位置之后，我们继续贪心地寻找倒数第二步跳跃前所在的位置
     * 以此类推，直到找到数组的开始位置。
     * 时间复杂度：O(n^2)。有两层嵌套循环，在最坏的情况下，例如数组中的所有元素都是1，position需要遍历数组中的每个位置，对于position的每个值都有一次循环。
     * 空间复杂度：O(1)。
     */
    public int jump2(int[] nums) {
        //反向的从最后一个元素开始
        int position = nums.length - 1;
        //计算步数
        int steps = 0;
        while (position > 0) {
            //从前往后的找第一个步数能够到达指定position的节点
            for (int i = 0; i < position; i++) {
                //找到第一个能够到达指定position的节点，更新position以及步数加一
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
