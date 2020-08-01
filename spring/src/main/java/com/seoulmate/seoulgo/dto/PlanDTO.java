package com.seoulmate.seoulgo.dto;

import java.util.Date;

public class PlanDTO {
   private int planNo;
   private Date planDate;
   private String planTitle;
   private String planCate;
   private String memberid;

   public int getPlanNo() {
      return planNo;
   }

   public void setPlanNo(int planNo) {
      this.planNo = planNo;
   }

   public Date getPlanDate() {
      return planDate;
   }

   public void setPlanDate(Date planDate) {
      this.planDate = planDate;
   }

   public String getPlanTitle() {
      return planTitle;
   }

   public void setPlanTitle(String planTitle) {
      this.planTitle = planTitle;
   }

   public String getPlanCate() {
      return planCate;
   }

   public void setPlanCate(String planCate) {
      this.planCate = planCate;
   }

   public String getMemberid() {
      return memberid;
   }

   public void setMemberid(String memberid) {
      this.memberid = memberid;
   }

   @Override
   public String toString() {
      return "PlanDTO [planNo=" + planNo + ", planDate=" + planDate + ", planTitle=" + planTitle + ", planCate="
            + planCate + ", memberid=" + memberid + "]";
   }
}