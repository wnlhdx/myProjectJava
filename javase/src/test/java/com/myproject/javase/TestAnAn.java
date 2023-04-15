//package com.myproject.javase;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author lkxl
// */
//public class TestAnAn {
//    class TA{
//        String code;
//        String level;
//        String parent;
//        String name;
//    }
//
//    class TB{
//        String code;
//        String level;
//        HashMap<String,TB> childrens;
//        String name;
//        TB(String c,String l){
//            this.code=c;
//            this.level=l;
//            this.childrens=new HashMap<>();
//        }
//        String SetName(String tname){
//            this.name=tname;
//        }
//    }
//
//    //多层
//    public void change(List<TA> orList){
//        //存储多个层级的关联关系
//        HashMap<Integer, HashMap<String,String>> codes=new HashMap<>();
//        //结果类
//        HashMap<String,TB> res=new HashMap<>();
//        for(TA obj:orList){
//            Integer level=Integer.parseInt(obj.level);
//            codes.get(level).put(obj.code,obj.parent);
//        }
//        for(TA obj:orList){
//            //层级判断
//           int level=Integer.parseInt(obj.level);
//           String code =obj.code;
//           String pcode=obj.parent;
//           if(pcode!=null){
//               List<String> road=new ArrayList<>();
//               road.add(null);
//               for(int i=level;i<=0;i--){
//                   code=codes.get(i).get(code);
//                   road.add(code);
//               }
//               HashMap<String,TB> temp=res;
//               for(int i=1;i<=road.size();i++){
//                   if(temp.get(road.get(i-1))==null) {
//                       TB objp = new TB(road.get(i), road.get(i - 1));
//                       temp.put((road.get(i)), objp);
//                       if(i==road.size()){
//                           objp.name=obj.name;
//                       }
//                   }
//                   temp=temp.get(road.get(i)).childrens;
//               }
//           }
//        }
//    }
//
//
//}
//
//
