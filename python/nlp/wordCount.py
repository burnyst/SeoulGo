import argparse
from datetime import datetime, date
from dao.reviewWordDao import ReviewWordDao
from konlpy.tag import Okt
class PlaceWordCount:
    def __init__(self):
        self.okt = Okt()
        self.dao = ReviewWordDao()
        self.ruleout = []
        with open('ruleout.txt', 'r', encoding='utf-8') as f:
            self.ruleout = f.read().replace(' ', '').split()
    def __wordCount__(self, text):
        rst = {}
        nouns = self.okt.nouns(text)
        for n in nouns:
            if n not in self.ruleout:
                if n not in rst:
                    rst[n] = 0
                rst[n] = rst[n] + 1
        # rst = sorted(words.items(), key=lambda x: x[1], reverse=True)
        return rst
    def countList(self, startDate, endDate):
        reviewList = self.dao.getReviewList(startDate, endDate)
        saveList = []
        for i in reviewList:
            self.dao.delete(i["rNo"])
            if i["rIsShow"] == 1:
                data = self.__wordCount__(i["rContent"])
                for key, val in data.items():
                    saveList.append({"rNo": i["rNo"], "word": key, "freq": val})
        self.dao.insertList(saveList)
    def count(self, rNo):
        review = self.dao.getReview(rNo)
        if review is not None:
            self.dao.delete(rNo)
            if review["rIsShow"] == 1:
                saveList = []
                data = self.__wordCount__(review["rContent"])
                for key, val in data.items():
                    saveList.append({"rNo": review["rNo"], "word": key, "freq": val})
                self.dao.insertList(saveList)
def main():
    target = ['review']
    parser = argparse.ArgumentParser()
    parser.add_argument('-t', '--target', type=str, default='review', choices=[target], help="word count target.")
    parser.add_argument('-k', '--key', type=int, help="key value.")
    parser.add_argument('-s', '--start', default=date.today(), type=lambda x: datetime.strptime(x, '%Y-%m-%d').date(), help="start date. date in the format yyyy-mm-dd")
    parser.add_argument('-e', '--end', default=date.today(), type=lambda x: datetime.strptime(x, '%Y-%m-%d').date(), help="end date. date in the format yyyy-mm-dd")
    args = parser.parse_args()
    if args.target == 'review':
        wc = PlaceWordCount()
        if args.key != None:
            # wc.count(982)
            wc.count(args.key)
        else:
            # startDate = datetime.strptime('1900-01-01', '%Y-%m-%d')
            # endDate = datetime.strptime('2009-01-01', '%Y-%m-%d')
            wc.countList(args.start, args.end)
if __name__ == '__main__':
    main()

