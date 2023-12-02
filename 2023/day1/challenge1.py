import re

numbers = []
with open("input.txt", 'r') as file:
    for i in file:
        n = re.findall(r'\d', i)
        if len(n) >= 1:
            numbers.append(f"{n[0]}{n[-1]}")

print(sum(int(n) for n in numbers))