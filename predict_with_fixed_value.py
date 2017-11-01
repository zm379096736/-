import pandas as pd
from genUidStat import loadData,genUidStat
from evaluation import precision
from runTime import runTime



# 固定的值 如 0 0 0， 0 1 1等 測試用
@runTime
def predict_with_fixed_value(forward,comment,like,submission=True):
	# type check
	if isinstance(forward,int) and isinstance(forward,int) and isinstance(forward,int):
		pass
	else:
		raise TypeError("forward,comment,like should be type 'int' ")
	
	traindata,testdata = loadData()
	
	# score on the training set
	train_real_pred = traindata[['forward','comment','like']]
	train_real_pred['fp'],train_real_pred['cp'],train_real_pred['lp'] = forward,comment,like
	print ("Score on the training set:{0:.2f}%".format(precision(train_real_pred.values)*100))
	
	# predict on the test data with fixed value, generate submission file
	if submission:
		test_pred = testdata[['uid','mid']]
		test_pred['fp'],test_pred['cp'],test_pred['lp'] = forward,comment,like
		
		result = []
		filename = "weibo_predict_{}_{}_{}.txt".format(forward,comment,like)
		for _,row in test_pred.iterrows():
			result.append("{0}\t{1}\t{2},{3},{4}\n".format(row[0],row[1],row[2],row[3],row[4]))
		f = open(filename,'w')
		f.writelines(result)
		f.close()
		print ('generate submission file "{}"'.format(filename))
        
# 統計方法 如中位數等
@runTime	
def predict_with_stat(stat="median",submission=True):
	"""
	stat:
		string
		min,max,mean,median
	"""
	stat_dic = genUidStat()
    
    # 載入資料 并設定欄位
	traindata,testdata = loadData()
	
	# get stat for each uid
    
	forward,comment,like = [],[],[]
	for uid in traindata['uid']:
		if uid in stat_dic:
			forward.append(int(stat_dic[uid]["forward_"+stat]))
			comment.append(int(stat_dic[uid]["comment_"+stat]))
			like.append(int(stat_dic[uid]["like_"+stat]))
		else:
			forward.append(0)
			comment.append(0)
			like.append(0)
            
	# score on the training set
	train_real_pred = traindata[['forward','comment','like']]
	train_real_pred['fp'],train_real_pred['cp'],train_real_pred['lp'] = forward,comment,like
	print ("Score on the training set:{0:.2f}%".format(precision(train_real_pred.values)*100))	
	#predict on the test data with fixed value, generate submission file
	
	if submission:
		test_pred = testdata[['uid','mid']]
		forward,comment,like = [],[],[]
		for uid in testdata['uid']:
			if uid in stat_dic: 
				forward.append(int(stat_dic[uid]["forward_"+stat]))
				comment.append(int(stat_dic[uid]["comment_"+stat]))
				like.append(int(stat_dic[uid]["like_"+stat]))
			else:
				forward.append(0)
				comment.append(0)
				like.append(0)
				
				
		test_pred['fp'],test_pred['cp'],test_pred['lp'] = forward,comment,like
		
		result = []
		filename = "weibo_predict_{}.txt".format(stat)

		for _,row in test_pred.iterrows():
			result.append("{0}\t{1}\t{2},{3},{4}\n".format(row[0],row[1],row[2],row[3],row[4]))
		f = open(filename,'w')
		f.writelines(result)
		f.close()
		print ('generate submission file "{}"'.format(filename))


if __name__ == "__main__":
	 #predict_with_fixed_value(0,1,0,submission=True)
	 predict_with_stat(stat="median",submission=True)
	 