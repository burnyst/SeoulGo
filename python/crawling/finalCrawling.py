from selenium import webdriver
import time
import pandas as pd
from bs4 import BeautifulSoup as bs
import urllib.request
import urllib.parse
import numpy as np
import requests
import cx_Oracle
import os
import random

# ★☆★☆★☆★☆이게 전체 내용 한번에 가져오고 저장하는 진짜진짜 파이널★☆★☆★☆★☆
# 실행기록1 : test2(음식점 1-10위)
# 실행기록2 : 48번까지 했음
# 실행기록3 : 1000개 돌리기

os.putenv('NLS_LANG', '.UTF8')

# 연결 기본 정보(유저, 비밀번호, 서버주소)
conn = cx_Oracle.connect("seoulmate",'seoulgo', '192.168.56.83/orcl')
# "seoulmate", "seoulgo", "192.168.56.83/orcl"
cursor = conn.cursor()

urls = pd.read_csv('output\\test2.csv', header=None)    # 콤마 없는거
urls = np.array(urls[0].tolist())
placeNo = 1917                         # 시작할 때 여기 변경하기!!!!!!
for url in urls:
    url = 'https://www.tripadvisor.co.kr/'+url
    print(url)
    recvd = requests.get(url)
    dom = bs(recvd.text, 'html.parser', from_encoding='utf-8')

    # 장소 번호
    placeNo = placeNo + 1

    # 식당 이름(placeName)
    placeName = dom.find('h1', {'class': "_3a1XQ88S"}).text
    placeName = placeName.strip()
    print('placeName=', placeName)

    # 식당 주소, 상세 주소(addr1, addr2)
    try:
        addr = dom.find('span',{'class':'_2saB_OSe'}).text
        addr = addr.split(' ')
        test = addr[1][-1]
        # print(test)
        if test == '구':
            addr1 = addr[0] + ' ' + addr[1]
            addr1 = addr1.strip()
            addr2 = addr[2:]
            print('구정보가 있는 addr1=' + addr1)
            addr2 = ' '.join(addr2)
            addr2 = addr2.strip()
            print('구정보가 있는 addr2='+addr2)
        else:
            addr1 = addr[0]
            addr1 = addr1.strip()
            addr2 = addr[1:]
            addr2 = ' '.join(addr2)
            addr2 = addr2.strip()
            print('구정보가 없는 addr1=' + addr1)
            print('구정보가 없는 addr2='+addr2)
        # print('addr=', addr)
        # print('addr=', addr)
    except:
        print('주소가 없어 패스')
        continue
    # 카테고리 즐길거리0, 음식점1(cate)
    cate = 1

    # 있는지 없는지 검증부터 not null 가능한 컬럼
    # 전화번호(tel)
    if dom.find('span',{'class':'_15QfMZ2L'}) :
        temp_tel = dom.find('span',{'class':'_15QfMZ2L'})
        tel = temp_tel.find('a',{'class':'_3S6pHEQs'}).text
        tel = tel.replace('+82 ','0')
        tel = tel.strip()
        print('tel=', tel)
    else: tel = None

    # 장소평점(placeRate)
    if dom.find('span',{'class':'r2Cf69qf'}) :
        placeRate = dom.find('span',{'class':'r2Cf69qf'}).text
        placeRate = placeRate.strip()
        print('placeRate=', placeRate)
    else: placeRate = None

    # sql_insert_place = 'insert into place(placeNo,placeName,addr1,addr2,cate,tel,reviewcount, placeRate) values (:1,:2,:3,:4,:5,:6,:7,:8)'
    sql_insert_place = 'insert into place values (:1,:2,:3,:4,:5,:6,:7,:8)'
    data_place = (placeNo, placeName, addr1, addr2, cate, tel, None, placeRate)
    # 전체 리뷰 수는 데이터 크롤링 후 일괄 처리
    print(data_place)
    cursor.execute(sql_insert_place, data_place)


    # 이미지(placeImage테이블, iname, 장소번호, imgURL)
    if dom.find('div',{'class':'prw_rup prw_common_basic_image photo_widget mini landscape'}):
        imgs = dom.find_all('div',{'class':'prw_rup prw_common_basic_image photo_widget mini landscape'})
        imgNum = 0
        for i in imgs:
            imgNum += 1
            if imgNum>3 :       # 최대 3개
                break
            imgURL = i.find('img')['data-lazyurl']
            temp = imgURL.split('media/')
            iname = temp[1]
            iname = iname.replace('/','_')

            try:
                urllib.request.urlretrieve(imgURL, 'placeImage\\'+iname)    # 이미지 저장
                sql_insert_placeImage = 'insert into placeImage values (:1, :2, :3)'
                data_placeImage = (iname, placeNo ,imgURL)
                cursor.execute(sql_insert_placeImage, data_placeImage)
            except:
                iname = iname+str(random.randrange(1,1000))
                urllib.request.urlretrieve(imgURL, 'placeImage\\'+iname)    # 이미지 저장
                sql_insert_placeImage = 'insert into placeImage values (:1, :2, :3)'
                data_placeImage = (iname, placeNo ,imgURL)
                cursor.execute(sql_insert_placeImage, data_placeImage)
            finally:
                print(str(imgNum) + " && " + iname + " && " + imgURL)

    # 전체리뷰수 (리뷰 크롤링용)
    temp = dom.find('div', {'class': 'ratings_and_types block_wrap ui_section'})
    ko = dom.find('div', {'data-tracker': '한국어'})
    try:
        koCount = ko.find('span', {'class': 'count'}).text  # 1페이지에 있는 한국어 리뷰 수르를 담음
        koCount = koCount.replace('(', '')
        koCount = koCount.replace(')', '')
        koCount = int(koCount)
        print(koCount)
    except:
        koCount = 0

    if koCount>0:        # 일단 한국어 리뷰가 1개 이상이라면
        try:
            temp.find('div', {'class': 'pageNumbers'})                   # 리뷰div에서 pageNumber가 있는 경우
            temp_lastNumber = dom.find('div', {'class': 'pageNumbers'})
            lastNumber = temp_lastNumber.find('a', {'class': 'pageNum last cx_brand_refresh_phase2'})['data-offset']
            lastNumber = int(lastNumber) + 10
            print('리뷰 라스트 페이지 : ', lastNumber)
        except:                                                         # 페이지 넘버가 없는 경우
            lastNumber = 10
            print('한국어 리뷰 1페이지인 경우')
    else:                 # 한국어 리뷰가 아예 없는 경우
        lastNumber = None

    if lastNumber == None:
        print('리뷰가 없습니다.')
    else:
        # 리뷰
        for i in range(0, lastNumber, 10):  # 리뷰 전체 페이지 수
            # url ='https://www.tripadvisor.co.kr//Restaurant_Review-g294197-d17423735-Reviews-Jangseng_Geongangwon-Seoul.html'
            url_str = url.split('-Reviews-')[0]+'-Reviews-or'
            url_num = str(i)
            url_keyword = '-'+url.split('-Reviews-')[1]

            total_url = url_str + url_num + url_keyword
            print('리뷰 total_url',total_url)

            driver = webdriver.Chrome("c:/chromedriver.exe")
            driver.get(total_url)
            time.sleep(3)
            # 모든 리뷰의 더 보기 열기
            try:
                driver.find_elements_by_css_selector(".ulBlueLinks")[0].click()
                time.sleep(2)           # 더보기 누르고 2초(각 페이지마다 2초 휴식)
            except:
                print('더 보기가 없습니다.')

            # 뷰티풀숩을 위한 선언
            recvd = requests.get(total_url)
            dom = bs(recvd.text, 'html.parser', from_encoding='utf-8')

            reviews = dom.find_all('div', {'class': 'rev_wrap ui_columns is-multiline'})

            # 이건 컨텐츠를 위한 셀레니움 선언
            reviewContentOnly = driver.find_elements_by_css_selector(".review-container")

            j = 0  # 컨텐츠 가져오는 번호(0-9)

            for r in reviews:
                # 작성자
                try:
                    writer = r.find('div', {'class': 'info_text pointer_cursor'}).text
                    writerEmail = writer+'@naver.com'
                except:
                    writer = 'writer'+str(random.randrange(1,1000))
                    writerEmail = writer + '@naver.com'

                # 회원테이블 생성
                # insert into member_t(memberID, memberPW, mName, nickname, gender, birth, phone1, phone2, phone3, email, mLevel)
                # values ('kkm402', 'kkm402', 'kkm402', 'kkm402', 'F', '1995-10-27', '000','000','000','kkm402@naver.com','ROLE_MEMBER');
                try:
                    sql_insert_member_t = '''insert into member_t(memberID, memberPW, mName, nickname, gender, 
                                                                     birth, phone1, phone2, phone3, email, mLevel)
                                            values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11)'''

                    data_member_t = (writer, writer, writer, writer, 'F', '1990-07-14', '000', '000', '000', writerEmail, 'ROLE_MEMBER')
                    cursor.execute(sql_insert_member_t, data_member_t)
                except:
                    print('이미 가입된 회원입니다.')
                finally:
                    # 방문일
                    visitDate = r.find('div', {'class': 'prw_rup prw_reviews_stay_date_hsx'}).text
                    search = '방문'
                    indexNo_visitDate = visitDate.find(search)
                    if indexNo_visitDate ==-1:
                        visitDate = None
                    else:
                        visitDate = visitDate.replace('방문 날짜: ', '')
                        visitDate = visitDate.replace('년 ', '/')
                        visitDate = visitDate.replace('월', '')
                        visitDate = visitDate+'/1'               # 2020/6/1
                        visitDate = visitDate.strip()

                    # 작성일
                    writeDate = r.find('span', {'class': 'ratingDate'}).text
                    search = '월'
                    indexNo_writeDate = writeDate.find(search)
                    if indexNo_writeDate==-1:
                        writeDate = visitDate
                    else:
                        writeDate = r.find('span', {'class': 'ratingDate'}).text
                        writeDate = writeDate.replace('년 ', '/')
                        writeDate = writeDate.replace('월 ', '/')
                        writeDate = writeDate.replace('일에 작성.', '').strip()             # 2020/6/6
                    # 별점
                    score = r.find('div', {'class': 'ui_column is-9'})
                    score = score('span')[0]
                    score = score['class'][1]
                    score = int(score.replace('bubble_', '')) / 10
                    # 리뷰 제목
                    title = r.find('span', {'class': 'noQuotes'}).text

                    # 리뷰 내용 : 이것만 셀레니움으로 처리(셀레니움으로 해야 더 보기를 클릭한 값을 가져올 수 있다.)
                    try:
                        temp_review = reviewContentOnly[j].find_element_by_css_selector(".partial_entry").text
                        content = temp_review.replace("\n", " ")  # 라인 변경 띄어쓰기로 교체
                        # print(content)
                    except:  # 에러 시 나올 대체 문장(분석시에는 제외하고 워드 클라우드 가능)
                        if score == 5.0:
                            content = '인생 맛집입니다. (5점)'
                        elif score == 4.0:
                            content = '맛있네요. 추천해요. (4점)'
                        elif score == 3.0:
                            content = '평범합니다! (3점)'
                        elif score == 2.0:
                            content = '굳이 가야할까요..? (2점)'
                        else:
                            content = '가지마세요. (1점)'
                        # print(content)
                    j = j + 1

                    # insert into reviewboard(rNo, rTitle, rContent, rDate, rVisit, rRate, memberID, placeNo)
                    # values (1,'진짜 추천','안가고 뭐하지? 언능 가세요', '2019/7/14', '2019/7/14', 4.0, 'kkm402', 1);
                    sql_insert_reviewBoard = '''insert into reviewBoard(rNo, rTitle, rContent, rDate, rVisit, rRate, memberID, placeNo) 
                                                values (REVIEWBOARD_SEQ.nextval, :1, :2, TO_DATE(:3,'YYYY-MM-DD'), TO_DATE(:4,'YYYY-MM-DD'), :5, :6, :7)'''
                    data_reviewBoard = (title, content, writeDate, visitDate, score, writer, placeNo)
                    print(data_reviewBoard)
                    cursor.execute(sql_insert_reviewBoard, data_reviewBoard)

                    time.sleep(0.5)       # 리뷰 사이마다 1초
            print('-' * 50)
            time.sleep(1)           # 리뷰 페이지마다 2초
        print(placeNo,', ',placeName, ' 끝!')
        conn.commit()
cursor.close()
conn.close()
