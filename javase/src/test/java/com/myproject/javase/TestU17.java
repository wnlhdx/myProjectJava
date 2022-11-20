package com.myproject.javase;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;


import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lkxl
 */

public class TestU17 {
    static ExecutorService ThreadPool= Executors.newFixedThreadPool(100);
    String url="https://www.u17.com/comic/";
    @Test
    public String searchByURL(List<Integer> idList) throws IOException {

        StringBuilder stringBuilder=new StringBuilder("漫画名,id,推荐票\r\n");
        int k=0;
        for(Integer id:idList){
            k+=1;
            String url2=url+id+".html";
            Document document = Jsoup.connect(url2).get();
            stringBuilder.append(SearchInfo(document,id));
            if(k%100==0){
                System.out.println("已完成"+k+"个,剩余"+(idList.size()-k)+"个");
            }
        }
        String res=stringBuilder.toString();
        System.out.println("总字符串为"+res);
        return res;
    }

    @Test
    public StringBuilder SearchInfo(Document document,int id) throws IOException {
        StringBuilder stringBuilder=new StringBuilder("");
        if(!document.html().contains("月票")){
            return stringBuilder;
        }
        Elements tag=document.getElementsByClass("class_tag");
        if(tag.size()==1){
            String click=document.select("div[class=comic_infor_status]:contains(总点击)").get(0).getElementsByClass("color_red").html();
            String tickets=document.select("div[class=comic_infor_status]:contains(总月票)").get(0).getElementsByClass("color_red").html();
            if(click.contains("亿")||tickets.contains("亿")||click.contains("万")||tickets.contains("万")){
                return stringBuilder;
            }
            int clicknum=Integer.parseInt(click);
            int ticketnum=Integer.parseInt(tickets);
            String status=document.select("div[class=comic_infor_status]:contains(状态)").get(0).getElementsByClass("color_green").html();
            String type=document.select("div[class=comic_infor_status]:contains(类型)").get(0).getElementsByClass("color_green").html();
            String title=document.select("h1[class=fl]").get(0).html();
            if(type.equals("故事漫画")&&clicknum>=11500&&clicknum<=12500&&ticketnum==0) {
                stringBuilder.append(title.toString()).append(",").append(id).append(",").append(clicknum).append("\r\n");
                System.out.println("第" + id + "漫画符合要求,字符串为"+stringBuilder);
            }
        }
        return stringBuilder;
    }

    @Test
    public void output(String output,String path) throws IOException {

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path, true)));
        out.write(output);
        out.close();

    }





    @Test
    public   List<Integer> searchComic(int page){
        String url="https://www.u17.com/comic/ajax.php?mod=comic_list&act=comic_list_new_fun&a=get_comic_list";
        HashMap<String,String> map = new HashMap<>();
        map.put("data[group_id]","no");
        map.put("data[theme_id]","103");
        map.put("data[is_vip]","no");
        map.put("data[accredit]","no");
        map.put("data[color]","no");
        map.put("data[comic_type]","no");
        map.put("data[series_status]","1");
        map.put("data[order]","2");
        map.put("data[page_num]",Integer.toString(page));
        map.put("data[read_mode]","no");
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry : map.entrySet()){
            params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        httppost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
        HttpResponse response = null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        HttpEntity httpEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchComicId(result);
    }

    @Test
    public  List<Integer>  searchComicId(String cl){
        List<Integer> list =new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(cl);
        JSONArray jsonArray=jsonObject.getJSONArray("comic_list");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object=jsonArray.getJSONObject(i);
            list.add(Integer.parseInt(object.get("comic_id").toString()));
        }
        return list;
    }

    @Test
    public   List<Integer> searchComic2(int page){
        String url="https://www.u17.com/comic/ajax.php?mod=comic_list&act=comic_list_new_fun&a=get_comic_list";
        HashMap<String,String> map = new HashMap<>();
        map.put("data[group_id]","no");
        map.put("data[theme_id]","103");
        map.put("data[is_vip]","no");
        map.put("data[accredit]","no");
        map.put("data[color]","no");
        map.put("data[comic_type]","no");
        map.put("data[series_status]","1");
        map.put("data[order]","2");
        map.put("data[page_num]",Integer.toString(page));
        map.put("data[read_mode]","2");
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry : map.entrySet()){
            params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        httppost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
        HttpResponse response = null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        HttpEntity httpEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchComicId(result);
    }

    @Test
    public  void u17() throws IOException {
        List<Integer> list= new ArrayList<>();
        for(int i=0;i<50;i++){
            list.addAll(searchComic(i+1));
        }for(int i=0;i<29;i++){
            list.addAll(searchComic2(i+1));
        }
        Collections.sort(list);
        System.out.println("已经加载完全部漫画，总数量为"+list.size());
        String res=searchByURL(list);
        output(res,"D:\\work\\cache\\text.csv");
    }

    @Test
    public void testBug() throws IOException {
        List<Integer> list =new ArrayList<>();
        list.add(68567);
        list.add(76841);
        list.add(77476);
        list.add(85689);
        list.add(114222);
        list.add(119809);
        list.add(120393);
        List<Integer> list2= new ArrayList<>();
        for(int i=0;i<29;i++){
            list2.addAll(searchComic2(i+1));
        }
        Collections.sort(list2);
        System.out.println(list2.toString());
        for(Integer i:list){
            System.out.println(list2.contains(i));
        }

    }


}
