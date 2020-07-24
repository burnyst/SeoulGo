import sys
from datetime import datetime
from dao.placeWordDao import PlaceWordDao
from konlpy.tag import Okt
class PlaceWordCount:
    def __init__(self):
        self.okt = Okt()
        self.dao = PlaceWordDao()
    def __wordCount__(self, text):
        rst = {}
        nouns = self.okt.nouns(text)
        for n in nouns:
            if n not in ['나', '내', '제', '이', '이것', '이곳',
                         '저', '저것', '저곳', '그', '그것', '그곳', '곳', '것',
                         '때', '및', '를', '더', '수', '후']:
                if n not in rst:
                    rst[n] = 0
                rst[n] = rst[n] + 1
        # rst = sorted(words.items(), key=lambda x: x[1], reverse=True)
        return rst
    def countList(self, startDate, endDate):
        reviewList = self.dao.getReviewList(startDate, endDate)
        saveList = []
        for i in reviewList:
            data = self.__wordCount__(i["content"])
            for key, val in data.items():
                saveList.append({"placeNo": i["placeNo"], "word": key, "freq": val})
        self.dao.insertList(saveList)
    def count(self, reviewNo):
        review = self.dao.getReview(reviewNo)
        saveList = []
        data = self.__wordCount__(review["content"])
        for key, val in data.items():
            saveList.append({"placeNo": review["placeNo"], "word": key, "freq": val})
        self.dao.insertList(saveList)

if __name__ == '__main__':
    cnt = len(sys.argv)
    wc = PlaceWordCount()
    if cnt == 2:
        # wc.count(982)
        wc.count(int(sys.argv[1]))
    elif cnt == 3:
    #     startDate = datetime.strptime('1900-01-01', '%Y-%m-%d')
    #     endDate = datetime.strptime('2009-01-01', '%Y-%m-%d')
        startDate = datetime.strptime(sys.argv[1], '%Y-%m-%d')
        endDate = datetime.strptime(sys.argv[2], '%Y-%m-%d')
        wc.countList(startDate, endDate)
    else:
        print('require parameter. it is rno or stareDate and endDate.')

