package main

import (
	"bytes"
	"fmt"
	"strconv"
	"strings"
	"testing"
	"unicode"
)

//字符串拼接
func TestString1(t *testing.T) {
	// 方法一： + 号
	s1 := "字符串"
	s2 := "拼接"
	s3 := s1 + s2
	t.Log(s3) //s3 = "字符串拼接"

	// 方法二：对于纯ASCII字符串，可以使用[]byte类型的切片进行字符串的增删
	// 对于Leetcode编码比较多
	s4 := []byte("abcccddd")
	s4 = append(s4, 'e')
	s4 = append(s4, '.')
	t.Log(string(s4)) //s4 = "abccccddde."

	// 方法三使用fmt.Sprintf()
	s5 := fmt.Sprintf("%s(%s)", s1, s2)
	t.Log(s5) //s5 = 打印 "字符串拼接"

	// 方法四：使用strings.Builder
	s6 := strings.Builder{}
	s6.WriteString(s1)
	s6.WriteString(s2)
	s6.WriteByte('c')
	s6.WriteRune('王')
	t.Log(s6.String()) //s6 = 打印 "字符串拼接"

	// 方法五：使用strings.Join()
	s7 := strings.Join([]string{s1, s2}, "")
	t.Log(s7) //s7 = 打印 "字符串拼接"
}

// 字符串与其他基础类型的相互转化 strconv包下
func TestString2(t *testing.T) {
	//1. 字符串转整数
	s := "123"
	i, err := strconv.Atoi(s)
	if err != nil {
		t.Error(err)
	}
	t.Log(i) //i = 123
	i2, err2 := strconv.ParseInt(s, 10, 64)
	if err2 != nil {
		t.Error(err2)
	}
	t.Log(i2) //i2 = 123

	//2. 整数转字符串
	s1 := strconv.Itoa(i)
	t.Log(s1) //s1 = "123"
	s6 := strconv.FormatInt(int64(i), 10)
	t.Log(s6) //s1 = "123"

	//3. 字符串转浮点数
	s2 := "123.456"
	f, err := strconv.ParseFloat(s2, 64)
	if err != nil {
		t.Error(err)
	}
	t.Log(f) //f = 123.456

	//4. 浮点数转字符串
	d := 2.345
	s3 := strconv.FormatFloat(d, 'f', -1, 64)
	t.Log(s3) //s3 = "2.345"

	//5. 字符串转字节数组  从这往后的是可以直接转化的
	s4 := "abcdefg"
	b := []byte(s4)
	t.Log(b) //b = [97 98 99 100 101 102 103]

	//6. 字节数组转字符串
	s5 := string(b)
	t.Log(s5) //s5 = "abcdefg"

	//7. 字符串转rune数组
	s7 := "中文"
	r := []rune(s7)
	t.Log(r) //r = [中文]

	//8. rune数组转字符串
	s8 := string(r)
	t.Log(s8) //s8 = "中文"

}

// 字符串Join() Spilt()操作 strings包下
// bytes.Join() bytes.Split()
func TestString3(t *testing.T) {
	//1. 字符串Join() strings.Join
	s1 := []string{"a", "b", "c"}
	s2 := strings.Join(s1, ",")
	t.Log(s2) //s2 = "a,b,c"

	//2. 字符串Split() strings.Split()
	s3 := "a,b,c"
	s4 := strings.Split(s3, ",")
	t.Log(s4) //s4 = ["a", "b", "c"]

	//3. bytes.Split()
	b1 := []byte("ad,bc,ce")
	b2 := bytes.Split(b1, []byte(","))
	for _, v := range b2 {
		t.Log(string(v))
	}

	//4. bytes.Join()
	b3 := []byte("adbcce")
	b4 := bytes.Join([][]byte{b3[0:2], b3[2:4], b3[4:]}, []byte(","))
	t.Log(string(b4)) //b4 = "ad,bc,ce"
}

// 字符｜字符串 变成大小写
func TestString4(t *testing.T) {
	s := "AcDB"
	fmt.Println(strings.ToLower(s))
	fmt.Println(strings.ToUpper(s))
	c := 'd'
	c2 := 'D'
	fmt.Println(unicode.ToLower(c2))
	fmt.Println(unicode.ToUpper(c))
	c3 := ' '
	fmt.Println(unicode.IsDigit(c3) || unicode.IsLetter(c3))
}
