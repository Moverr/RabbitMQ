/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.activeUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author muyin
 */
public class ActiveUsers {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://jsonmock.hackerrank.com/api/article_users";

 

    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<>();

        Integer threshold = 2;
        Boolean status = false;
        Integer page = 1;
        while (status == false) {
            
          
            Response r = sendGET(page);
             
            
            
            if (r.getData().size() > 0) {
             
                for (AuthorResponse ar : r.getData()) {
                    
                        System.out.println(ar.submission_count);
                      
                    if(ar.getSubmission_count()!= null){
                       if(ar.getSubmission_count() > threshold){
                         list.add(ar.username);
                     }
                } 
                    }   
                     
            }else{
                status = true;
            }

            page++;
        }
        
        System.out.println(list);
    }

    private static Response sendGET(Integer page) throws IOException {
        URL obj = new URL(GET_URL + "?page=" + page);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            Gson g = new Gson();
            Response p = g.fromJson(response.toString(), Response.class);

            return p;
        } else {
            System.out.println("GET request not worked");
        }

        return null;

    }

    public class Response {

        private String page;
        private long perPage;
        private long total;
        private long totalPages;
        private List<AuthorResponse> data;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public long getPerPage() {
            return perPage;
        }

        public void setPerPage(long perPage) {
            this.perPage = perPage;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public long getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(long totalPages) {
            this.totalPages = totalPages;
        }

        public List<AuthorResponse> getData() {
            return data;
        }

        public void setData(List<AuthorResponse> data) {
            this.data = data;
        }

    }

    public class AuthorResponse {

        private Long id;
        private String username;
        private String about;
        private Long submitted;
        private String updated_at;
        private Long submission_count;
        private Long comment_count;
        private Long created_at;

        public AuthorResponse() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public Long getSubmitted() {
            return submitted;
        }

        public void setSubmitted(Long submitted) {
            this.submitted = submitted;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Long getSubmission_count() {
            return submission_count;
        }

        public void setSubmission_count(Long submission_count) {
            this.submission_count = submission_count;
        }

        public Long getComment_count() {
            return comment_count;
        }

        public void setComment_count(Long comment_count) {
            this.comment_count = comment_count;
        }

        public Long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Long created_at) {
            this.created_at = created_at;
        }
 

    }

}
