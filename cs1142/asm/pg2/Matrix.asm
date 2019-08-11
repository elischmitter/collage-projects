#Eli Schmitter
#epschmit
#this will make an 2d array baced on user input
	
	li 		$v0 5 			# set v0 to 5
	syscall		  		#read in an integer
	move 	$t0, $v0		#move v0 to t0
	bgt  	$t0, 40, end 
	blt  	$t0, 0,  end
loop:

	li 		$v0 5 			# set v0 to 5
	syscall		  			#read in an integer
	move 	$t1, $v0		#move v0 to t0
	bge  	$t1, $t0, done  #if t1 >= t0 branch to done
	blt  	$t1, 0,   done	#if t1 < 0 branch to done
	
	li 		$v0 5 			# set v0 to 5
	syscall		  			#read in an integer
	move 	$t2, $v0		#move v0 to t0
	bge  	$t2, $t0, done 	#if t1 >= t0 branch to done
	blt  	$t2, 0,   done	#if t1 < 0 branch to done
	
	# get the address of the arr from the cords(add=((2^2)*t0)*t1+(2^2)*t2)
	sll 	$t7,$t0, 1		#(2^2)*t0
	mult	$t1, $t7		#(2^2)*t0*t1
	mflo	$t1
	sll		$t2, $t2,1	#4*t2
	add 	$t3,$t1, $t2	#(2^2)*t0)*t1+(2^2)*t2)

	
	li		$t5, 1
	sb   	$t5, nums($t3)  # nums[i] <- val
	addi 	$t1, $t1, 1     # i++
	b    	loop	
	
done:
	li $t1 -1
printloop:
	addi	$t1,$t1, 1		#adds one to t1
	li 		$t2, 0			#sets t2 =0
	#prints a new line 
	addi 	$a0, $0, 10		
   	addi 	$v0, $0, 11
    syscall					#print out a new line
    bge		$t1,$t0 end		#goto end if end of prgram
    
printhelp:
# get the address of the arr from the cords(add=((2^2)*t0)*t1+(2^2)*t2)
    li      $t5,0 
    sll     $t7,$t0, 1
    mult    $t1, $t7
    mflo    $t8
    sll     $t9 $t2,1
    add     $t3,$t8 $t9

	lb   	$t5, nums($t3)  # nums[i] <- val
	xor		$t5,$t5, 0x0001
	li 		$v0, 1		#int
	move 	$a0, $t5		 
	syscall				#print out an int 
	
	addi	$t2, $t2, 1	
	bge 	$t2, $t0 printloop
	b		printhelp
end:
	li 		$v0, 10
	syscall
	
	.data
	.align	2
nums:	.space 1600
