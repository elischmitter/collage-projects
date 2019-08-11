.text
	li		$t0,100
top:
	add		$t0,$t0,-1
	add		$t0,$t0,-1
	add		$t0,$t0,-1
	add		$t0,$t0,-1
	bgez	$t0,top
done:
	li	$v0,10
	syscall