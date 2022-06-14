package main

import (
	"fmt"
	"math/rand"
	"testing"
	"time"
)

// "math/rand" 包实现了伪随机数生成器。也就是生成 整形和浮点型。
//　该包中根据生成伪随机数是是否有种子(可以理解为初始化伪随机数)，可以分为两类：
//　1、有种子。通常以时钟，输入输出等特殊节点作为参数，初始化。该类型生成的随机数相比无种子时重复概率较低。
//　2、无种子。可以理解为此时种子为1， Seek(1)
func TestRandom(t *testing.T) {
	// rand.New(rand.NewSource(time.Now().UnixNano())) 生成一个rand.Rand类型的实例，seed为当时的种子
	// rand.Seed(time.Now().UnixNano()) 为rand的global Rand实例注入时间戳作为种子
	for i := 0; i < 10; i++ {
		r := rand.New(rand.NewSource(time.Now().UnixNano()))
		fmt.Printf("%d ", r.Int31())
	}
	fmt.Println("")

	// 使用的是默认的Global Rand实例
	rand.Seed(time.Now().UnixNano())
	for i := 0; i < 10; i++ {
		fmt.Printf("%d ", rand.Int31())
	}

	// 生成0-9的随机数
	fmt.Println(rand.Int31n(10))
}

// 生成时间戳随机种子
func TestRandom2(t *testing.T) {
	rand.Seed(time.Now().UnixNano())
	for i := 0; i < 10; i++ {
		fmt.Printf("%d ", rand.Int31())
	}
}

// 没有随机种子的情况，run两次的结果一样
func TestRandom3(t *testing.T) {
	for i := 0; i < 10; i++ {
		fmt.Printf("%d ", rand.Int31())
	}
}
