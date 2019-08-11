#Eli Schmitter
#epschmit
#Reads in an integer and print all the numbers less then it and what they aredivisble by.
	
	li 	$v0 5 			# set v0 to 5
	syscall		  		#read in an integer
	move 	$t0, $v0		#move v0 to t0
	li 	$t1, 1			#set t1 to 0
	li 	$t2, 0
	 	
start:
	addi 	$t2 $t2 1
	
	div  	$t2 $t1			#integer devstion
	mfhi	$t3			#get the remander of t2/t1 => t2%t1
	bne	$t3, $zero, addd	#goto addd if t2 % t1 ~= 0
	
	bgt	$t2, $t0, change	#goto change if t2 > t0
	li 	$v0 1 		
	move    $a0,$t2	
	syscall		  		#print out an integer
	#prints out a space betwen numbers 
	li 	$a0, 32			#space char
	li 	$v0, 11 			 
	syscall				#print out an char
	addi	$t3, $t3, 1	
	b	start
	
addd:
	addi	$t3, $t3, 1		#adds one to t3
	b	start			#go to start
	
change:
	add	$t1, $t1, 1		#adds one to t2 
	li	$t2, 0
	bgt	$t1, $t0, done
	addi 	$a0, $0, 10		
   	addi 	$v0, $0, 11
    	syscall 			#print out a new line
	b	start
done:
	li 	$v0, 10
	syscall	
