ladders = dict()
snakes = dict()
visited = dict()

def shortestPath(currentpoint, numberOfRolls, endPoint):
    global ladders;
    global snakes;
    global visited;
    
    if currentpoint > endPoint:
        return float('inf')
    if currentpoint is endPoint:
        return numberOfRolls
    
    minimum=float('inf')
    
    for i in range(1,7):
        if visited.get(currentpoint+i) is not None:
            prevRolls, predecessor = visited.get(currentpoint+i)
            if prevRolls > (numberOfRolls+1):
                visited[currentpoint+i] = ((numberOfRolls+1),currentpoint)
                if ladders.get(currentpoint+i) is not None:
                    if visited.get(ladders.get(currentpoint+i)) is not None:
                        prevRolls, predecessor = visited.get(ladders.get(currentpoint+i))
                        if prevRolls > (numberOfRolls+1):
                            visited[ladders.get(currentpoint+i)] = ((numberOfRolls+1),(currentpoint+i))
                if snakes.get(currentpoint+i) is not None:
                    if visited.get(snakes.get(currentpoint+i)) is not None:
                        prevRolls, predecessor = visited.get(snakes.get(currentpoint+i))
                        if prevRolls > (numberOfRolls+1):
                            visited[snakes.get(currentpoint+i)] = ((numberOfRolls+1),currentpoint+i)
            else:
                continue
        if ladders.get(currentpoint+i) is not None:
            visited[currentpoint+i] = ((numberOfRolls+1),currentpoint)
            if visited.get(ladders.get(currentpoint+i)) is not None:
                prevRolls, predecessor = visited.get(ladders.get(currentpoint+i))
                if prevRolls > (numberOfRolls+1):
                    visited[ladders.get(currentpoint+i)] = ((numberOfRolls+1),(currentpoint+i))
            minimum=min(minimum, (shortestPath(ladders.get(currentpoint+i), numberOfRolls+1, endPoint)))
            continue
        if snakes.get(currentpoint+i) is not None:
            visited[currentpoint+i] = ((numberOfRolls+1),currentpoint)
            if visited.get(snakes.get(currentpoint+i)) is not None:
                prevRolls, predecessor = visited.get(snakes.get(currentpoint+i))
                if prevRolls > (numberOfRolls+1):
                    visited[snakes.get(currentpoint+i)] = ((numberOfRolls+1),currentpoint+i)
            minimum=min(minimum, (shortestPath(snakes.get(currentpoint+i), numberOfRolls+1, endPoint)))
            continue
        visited[currentpoint+i] = ((numberOfRolls+1),currentpoint)
        minimum=min(minimum, shortestPath(currentpoint+i, numberOfRolls+1, endPoint))
    return minimum

numberOfLadders = int(raw_input("Enter the number of Ladders : "));
print "Enter the Starting point and the ending point of the ladder : "
for i in range(numberOfLadders):
    start,end = map(int,raw_input().split())
    ladders[start]=end;

numberOfSnakes = int(raw_input("Enter the number of Snakes : "));
print "Enter the Starting point and the ending point of the snake : "
for i in range(numberOfSnakes):
    start,end = map(int,raw_input().split())
    snakes[start]=end;

shortestPath(1, 0, 100)
print "fewest number of die rolls to reach from 1 to 100 is :"
if visited.get(100) is not None:
    rolls, predecessor = visited.get(100)
    print rolls
else:
    print "Not possible to reach 100. No path Exists"