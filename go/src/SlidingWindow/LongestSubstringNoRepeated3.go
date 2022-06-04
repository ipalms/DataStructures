package main

import "fmt"

func main() {
	fmt.Print(lengthOfLongestSubstring("aabcada"))
}

func lengthOfLongestSubstring(s string) int {
	left,right,length,max:=0,0,len(s),0
	dict:=[128]int{}
	for right<length{
		dict[s[right]]++
		for dict[s[right]]>1{
			if right-left>max{
				max=right-left
			}
			dict[s[left]]--
			left++
		}
		right++
	}
	if right-left>max{
		max=right-left
	}
	return max
}

func lengthOfLongestSubstring1(s string) int {
	left,right,length,max,dict:=0,0,len(s),0,make(map[byte]int)
	for right<length{
		dict[s[right]]++
		for dict[s[right]]>1{
			dict[s[left]]--
			left++
		}
		if right-left+1>max{
			max=right-left+1
		}
		right++
	}
	return max
}
