#Eli Schmitter
#epschmit
#Reads in an integer and print all the number counting up and down to/from the intgers
	
	li 	$v0 5 			# set v0 to 5
	syscall		  		#read in an integer
	move 	$t0, $v0		#move v0 to t0
	li 	$t2, 0			#set t2 to 0 

up:
	addi	$t2, $t2, 1		#add 1 to t2
	bgt	$t2, $t0, down1 	#go to down if t2>t0
	li 	$v0 1 		
	move    $a0,$t2	
	syscall		  		#print out an integer
	#prints out a space betwen numbers 
	li 	$a0, 32			#space char
	li 	$v0, 11 			 
	syscall				#print out an char
	b	up #go to up
down1:
  	addi 	$a0, $0, 10		
    	addi 	$v0, $0, 11
    	syscall 			#print out a new line
	b down
	
down:
	sub	$t2, $t2, 1		#subrtract one from t2
	beq	$t2, $zero, done	#goto done if t2 = 0
	li 	$v0, 1 			
	move	$a0, $t2		
	syscall		  		#print out an integer
	#prints out a space betwen numbers 
	li 	$a0, 32			#space char
	li 	$v0, 11 			 
	syscall				#print out an char
	b	down
	
done:
	li 	$v0 10
	syscall	
