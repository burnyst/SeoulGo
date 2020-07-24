import logging
from db.oracleDB import OracleDB


class PlaceWordDao:
    def __init__(self):
        self.logger = logging.getLogger("PlaceWordDao")
        self.logger.setLevel(logging.INFO)
        self.logger.addHandler(logging.StreamHandler())
        handler = logging.FileHandler("PlaceWordDao.log")
        handler.setFormatter(logging.Formatter('%(asctime)s - %(levelname)s - %(message)s'))
        self.logger.addHandler(handler)
        self.db = OracleDB(host="192.168.56.83", user="seoulmate", password="seoulgo")
        self.db.connect()
    def __del__(self):
        self.db.disconnect()
    def __getFreq__(self, placeNo, word):
        rst = None
        sql = """select freq from placeWord where placeNo=:placeNo and word=:word"""
        cur = self.db.execute(sql, placeNo=placeNo, word=word)
        for i in cur:
            rst = i[0]
            break
        return rst
    def getReview(self, rno):
        rst = None
        sql = """select placeNo, rtitle, rcontent
                from reviewboard
                where rno=:rno"""
        cur = self.db.execute(sql, rno=rno)
        for i in cur:
            rst = {"placeNo": i[0], "title": i[1], "content": i[2]}
            break
        return rst
    def getReviewList(self, startDate, endDate):
        rst = []
        sql = """select placeNo, rtitle, rcontent
        from reviewboard
        where rcontent not like '%더 보기%' AND (rdate between :startDate and :endDate)"""
        cur = self.db.execute(sql, startDate=startDate, endDate=endDate)
        for i in cur:
            rst.append({"placeNo": i[0], "title": i[1], "content": i[2]})
        return rst
    def insertList(self, list):
        insertSql = """insert into placeWord (placeNo, word, freq) values (:placeNo, :word, :freq)"""
        updateSql = """update placeWord set freq=:freq where placeNo=:placeNo and word=:word"""
        for i in list:
            freq = self.__getFreq__(i['placeNo'], i['word'])
            try:
                if freq is None:
                    self.db.execute_dml(insertSql, placeNo=i['placeNo'], word=i['word'], freq=i['freq'])
                else:
                    self.db.execute_dml(updateSql, placeNo=i['placeNo'], word=i['word'], freq=i['freq']+freq)
            except Exception as e:
                self.logger.error(e, 'placeNo='+str(i['placeNo'])+', word='+i['word']+', freq='+str(i['freq']))
        try:
            self.db.commit()
        except Exception as e:
            for i in list:
                self.logger.error(e, 'placeNo=' + str(i['placeNo']) + ', word=' + i['word'] + ', freq=' + str(i['freq']))