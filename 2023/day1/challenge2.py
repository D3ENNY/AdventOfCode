cnt = 0
numDic = {
    "one" : 1,
    "two" : 2,
    "three" : 3,
    "four" : 4,
    "five" : 5,
    "six" : 6,
    "seven" : 7,
    "eight" : 8,
    "nine" : 9
}
with open("input.txt", 'r') as file:
    for line in file:
      n = []
      for i,c in enumerate(line):
        if c.isdigit():
          n.append(c)
        for val in numDic.keys():
          if line[i:].startswith(val):
            n.append(str(numDic[val]))
      cnt += int(n[0]+n[-1])

print(cnt)