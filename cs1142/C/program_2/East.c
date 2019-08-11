/********************************************************
*Name: Eli Schmitter
*email: epschmit@mtu.edu
*This program takes a input and uses a greedy algorithm to move left 
*********************************************************/
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
	int maxX=0;
	int maxY=0;
	double avg=0;
	int min=INT_MAX;
	int max=INT_MIN;
	int sum=0;
	if(scanf("%d %d",&maxX,&maxY)==0)
	return 1;
	int numPoits=maxX*maxY;
	int graph[maxX][maxY];
	int path[maxX][maxY];
	int cost[maxY];
	for(int y=0; y<maxY; y++){
		for(int x=0;x<maxX; x++){
			scanf("%d ",&graph[x][y]);
			if (graph[x][y]> max)
			max=graph[x][y];
			if (graph[x][y]< min)
			min=graph[x][y];
			sum=sum+ graph[x][y];
		}
	}
	avg = sum/((double)numPoits);
	printf("Data points: %d\nAvg elevation: %.2f\nMin elevation: %d\nMax elevation: %d\n",numPoits,avg,min,max);

	for(int row=0;row<maxY;row++){
		path[0][row]=row;
		cost[row]=0;
		for(int coll=1;coll<maxX;coll++){
			path[coll][row]=path[coll-1][row];
			for(int y=path[coll-1][row]-1;y<path[coll-1][row]+2&&y<maxY;y++){
				while (y<0)
				y++;
				//printf(" -%d- ",y);
				int pastVal=graph[coll-1][path[coll-1][row]]-graph[coll][path[coll][row]];
				int newVal=graph[coll-1][path[coll-1][row]]-graph[coll][y];
				if(abs(newVal)==abs(pastVal)){
					path[coll][row]=((y!=path[coll-1][row]&&path[coll][row]!=path[coll-1][row])?path[coll-1][row]+1:path[coll-1][row]);
				}else if (abs(newVal)<abs(pastVal))
				path[coll][row]=y;

			}
		}
		if(argc==2&&atoi(argv[1])!=0){
			for(int coll=0;coll<maxX;coll++){
				printf(" (%d,%d) %d",coll,path[coll][row],graph[coll][path[coll][row]]);
				if(coll != maxX-1){ 	
					if (path[coll][row]<path[coll+1][row])
					printf(" D,");
					else if (path[coll][row]==path[coll+1][row])
					printf(" F,");
					else
					printf(" U,");
				}
			}
		}
		if(argc==2&&atoi(argv[1])!=0){
			for(int i=1;i<maxX;i++){
				int past=graph[i-1][path[i-1][row]];
				int d=abs(past-graph[i][path[i][row]]);
				cost[row]=cost[row]+d;
			}
			printf(", cost %d",cost[row]);
			printf("\n");
		}else{
			for(int i=1;i<maxX;i++){
				int past=graph[i-1][path[i-1][row]];
				int d=abs(past-graph[i][path[i][row]]);
				cost[row]=cost[row]+d;
			}
		}
	}
	int mincost=0;
	for(int i=0;i<maxY;i++){
		mincost=(cost[mincost]>cost[i])? i : mincost;
	}
	printf("Best: row %d, cost %d\n",mincost,cost[mincost]);
	return 0;
}