package algorithm.Greedy;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 */
public class JumpGame55 {
    /**
     * 贪心实质--如果能用所有局部最优的结果是全局最优的，那么就可以使用贪心算法解决问题
     * 例如从一堆钞票中取尽可能多的钱--就可以每一步都取面值最大的钞票最终取得的就是全局最多的钱
     */

    /**
     * 贪心做法
     * 如果一个位置能够到达，那么这个位置左侧所有位置都能到达。
     * 我们依次遍历数组中的每一个位置，并实时维护最远可以到达的位置。
     *
     * 如果某一个作为起跳点的格子可以跳跃的距离是 3，那么表示后面3个格子都可以作为起跳点
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把能跳到最远的距离不断更新
     * 如果可以一直跳到最后，就成功了
     */

    /**
     * 将上一部的判断条件取反
     */
    public boolean canJump1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            //如果当前节点大于左侧所有节点最大能跳至的地方，那么就说明这个节点是个断点
            if(i>rightmost) return false;
            rightmost=Math.max(rightmost,nums[i]+i);
        }
        return true;
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            //如果当前节点在rightmost范围内说明这个节点可以到达，才能继续去维护rightmost
            if (i <= rightmost) {
                //维护rightmost
                rightmost = Math.max(rightmost, i + nums[i]);
                //判断是否能够到达，能就可以中断遍历了
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 我的思路是从倒数第二个节点开始判断这个节点是否能到达最后一个节点，如果可以到达更新节点位置
     * 如果这个节点更新到0就说明从开始索引可以到最后
     */
    public boolean canJumpMy(int[] nums) {
        int n=nums.length;
        if(n==1) return true;
        boolean isCome=false;
        int max=-1;
        for(int i=n-2;i>=0;i--){
            if(nums[i]>=n-1-i){
                isCome=true;
                max=i;
            }else if(nums[i]>=max-i){
                max=i;
            }
        }
        return (max==0&&isCome);
    }
}
