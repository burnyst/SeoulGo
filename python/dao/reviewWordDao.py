import logging
from db.oracleDB import OracleDB


class ReviewWordDao:
    def __init__(self):
        self.logger = logging.getLogger("ReviewWordDao")
        self.logger.setLevel(logging.INFO)
        self.logger.addHandler(logging.StreamHandler())
        handler = logging.FileHandler("ReviewWordDao.log")
        handler.setFormatter(logging.Formatter('%(asctime)s - %(levelname)s - %(message)s'))
        self.logger.addHandler(handler)
        self.db = OracleDB(host="192.168.56.83", user="seoulmate", password="seoulgo")
        self.db.connect()
    def __del__(self):
        self.db.disconnect()
    def __getFreq__(self, rNo, word):
        rst = None
        sql = """select freq from reviewWord where rNo=:rNo and word=:word"""
        cur = self.db.execute(sql, rNo=rNo, word=word)
        for i in cur:
            rst = i[0]
            break
        return rst
    def getReview(self, rNo):
        rst = None
        sql = """SELECT rNo, rTitle, rContent, rIsShow
                FROM reviewBoard
                WHERE rNo=:rNo"""
        cur = self.db.execute(sql, rNo=rNo)
        for i in cur:
            rst = {"rNo": i[0], "rTitle": i[1], "rContent": i[2], "rIsShow": i[3]}
            break
        return rst
    def getReviewList(self, startDate, endDate):
        rst = []
        sql = """SELECT rNo, rTitle, rContent, rIsShow
        FROM reviewBoard
        WHERE rContent not like '%더 보기%' AND (rDate between :startDate and :endDate)"""
        cur = self.db.execute(sql, startDate=startDate, endDate=endDate)
        for i in cur:
            rst.append({"rNo": i[0], "rTitle": i[1], "rContent": i[2], "rIsShow": i[3]})
        return rst
    def insertList(self, list):
        insertSql = """insert into reviewWord (rNo, word, freq) values (:rNo, :word, :freq)"""
        updateSql = """update reviewWord set freq=:freq where rNo=:rNo and word=:word"""
        for i in list:
            freq = self.__getFreq__(i['rNo'], i['word'])
            try:
                if freq is None:
                    self.db.execute_dml(insertSql, rNo=i['rNo'], word=i['word'], freq=i['freq'])
                else:
                    self.db.execute_dml(updateSql, rNo=i['rNo'], word=i['word'], freq=i['freq'])
            except Exception as e:
                self.logger.error(e, 'rNo='+str(i['rNo'])+', word='+i['word']+', freq='+str(i['freq']))
        try:
            self.db.commit()
        except Exception as e:
            for i in list:
                self.logger.error(e, 'rNo=' + str(i['rNo']) + ', word=' + i['word'] + ', freq=' + str(i['freq']))

    def delete(self, rNo):
        sql = """delete from reviewWord where rNo=:rNo"""
        try:
            self.db.execute_dml(sql, rNo=rNo)
        except Exception as e:
            self.logger.error(e, 'rNo=' + str(rNo))