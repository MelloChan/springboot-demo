package com.mello;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@SpringBootApplication
public class MelloChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MelloChatApplication.class, args);
	}

	@Bean
	public MongoClient client(){
		//配置mongoDB客户端
		return new MongoClient(new ServerAddress("localhost",27017));
	}
	@Bean
	public MongoDbFactory mongoDbFactory(){
		//配置mongodb工厂
		String database=new MongoClientURI("mongodb://localhost/chat").getDatabase();
		return new SimpleMongoDbFactory(client(),database);
	}
	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory){
		return new MongoTemplate(mongoDbFactory);
	}
}
