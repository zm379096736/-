# 偏差
def _deviation(predict, real, kind):
    # 如果type是轉發的話 t = 5 其餘為3
    t = 5.0 if kind=='f' else 3.0
    return abs(predict - real) / (real + t)

# 計算第i篇博文的準確率
def _precision_i(fp, fr, cp, cr, lp, lr):
    return 1 - 0.5 * _deviation(fp, fr, 'f') - 0.25 * _deviation(cp, cr, 'c') - 0.25 * _deviation(lp, lr, 'l')

# 符号
def _sgn(x):
    return 1 if x>0 else 0

# 计数
def _count_i(fr, cr, lr):
    x = fr + cr + lr
    return 101 if x>100 else (x+1)

# 計算整體的準確率
def precision(real_and_predict):    
    # 分子分母
    numerator,denominator = 0.0,0.0        
    for  fr, cr, lr,fp, cp, lp in real_and_predict:
        numerator += _count_i(fr, cr, lr) * _sgn(_precision_i(fp, fr, cp, cr, lp, lr) - 0.8)
        denominator += _count_i(fr, cr, lr)
    return numerator / denominator
