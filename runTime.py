def runTime(func):
	def rtime(*args,**kwargs):
		import time
		t1 = time.time()
		func(*args,**kwargs)
		t2 = time.time()
		print ("{0} run time: {1:.2f}s".format(func.__name__,t2-t1))
	return rtime
