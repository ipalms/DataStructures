package main

func checkValidString(s string) bool {
	nStack, sStack := []int{}, []int{}
	for i, v := range s {
		if v == '(' {
			nStack = append(nStack, i)
		} else if v == '*' {
			sStack = append(sStack, i)
		} else {
			if len(nStack) > 0 {
				nStack = nStack[:len(nStack)-1]
			} else if len(sStack) > 0 {
				sStack = sStack[:len(sStack)-1]
			} else {
				return false
			}
		}
	}
	for len(nStack) > 0 && len(sStack) > 0 {
		if nStack[len(nStack)-1] > sStack[len(sStack)-1] {
			return false
		}
		nStack = nStack[:len(nStack)-1]
		sStack = sStack[:len(sStack)-1]
	}
	return len(nStack) == 0
}
