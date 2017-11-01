import csv
import _pickle as cPickle
import pandas as pd

def loadData():
    traindata = pd.read_csv("weibo_train_data.txt",header=None,sep='\t')
    traindata.columns = ["uid","mid","date","forward","comment","like","content"]

    testdata = pd.read_csv("weibo_predict_data.txt",header=None,sep='\t')
    testdata.columns=["uid","mid","date","content"]

    return traindata,testdata

# 為每個uid生成轉發、評論、贊的資料
def genUidStat():
    # 讀入資料
    traindata, _ = loadData()        
    # 根據uid進行分組
    train_stat = traindata[['uid','forward','comment','like']].groupby('uid').agg(['min','max','median','mean'])        
    train_stat.columns = ['forward_min','forward_max','forward_median','forward_mean',\
                          'comment_min','comment_max','comment_median','comment_mean',\
                          'like_min','like_max','like_median','like_mean']
    train_stat = train_stat.apply(pd.Series.round)        # 將小數舍入               
    # store into dictionary,for linear time search
    stat_dic = {}

    for uid,stats in train_stat.iterrows():        #將資料存為字典
        stat_dic[uid] = stats   # type(stats) : pd.Series
    return stat_dic
    
    





