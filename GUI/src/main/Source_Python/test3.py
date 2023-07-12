import pathlib 
import os

path = pathlib.Path(__file__).parent.resolve()

p = pathlib.PureWindowsPath(path)
print(len(p.parents))

for i in range()