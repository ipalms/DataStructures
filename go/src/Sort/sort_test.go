package Sort

import (
	"fmt"
	"sort"
	"testing"
)

// sort.Ints() 测试
func TestSort(t *testing.T) {
	// 对int切片快速排序
	i := []int{3, 2, 1, 12, 32, 5, 3}
	sort.Ints(i)
	fmt.Println(i) // 结果：[1 2 3 3 5 12 32]

	// 对float64切片快速排序
	f := []float64{0.6, 9.4, 0.4, 1.2, 3.1}
	sort.Float64s(f)
	fmt.Println(f) // 结果：[0.4 0.6 1.2 3.1 9.4]

	// 对string切片快速排序
	s := []string{"a", "A", "AA", "aa", "Aa", "aA", "AAA", "AAa", "b", "bA", "abA"} // string类型比较特殊，这里我们的数据也设置的比较特殊，看sort包对它们是怎么排序的
	sort.Strings(s)
	// 结果：[A AA AAA AAa Aa a aA aa abA b bA]，可以看到，对string类型排序，比较的是字符的ASCII表
	fmt.Println(s)
}

// sort.IntSlice 测试
func TestSort2(t *testing.T) {
	var s sort.IntSlice = []int{1, 4, 2, 6, 4, 3} // 声明一个IntSlice变量
	//也可以这样包装一个sort.IntSlice对象
	//nums := []int{1, 4, 2, 6, 4, 3}
	//b := sort.IntSlice(nums)
	//sort.Sort(sort.Reverse(b))
	fmt.Println(s.Len())          // 打印长度 6
	fmt.Println(sort.IsSorted(s)) // sort方法：判断切片类型的数据是不是有序的, 这里打印false
	s.Sort()                      // 进行排序
	fmt.Println(sort.IsSorted(s)) // 打印true，上一步已经排好序了
	fmt.Println(s)                // 打印 [1 2 3 4 4 6]

	// 搜索切片中的值，插一句，go在这里的搜索方法底层用的二分查找法，感兴趣的读者可以去看源码
	fmt.Println(s.Search(4)) // 打印 3
	fmt.Println(s.Search(2)) // 打印 1
	fmt.Println(s.Search(5)) // 打印 5， s中没有5，但按顺序，5应该在4和6之间，所有返回索引5
}

// 自定义结构体排序
// 声明一个person类型结构体
type person struct {
	id   int
	name string
}

// 声明一个person切片类型别名
type ps []person

func (p ps) Len() int { // 实现len方法
	return len(p)
}

func (p ps) Swap(i, j int) { // 实现Swap方法，交换i，j的位置
	p[i], p[j] = p[j], p[i]
}

// 实现Less方法，排序方法(Sort)主要就是根据这个方法来进行数据大小的比较，我们根据p的id大小比较
func (p ps) Less(i, j int) bool {
	return p[i].id < p[j].id
}

// 对自定义结构体排序测试
func TestSort3(t *testing.T) {
	// 声明一个person切片
	var s ps = []person{{1, "go"}, {7, "java"}, {3, "js"}, {2, "c"}}
	fmt.Println(sort.IsSorted(s)) // 打印 false
	// 对它进行排序
	sort.Sort(s)
	fmt.Println(sort.IsSorted(s)) // 打印 true
	fmt.Println(s)                // 打印 [{1 go} {2 c} {3 js} {7 java}]
}

// 测试反转排序，降序排序
type s1 struct {
	sort.IntSlice
}

func (p s1) Less(i, j int) bool {
	return p.IntSlice[i] > p.IntSlice[j]
}

func TestSort4(t *testing.T) {
	nums := []int{1, 4, 2, 6, 4, 3}
	b := sort.IntSlice(nums)
	sort.Sort(sort.Reverse(b))
	fmt.Println(nums) //[6 4 4 3 2 1]

	//第二种,构造结构体覆写Less方法实现降序排列
	nums2 := []int{1, 4, 2, 6, 4, 3}
	b2 := s1{nums2}
	sort.Sort(b2)
	fmt.Println(nums2) //[6 4 4 3 2 1]
}
