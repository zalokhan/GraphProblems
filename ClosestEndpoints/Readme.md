#Closest Endpoints

Finds the closest Endpoint in the network.
X	:	Endpoint
O 	:	Open Node
F 	:	Failed Node

All the open nodes need to point to the closest Endpoint or to the open node
which points to the closest endpoint.

The open node needs to point to the endpoint in the following way.

X 		=>		X
O 				^

X O 	=>		X <

O X 	=> 		> X

O 		=>		v
X 				X

If there is no possible path to the endpoint:

F F F 			F F F
F O F 	=>		F ? F
F F F 			F F F

If the open node has multiple paths to endpoints near it:
1)	It needs to choose the shortest path.
2)	The Order of priority to break ties in case of multiple paths of same 
	length is : up (^), right (>), down (v) and left (<).

Example:

x f f f f f f f f f
o f o o o f o o o o
o f o f o f o o f f
o f o f o f o o f o
o o o f o o o f o o
f f f o f f f o f f
x o o o o x o o o o
x x x x x o o o o o
f f f f o o f o f o
x o x f o o o o o x

Output:

x f f f f f f f f f 
^ f v < < f v v < < 
^ f v f ^ f v v f f 
^ f v f ^ f v < f ? 
^ < < f ^ < < f ? ? 
f f f v f f f v f f 
x v v v > x < < < v 
x x x x x ^ ^ ^ > v 
f f f f ^ ^ f v f v 
x > x f ^ ^ > > > x 