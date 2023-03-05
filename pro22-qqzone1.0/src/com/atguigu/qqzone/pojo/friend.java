package com.atguigu.qqzone.pojo;

/**
 * ClassName: friend
 * Package: com.atguigu.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 10:50
 * @Version 1.0
 */

public class friend {
   private Integer id;
   private Integer uid;
   private Integer fid;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getUid() {
      return uid;
   }

   public void setUid(Integer uid) {
      this.uid = uid;
   }

   public Integer getFid() {
      return fid;
   }

   public void setFid(Integer fid) {
      this.fid = fid;
   }

   public friend(Integer id, Integer uid, Integer fid) {
      this.id = id;
      this.uid = uid;
      this.fid = fid;
   }

   public friend() {
   }
}
