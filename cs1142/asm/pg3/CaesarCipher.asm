### START DATA ###
.data
# Test 3: "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG" -> "QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD"
shift:  .word    10
first:  .word    node2
        .asciiz  "Don't"
node2:  .word    node3
        .asciiz  "worry,"
node3:  .word    node4
        .asciiz  "Be"
node4:  .word    -1
        .asciiz  ":)"
### END DATA ### 
.text 
	li		$t9, 0			#loop var
	lw		$t1, shift		#shift
	la 		$t0, first		#addres of first word
loop:
	add 	$t2,$t0,$t9		#get the curent index of byte in the word
	lbu		$a0,4($t2)		#load the ascii vall
	beq		$a0,0,next		#if null char goto next
	#if nonletter char skip and goto back
	blt		$a0,65,back 	
	bgt		$a0,91,back
	add		$a0,$a0,$t1		#add shift to the ascii vall
	blt		$a0,65,under	#check if underflow
	bgt		$a0,90,over		#check if overflow
back:
	#print char
	li		$v0,11
	syscall
	addi	$t9,$t9, 1
	#goto loop
	b		loop
#get the next word
next:
	li		$t9, 0			#reset counter 
	#printspace
	li      $v0, 11      	
	addi    $a0, $0, 32  	
	syscall
	
	#get the next node of the linkedlist
	lw		$t0,0($t0)
	
	beq 	$t0,-1 end
	b		loop
	
under:
	#fix underflows
	addi	$t3,$a0,-65
	addi	$a0,$t3,91
	b back
over:
	#fix overflows
	addi	$t3,$a0,-91
	addi	$a0,$t3,65
	b back
	
	
end:
	li		$v0,10
	syscall
	
