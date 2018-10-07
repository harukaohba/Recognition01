package recognition01;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;



public class Recognition01_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("====");
		DetectFacesOptions detectFacesOptions = null;
			try {
				detectFacesOptions = new DetectFacesOptions.Builder()
						  .imagesFile(new File("img/prez.jpg"))
						  .build();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		DetectedFaces result = service.detectFaces(detectFacesOptions).execute();
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(String.valueOf(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int age_min = node.get("images").get(0).get("faces").get(0).get("age").get("min").asInt();
		int age_max = node.get("images").get(0).get("faces").get(0).get("age").get("max").asInt();
		double age_score = node.get("images").get(0).get("faces").get(0).get("age").get("score").doubleValue();
		int gender;
		if(node.get("images").get(0).get("faces").get(0).get("gender").get("gender").toString() == "MALE") {
			gender = 0;
		}else{
			gender = 1;
		}
		double gender_score = node.get("images").get(0).get("faces").get(0).get("gender").get("score").doubleValue();
		 System.out.println("age_min : " + age_min);
		 
		 //MySQL mysql = new MySQL();
		 //mysql.recognition01(age_min,age_max,age_score,gender,gender_score);
		 
		 

		//System.out.println(result.getImages().iterator().next().getFaces().iterator().next().getAge());
		//String age_min =  result.getImages().iterator().next().getFaces().iterator().next().getAge().getMin().toString();
		//System.out.println(age_min);
		
		/*
		try {
            // FileWriterクラスのオブジェクトを生成する
            FileWriter file = new FileWriter(new File("img/test.json"));
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            //ファイルに書き込む
            pw.println(result);
            //ファイルを閉じる
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		*/
		//JSONArray arr = new JSONArray(script);
		
		/*try {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File("test.json"));
		//String member_name = root.get("tets").get(0).get("img/test.json").asText();
        //System.out.println(member_name);
		}catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
	}

}
